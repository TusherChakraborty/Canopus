package de.datpixelstudio.canopus.states;

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

	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_TO_WORLD = 100f;
	
	private World world = null;
	private Box2DDebugRenderer debugRenderer = null;
	
	
	public Box2DTestState(int stateID, StateBasedGame sbg) {
		super(stateID, "Box2DTestState", sbg);
	}

	@Override
	public void init(GameContainer gc) {
		gc.glClearColor = Color.BLACK;
		
		world = new World(new Vector2(0, -50), true);
		debugRenderer = new Box2DDebugRenderer();
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(Settings.screenWidth() / 2, Settings.screenHeight() / 2);
		
		Body body = world.createBody(bodyDef);
		
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(30);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		fixtureDef.density = 0.8f;
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0.6f;
		
		
		
		// Ground
		
		body.createFixture(fixtureDef);
		
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0, 10));
		
		Body groundBody = world.createBody(groundBodyDef);
		
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(Settings.screenWidth(), 10f);
		groundBody.createFixture(polygonShape, 0.0f);
	}

	@Override
	public void update(GameContainer gc) {

		world.step(1/45f, 6, 2);
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
