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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import de.datpixelstudio.statebasedgame.Direction;
import de.datpixelstudio.statebasedgame.GameContainer;

public class Player extends GameObject {

	private Fixture sensorBottom = null;
	
	float leftOffset = -2.0f;
	private Fixture sensorPlayerLeft = null;
	float rightOffset = 2.0f;
	private Fixture sensorPlayerRight = null;
	float bottomCut = 0.5f;
	
	private Fixture sensorPlayerCenter = null;
	
	private float maxVelocity = 4f;
	
	private boolean isGround = false;
	private boolean isMovement = false;
	private Direction direction = Direction.RIGHT;
	
	private boolean isJump = false;
	
	private float stillTime = 0;
	private long lastGroundTime = 0;
	
	//private HashMap<Direction, Boolean> dimensonsSwitchDirAllowed = null;
	
	public Player(World world) {
		super(world);
		
		createBody();
		/*
		dimensonsSwitchDirAllowed = new HashMap<Direction, Boolean>();
		dimensonsSwitchDirAllowed.put(Direction.LEFT, false);
		dimensonsSwitchDirAllowed.put(Direction.RIGHT, false);
		dimensonsSwitchDirAllowed.put(Direction.UP, false);
		dimensonsSwitchDirAllowed.put(Direction.DOWN, false);
		*/
		
		loadPlayerInfos();
		/* Test */
		//setDimensonsSwitchDir(Direction.LEFT, true);
		//setDimensonsSwitchDir(Direction.RIGHT, true);
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
		
		/* Sensor Player Center */
		FixtureDef centerDef = new FixtureDef();
		PolygonShape polygonShapeCenter = new PolygonShape();
		Vector2[] verticesCenter = {
				new Vector2(0,0 - 0.7f + bottomCut),
				new Vector2(1.5f,0 - 0.7f + bottomCut),
				new Vector2(1.5f, 1.75f),
				new Vector2(0, 1.75f)
		};
		polygonShapeCenter.set(verticesCenter);
		centerDef.isSensor = true;
		centerDef.shape = polygonShapeCenter;
		sensorPlayerCenter = getBody().createFixture(centerDef);
		addFixture(sensorPlayerCenter);
		polygonShapeCenter.dispose();
		
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
		
		/* Sensor player left */
		FixtureDef bodyDef = new FixtureDef();
		PolygonShape polygonShape = new PolygonShape();
		Vector2[] vertices2 = {
				new Vector2(0 + leftOffset, 0 - 0.7f + bottomCut),
				new Vector2(1.5f + leftOffset,0 - 0.7f + bottomCut),
				new Vector2(1.5f + leftOffset, 1.75f),
				new Vector2(0 + leftOffset, 1.75f)
		};
		polygonShape.set(vertices2);
		bodyDef.isSensor = true;
		bodyDef.shape = polygonShape;
		sensorPlayerLeft = getBody().createFixture(bodyDef);
		addFixture(sensorPlayerLeft);
		
		/* Sensor player right */
		FixtureDef rightFixDef = new FixtureDef();
		Vector2[] verticesRight = {
				new Vector2(0 + rightOffset,0 - 0.7f + bottomCut),
				new Vector2(1.5f + rightOffset,0 - 0.7f + bottomCut),
				new Vector2(1.5f + rightOffset, 1.75f),
				new Vector2(0 + rightOffset, 1.75f)
		};
		polygonShape.set(verticesRight);
		rightFixDef.isSensor = true;
		rightFixDef.shape = polygonShape;
		sensorPlayerRight = getBody().createFixture(rightFixDef);
		addFixture(sensorPlayerRight);
		polygonShape.dispose();
		
		setFriction(0);
		setDensity(1);
		setRestitution(0);
	}
	
	public void update() {
		updateSensorEvents();
		
		if(isGround) {
			lastGroundTime = System.nanoTime();
		} else {
			if(System.nanoTime() - lastGroundTime < 100000000) {
				isGround = true;
			}
		}
		
		if(Math.abs(getLinearVelocity().x) > maxVelocity) {
			getLinearVelocity().x = Math.signum(getLinearVelocity().x) * maxVelocity;
			getBody().setLinearVelocity(getLinearVelocity().x , getLinearVelocity().y);
		}
		
		if(!isMovement) {
			stillTime += Gdx.graphics.getDeltaTime();
			getBody().setLinearVelocity(getLinearVelocity().x * 0.9f, getLinearVelocity().y);
		} else {
			stillTime = 0;
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
		
		if(isJump && isJumpAllowed()) {
			isJump = false;
			if(isGround) {
				getBody().setLinearVelocity(getLinearVelocity().x, 0);
				getBody().setTransform(getBody().getPosition().x,  getBody().getPosition().y + 0.01f, 0);
				getBody().applyLinearImpulse(0, 40, getBody().getPosition().x, getBody().getPosition().y);
			}
		}
		
		if(!isJump && isGround && stillTime > 0.35f) {
			if(!isMovement) {
				getBody().setGravityScale(0);
			} else {
				getBody().setGravityScale(1f);
			}
		} else {
			getBody().setGravityScale(1f);
		}
	}
	
	public void setJump(final boolean isJump) {
		this.isJump = isJump;
	}
	
	private boolean isJumpAllowed() {
		//TODO 
		return true;
	}
	
	public void movement(final boolean isMovement) {
		this.isMovement = isMovement;
	}
	
	public void draw(final GameContainer gc) {
		
	}
	
	public void switchDimension(final Level level, final int newDimensionSetIndex) {
		Vector2 oldPosition = getBody().getPosition();
		int oldDimensionSetIndex = level.getActiveDimensionSetIndex();
		
		// TODO doppel code ...
		if(direction == Direction.LEFT) {
			if(isCollision(Direction.LEFT)) {
				level.setDimensionSet(newDimensionSetIndex);
				getBody().setTransform(getBody().getPosition().x + leftOffset, getBody().getPosition().y, 0);
				world.step(1/100, 1, 1);
				if(!isCollision(Direction.LEFT)) {
					getBody().setTransform(oldPosition, 0);
					level.setDimensionSet(oldDimensionSetIndex);
				}
			}
		} else if(direction == Direction.RIGHT) {
			if(isCollision(Direction.RIGHT)) {
				level.setDimensionSet(newDimensionSetIndex);
				getBody().setTransform(getBody().getPosition().x + rightOffset, getBody().getPosition().y, 0);
				world.step(1/100, 1, 1);
				if(!isCollision(Direction.RIGHT)) {
					getBody().setTransform(oldPosition, 0);
					level.setDimensionSet(oldDimensionSetIndex);
				}
			}
		}
	}
	
	private boolean isCollision(final Direction direction) {
		Fixture collisionTestSensor = null;
		if(direction == Direction.LEFT) {
			collisionTestSensor = sensorPlayerLeft;
		} else if(direction == Direction.RIGHT) {
			collisionTestSensor = sensorPlayerRight;
		} else if(direction == Direction.UP) {
			//collisionTestSensor = sensorPlayerLeft;
		} else if(direction == Direction.DOWN) {
			//collisionTestSensor = sensorPlayerLeft;
		} else if(direction == Direction.NONE) {
			collisionTestSensor = sensorPlayerCenter;
		}
		
		for(Contact contact : world.getContactList()) {
			if(contact.isTouching() 
				&& (contact.getFixtureA() == collisionTestSensor
					|| contact.getFixtureB() == collisionTestSensor)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	public void setDimensonsSwitchDir(final Direction direction, final boolean allowed) {
		this.dimensonsSwitchDirAllowed.put(direction, allowed);
	}
	*/
	
	public void move(final Direction direction) {
		if(direction == Direction.LEFT && getLinearVelocity().x > -maxVelocity) {
			getBody().applyLinearImpulse(-2f,  0, getBody().getPosition().x, getBody().getPosition().y);
			this.direction = Direction.LEFT;
		}
		
		if(direction == Direction.RIGHT && getLinearVelocity().x < maxVelocity) {
			getBody().applyLinearImpulse(2f,  0, getBody().getPosition().x, getBody().getPosition().y);
			this.direction = Direction.RIGHT;
		}

		if(direction == Direction.UP) {
			this.direction = Direction.UP;
		}
		
		if(direction == Direction.DOWN) {
			this.direction = Direction.DOWN;
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
	
	private void loadPlayerInfos() {
		// TODO per levelLoader
	}
	
	public boolean isMove() { return isMovement; }
	
	public boolean isGround() { return isGround; }
	
	public Direction getDirection() { return direction; }
}



















