package de.datpixelstudio.canopus.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

import de.datpixelstudio.canopus.Canopus;
import de.datpixelstudio.canopus.LevelRectangles;
import de.datpixelstudio.canopus.Player;
import de.datpixelstudio.canopus.inputHandler.InputHandlerBox2DTestState;
import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.Settings;
import de.datpixelstudio.statebasedgame.State;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public class Box2DTestState extends State {

	static final float WORLD_TO_BOX = 0.05f;
	static final float BOX_TO_WORLD = 500f;
	
	private World world = null;
	private Box2DDebugRenderer debugRenderer = null;
	
	private Array<LevelRectangles> level = null;
	
	private Player player = null;
	
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
		
		createWorld();
		player = new Player(new Vector2(2, 8), world);
		
		addInput(new InputHandlerBox2DTestState(this, player));
		setInput();
	}
	
	private void createWorld() {
		level = new Array<LevelRectangles>();
		level.add(new LevelRectangles(new Vector2(0, 0), new Vector2(10, 1), false, world));
		level.add(new LevelRectangles(new Vector2(5, 1), new Vector2(1, 1), true, world));
	}

	@Override
	public void update(GameContainer gc) {
		player.update();
		
		world.step(1/60f, 6, 2);
	}

	@Override
	public void render(GameContainer gc) {
		gc.gameCam.position.set(player.getPostion().x, player.getPostion().y, 0);
		gc.gameCam.update();
		gc.b.setProjectionMatrix(gc.gameCam.combined);
		gc.b.begin();
		
		debugRenderer.render(world, gc.gameCam.combined);
		
		gc.b.setProjectionMatrix(gc.uiCam.combined);
		
		/* DebugText */
		Canopus.getFont().draw(gc.b, "isGround " + player.isPlayerGrounded(), 20, 20);
		
		gc.b.end();
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
