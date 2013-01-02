package de.datpixelstudio.canopus;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import de.datpixelstudio.canopus.states.Box2DTestState;
import de.datpixelstudio.statebasedgame.TextureSet;

public class Player {
	
	private World world;
	
	private float maxVelocity = 7f;
	
	private Vector2 position = null;
	private Vector2 velocity = null;
	
	private Body body = null;
	private Fixture physicFixture = null;
	private Fixture sensorFixture = null;
	
	private boolean isGround = false;
	private boolean isJump = false;
	
	private float stillTime = 0;
	private long lastGroundTime = 0;
	
	private TextureRegion texture = null;
	
	public Player(final Vector2 position, World world) {
		this.position = position;
		this.world = world;
		
		velocity = new Vector2();
		
		texture = TextureSet.MISC_TEX.getTexture(1);
		
		create();
	}
	
	private void create() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position);
		body = world.createBody(bodyDef);
		
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(0.95f, 1.5f);
		physicFixture = body.createFixture(polygonShape, 1);
		polygonShape.dispose();
		
		CircleShape circle = new CircleShape();
		circle.setRadius(1f);
		circle.setPosition(new Vector2(0, -1.5f));
		sensorFixture = body.createFixture(circle, 0);
		circle.dispose();
		
		body.setBullet(true);
		body.setFixedRotation(true);
	}
	
	public boolean isPlayerGrounded() {
		return isGround;
	}
	
	private void updateIsPlayerGrounded() {
		List<Contact> contactList = world.getContactList();
		for(Contact contact : contactList) {
			if(contact.isTouching() && (contact.getFixtureA() == sensorFixture 
					|| contact.getFixtureB() == sensorFixture)) {
				isGround = true;
				return;
			}
		}
		isGround = false;
	}
	
	public Vector2 getPostion() {
		return position;
	}
	
	public void draw(final SpriteBatch b) {
		b.draw (texture, body.getPosition().x, body.getPosition().y, 0, 0, 
				64, 64, Box2DTestState.WORLD_TO_BOX, Box2DTestState.WORLD_TO_BOX, body.getAngle());
		
	}
	
	public void update() {
		updateIsPlayerGrounded();
		position = body.getPosition();
		
		if(isGround) {
			lastGroundTime = System.nanoTime();
		} else {
			if(System.nanoTime() - lastGroundTime < 100000000) {
				isGround = true;
			}
		}
		
		if(Math.abs(getLinearVelocity().x) > maxVelocity) {
			getLinearVelocity().x = Math.signum(getLinearVelocity().x) * maxVelocity;
			body.setLinearVelocity(getLinearVelocity().x , getLinearVelocity().y);
		}
		
		if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)) {
			stillTime += Gdx.graphics.getDeltaTime();
			body.setLinearVelocity(getLinearVelocity().x * 0.9f, getLinearVelocity().y);
		} else {
			stillTime = 0;
		}
		
		if(!isGround) {
			physicFixture.setFriction(0);
			sensorFixture.setFriction(0);
		} else {
			if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT) && stillTime > 0.2) {
				physicFixture.setFriction(100f);
				sensorFixture.setFriction(100f);
			} else {
				physicFixture.setFriction(0.2f);
				sensorFixture.setFriction(0.2f);
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT) && getLinearVelocity().x > -maxVelocity) {
			body.applyLinearImpulse(-2f,  0, position.x, position.y); 
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && getLinearVelocity().x < maxVelocity) {
			body.applyLinearImpulse(2f,  0, position.x, position.y); 
		}
		
		if(isJump) {
			isJump = false;
			if(isGround) {
				body.setLinearVelocity(getLinearVelocity().x, 0);
				body.setTransform(position.x,  position.y + 0.01f, 0);
				body.applyLinearImpulse(0, 45, position.x, position.y);
			}
		}
	}
	
	public Vector2 getLinearVelocity() {
		return body.getLinearVelocity();
	}
	
	public void setJump(final boolean jump) {
		this.isJump = jump;
	}
}















