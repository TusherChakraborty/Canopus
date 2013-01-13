/*  ---
 * 	Welcome to the 'Canopus' code!
 *  ---	
 * 
 *	Player
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 06.01.2013
 * 
 * @lastChange: 06.01.2013
 * @Info: creation
 */

package de.datpixelstudio.canopus;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import de.datpixelstudio.statebasedgame.Direction;
import de.datpixelstudio.statebasedgame.GameContainer;

public class Player extends GameObject {

	private Fixture sensorBottom = null;
	
	private float maxVelocity = 7f;
	
	private boolean isGround = false;
	private boolean isMovement = false;
	
	public Player(World world) {
		super(world);
		
		createBody();
	}
	
	private void createBody() {
		setType(GameObject.Type.DYNAMIC, false);
		
		/* Body physic */
		Vector2[] vertices = {
				new Vector2(0,0),
				new Vector2(1.5f,0),
				new Vector2(1.5f,1.75f),
				new Vector2(0,1.75f)
		};
		setPolygonVertices(vertices);
		setAsCircle(0.85f, new Vector2(0.75f, -0f));
		setFixedRotation(true);
		
		/* Sensor bottom*/
		FixtureDef sensorDef = new FixtureDef();
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(0.6f);
		circleShape.setPosition(new Vector2(0.75f, -0.4f));
		sensorDef.shape = circleShape;
		sensorDef.isSensor = true;
		sensorBottom = getBody().createFixture(sensorDef);
		addFixture(sensorBottom);
		circleShape.dispose();
		
		setFriction(100);
	}
	
	public void update() {
		updateSensorEvents();
		
		if(Math.abs(getLinearVelocity().x) > maxVelocity) {
			getLinearVelocity().x = Math.signum(getLinearVelocity().x) * maxVelocity;
			getBody().setLinearVelocity(getLinearVelocity().x , getLinearVelocity().y);
		}
		
		if(!isMovement) {
			getBody().setLinearVelocity(getLinearVelocity().x * 0.9f, getLinearVelocity().y);
		}
		
		if(!isGround) {
			setFriction(0);
		} else {
			if(!isMovement) {
				setFriction(100f);
			} else {
				setFriction(0.2f);
			}
		}
	}
	
	public void draw(final GameContainer gc) {
		
	}
	
	public void move(final Direction direction) {
		if(direction == Direction.LEFT && getLinearVelocity().x > -maxVelocity) {
			getBody().applyLinearImpulse(-2f,  0, getBody().getPosition().x, getBody().getPosition().y);
		}
		
		if(direction == Direction.RIGHT && getLinearVelocity().x < maxVelocity) {
			getBody().applyLinearImpulse(2f,  0, getBody().getPosition().x, getBody().getPosition().y);
		}

		if(direction == Direction.UP) {
			
		}
		
		if(direction == Direction.DOWN) {
			
		}
	}
	
	private void updateSensorEvents() {
		for(Contact contact : world.getContactList()) {
			if(contact.isTouching())
			if(contact.isTouching() 
					&& (contact.getFixtureA() == sensorBottom
						|| contact.getFixtureB() == sensorBottom)) {
				isGround = true;
				return;
			}
		}
		isGround = false; // maybe first statement
	}
	
	public boolean isGround() { return isGround; }
}



















