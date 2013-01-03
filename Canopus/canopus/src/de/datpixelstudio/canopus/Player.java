package de.datpixelstudio.canopus;

import java.util.ArrayList;
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
	private ArrayList<LevelRectangles> level = null;
	
	private float maxVelocity = 7f;
	
	private Vector2 position = null;
	private Vector2 velocity = null;
	
	private Body body = null;
	private Fixture physicFixture = null;
	
	private Fixture sensorFixtureLeft = null;
	private Fixture sensorFixtureMiddle = null;
	private Fixture sensorFixtureRighte = null;
	
	private boolean isJumpAllowed = true;
	
	private boolean isGround = false;
	private boolean isJump = false;
	
	private float stillTime = 0;
	private long lastGroundTime = 0;
	
	private TextureRegion texture = null;
	
	private Vector2 aPos = null, aSize = null, bPos = null, bSize = null;
	
	public Player(final Vector2 position, World world, ArrayList<LevelRectangles> level) {
		this.position = position;
		this.world = world;
		this.level = level;
		
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
		//polygonShape.setAsBox(0.95f, 1.5f);
		
		Vector2[] vertices = {
				new Vector2(0.1f,0),
				new Vector2(0.5f,-0.35f),
				new Vector2(1.5f,-0.35f),
				new Vector2(1.9f,0),
				new Vector2(2.1f,3),
				new Vector2(-0.05f,3)
		};
		
		Vector2[] vertices2 = {
				new Vector2(0,0),
				new Vector2(0.5f,-0.35f),
				new Vector2(1.5f,-0.35f),
				new Vector2(2,0),
				new Vector2(2.0f,2),
				new Vector2(0f,2)
		};
		
		polygonShape.set(vertices2);
		physicFixture = body.createFixture(polygonShape, 1);
		polygonShape.dispose();
		
		CircleShape circle = new CircleShape();
		circle.setRadius(0.5f);
		// Left
		circle.setPosition(new Vector2(0.5f, 0.0f));
		sensorFixtureLeft = body.createFixture(circle, 1);
		// Right
		circle.setPosition(new Vector2(1.5f, 0f));
		sensorFixtureRighte = body.createFixture(circle, 1);
		circle.dispose();
		// Center
		PolygonShape polygonShape2 = new PolygonShape();
		//polygonShape2.setAsBox(0.5f, 0.5f);
		Vector2[] vertices3 = {
				new Vector2(0.5f,-0.5f),
				new Vector2(1.5f,-0.5f),
				new Vector2(1.5f,0.0f),
				new Vector2(0.5f,0.0f),
		};
		polygonShape2.set(vertices3);
		sensorFixtureMiddle = body.createFixture(polygonShape2, 1);
		
		body.setBullet(true);
		body.setFixedRotation(true);
		body.setUserData("Player");
	}
	
	public boolean isPlayerGrounded() {
		return isGround;
	}
	
	private void updateIsPlayerGrounded() {
		List<Contact> contactList = world.getContactList();
		for(Contact contact : contactList) {
			if(contact.isTouching() && ( 
					
					(contact.getFixtureA() == sensorFixtureLeft 
					|| contact.getFixtureB() == sensorFixtureLeft)
					
					||
					
					(contact.getFixtureA() == sensorFixtureRighte 
					|| contact.getFixtureB() == sensorFixtureRighte)

					||
					
					(contact.getFixtureA() == sensorFixtureMiddle 
					|| contact.getFixtureB() == sensorFixtureMiddle)
					)
					) {
				isGround = true;
				
				if(contact.getFixtureA().getUserData() instanceof FixtureDatas) {
					if(!((FixtureDatas) contact.getFixtureA().getUserData()).isJumpable()) {
						isJumpAllowed = false;
					} else {
						isJumpAllowed = true;
					}
				} else {
					isJumpAllowed = true;
				}
				
				return;
			}
		}
		isGround = false;
	}
	
	/*
	private void updateIsPlayerGrounded() {
		List<Contact> contactList = world.getContactList();
		for(Contact contact : contactList) {
			if(contact.isTouching() && (contact.getFixtureA() == sensorFixture 
					|| contact.getFixtureB() == sensorFixture)) {
				
				if(contact.getFixtureA().getBody().getUserData() != null && contact.getFixtureA().getBody().getUserData().equals("Player")){
					System.out.println("A = Player");
					
					bPos = contact.getFixtureB().getBody().getPosition();
					if(getLevelObject(contact.getFixtureB().getUserData()).getSize() != null){
						bSize = getLevelObject(contact.getFixtureB().getUserData()).getSize();
					}
					
					if(contact.getFixtureA().getBody().getPosition().y - (1.5) >= bPos.y + (bSize.y)){
						isGround = true;
						return;
					}
				}
				else if(contact.getFixtureB().getBody().getUserData() != null && contact.getFixtureB().getBody().getUserData().equals("Player")){
					System.out.println("B = Player");
					
					aPos = contact.getFixtureA().getBody().getPosition();
					if(getLevelObject(contact.getFixtureA().getUserData()).getSize() != null){
						aSize = getLevelObject(contact.getFixtureA().getUserData()).getSize();
					}
					
					if(contact.getFixtureB().getBody().getPosition().y - (1.5) >= aPos.y + (aSize.y)){
						isGround = true;
						return;
					}
				}
			}
		}
		isGround = false;
	}
	*/
	
	private LevelRectangles getLevelObject(final Object object) {
		if(object == null) return null;
		for(LevelRectangles obj : level) {
			if((object.toString().equals(obj.getFixture().getUserData().toString()))) {
				return obj;
			}
		}
		return null;
	}
	
	public Vector2 getPostion() {
		return position;
	}
	
	public void draw(final SpriteBatch b) {
	//	b.draw (texture, body.getPosition().x, body.getPosition().y, 0, 0, 
	//			64, 64, Box2DTestState.WORLD_TO_BOX, Box2DTestState.WORLD_TO_BOX, body.getAngle());
		
	}
	
	private void setSensorFrictions(final float friction) {
		sensorFixtureLeft.setFriction(friction);
		sensorFixtureMiddle.setFriction(friction);
		sensorFixtureRighte.setFriction(friction);
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
			setSensorFrictions(0);
		} else {
			if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)) {
				physicFixture.setFriction(100f);
				setSensorFrictions(100f);
			} else {
				physicFixture.setFriction(0.2f);
				setSensorFrictions(0.2f);
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT) && getLinearVelocity().x > -maxVelocity) {
			body.setTransform(new Vector2(body.getPosition().x - 0.01f,  body.getPosition().y), 0);
			body.applyLinearImpulse(-2f,  0, position.x, position.y);
			//body.setTransform(new Vector2(body.getPosition().x + 0.01f,  body.getPosition().y), 0);
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT) && getLinearVelocity().x < maxVelocity) {
			body.setTransform(new Vector2(body.getPosition().x + 0.01f,  body.getPosition().y), 0);
			body.applyLinearImpulse(2f,  0, position.x, position.y); 
			//body.setTransform(new Vector2(body.getPosition().x - 0.01f,  body.getPosition().y), 0);
		}
		
		if(isJump && isJumpAllowed) {
			isJump = false;
			if(isGround) {
				body.setLinearVelocity(getLinearVelocity().x, 0);
				body.setTransform(position.x,  position.y + 0.01f, 0);
				body.applyLinearImpulse(0, 80, position.x, position.y);
			}
		}
		
		if(!isJump && isGround) {
			if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT) && stillTime > 0.3f) {
				//body.setLinearVelocity(0, getLinearVelocity().y);
				body.setGravityScale(0);
			} else {
				body.setGravityScale(2);
			}
		} else {
			body.setGravityScale(2);
		}
	}
	
	public Vector2 getLinearVelocity() {
		return body.getLinearVelocity();
	}
	
	public void setJump(final boolean jump) {
		this.isJump = jump;
	}
	
	public boolean isJump() {
		return isJump;
	}
	
	public float getFriction() {
		return sensorFixtureRighte.getFriction();
	}
}















