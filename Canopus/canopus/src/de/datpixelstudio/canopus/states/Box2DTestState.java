package de.datpixelstudio.canopus.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.Settings;
import de.datpixelstudio.statebasedgame.State;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public class Box2DTestState extends State {

	static final float WORLD_TO_BOX = 0.05f;
	static final float BOX_TO_WORLD = 500f;
	
	private World world = null;
	private Box2DDebugRenderer debugRenderer = null;
	
	private Body body = null;
	
	public Box2DTestState(int stateID, StateBasedGame sbg) {
		super(stateID, "Box2DTestState", sbg);
	}

	@Override
	public void init(GameContainer gc) {
		gc.glClearColor = Color.BLACK;
		gc.gameCam.update();
		gc.gameCam.zoom = WORLD_TO_BOX;
		gc.gameCam.update();
		
		world = new World(new Vector2(0, -10), true);
		debugRenderer = new Box2DDebugRenderer();
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(Settings.screenWidth() / 2, Settings.screenHeight() / 2);
		
		body = world.createBody(bodyDef);
		
		PolygonShape playerShape = new PolygonShape();
		playerShape.setAsBox(1, 2);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = playerShape;
		fixtureDef.density = 0.4f;
		fixtureDef.friction = 5f;
		fixtureDef.restitution = 0f;
		
		body.createFixture(fixtureDef);
		body.setFixedRotation(true);
		
		// Ground
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0, 290));
		
		Body groundBody = world.createBody(groundBodyDef);
		
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(Settings.screenWidth(), 1f);
		groundBody.createFixture(polygonShape, 0.0f);
	}

	@Override
	public void update(GameContainer gc) {
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			//body.applyForceToCenter(new Vector2(-20, 0));
			body.applyLinearImpulse(new Vector2(-1, 0), body.getLocalCenter());
		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			//body.applyForceToCenter(new Vector2(20, 0));
			body.applyLinearImpulse(new Vector2(1, 0), body.getLocalCenter());
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			if(body.getLinearVelocity().y == 0)
				//body.applyForceToCenter(new Vector2(0, 2000));
				body.applyLinearImpulse(new Vector2(0, 30), body.getLocalCenter());
			//body.applyLinearImpulse(new Vector2(0, 2), new Vector2(0.5f, 0.5f));
		}
		
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			body.applyForceToCenter(new Vector2(0, -50));
		}
		
		if(Gdx.input.isKeyPressed(Keys.R)) {
			body.setTransform(400, 300, 0);
		}
		
		world.step(1/60f, 6, 2);
	}

	@Override
	public void render(GameContainer gc) {
		debugRenderer.render(world, gc.gameCam.combined);
	}

	@Override
	public void resize(int width, int height, GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

}
