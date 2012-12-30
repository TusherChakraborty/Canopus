package de.datpixelstudio.canopus;

import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Player {
	
	private World world;
	
	private Vector2 position = null;
	private Vector2 velocity = null;
	
	private Body body = null;
	private Fixture physicFixture = null;
	private Fixture sensorFixture = null;
	
	private boolean isGround = false;
	
	public Player(final Vector2 position, World world) {
		this.position = position;
		this.world = world;
		
		velocity = new Vector2();
		
		create();
	}
	
	private void create() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position);
		body = world.createBody(bodyDef);
		
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(1, 1.5f);
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
		// Hier: What the fuck?
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
	
	public void update() {
		updateIsPlayerGrounded();
	}
}















