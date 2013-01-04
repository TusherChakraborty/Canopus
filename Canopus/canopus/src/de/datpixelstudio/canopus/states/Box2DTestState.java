package de.datpixelstudio.canopus.states;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

import de.datpixelstudio.canopus.Canopus;
import de.datpixelstudio.canopus.LevelRectangles;
import de.datpixelstudio.canopus.Player;
import de.datpixelstudio.canopus.inputHandler.InputHandlerBox2DTestState;
import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.State;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public class Box2DTestState extends State {

	public static final float WORLD_TO_BOX = 0.05f;
	public static final float BOX_TO_WORLD = 500f;
	
	private World world = null;
	private Box2DDebugRenderer debugRenderer = null;
	
	private ArrayList<LevelRectangles> level = null;
	
	private Player player = null;
	
	private Texture backgorund = null;
	private TextureRegion backgroundRegion = null;
	
	public Box2DTestState(int stateID, StateBasedGame sbg) {
		super(stateID, "Box2DTestState", sbg);
	}

	@Override
	public void init(GameContainer gc) {
		//gc.glClearColor = new Color(0.2f, 0, 0.5f, 1f);
		gc.glClearColor = Color.BLACK;
		gc.gameCam.zoom = WORLD_TO_BOX;
		gc.gameCam.update();
		
		world = new World(new Vector2(0, -10), true);
		debugRenderer = new Box2DDebugRenderer();
		
		createWorld();
		player = new Player(new Vector2(2, 10), world, level);
		
		backgorund = new Texture(Gdx.files.internal("assets/textures/level/level1.png"));
		backgroundRegion = new TextureRegion(backgorund, 0, 0, 1024, 500);
		
		addInput(new InputHandlerBox2DTestState(this, player));
		setInput();
	}
	
	private void createWorld() {
		level = new ArrayList<LevelRectangles>();
		level.add(new LevelRectangles(new Vector2(0, 0), new Vector2(10, 1), false, world, "positiv"));
		level.add(new LevelRectangles(new Vector2(-1, -1), new Vector2(10, 1), false, world, "negativ"));
		//Boxcreation loop
		float j = -5;
		float j2 = 0.8f;
		for(int i = -9; i <= 2; i++){
			j+= 0.15f;
			j2 += 0.05f;
			level.add(new LevelRectangles(new Vector2(j,j2), new Vector2(0.1f, 0.1f), true, world, "positiv"));
			if(j > 4){
				j = 0;
			}
		}
		// walls
		level.add(new LevelRectangles(new Vector2(-9, 11), new Vector2(1, 10), false, world, "postiv"));
		//level.add(new LevelRectangles(new Vector2(7, 11), new Vector2(1, 10), false, world, "positiv"));
		//level.add(new LevelRectangles(new Vector2(9, 11), new Vector2(1, 10), false, world, "negativ"));
		level.add(new LevelRectangles(new Vector2(10, 11), new Vector2(1, 10), false, world, "positv"));
		//top
		level.add(new LevelRectangles(new Vector2(0, 22), new Vector2(10, 1), false, world, "postiv"));
		// Rectangle
		//level.add(new LevelRectangles(world));
		/*
		Vector2[] vertices1 = {
				new Vector2(0,0),
				new Vector2(5,0),
				new Vector2(5,5),
				new Vector2(0,5)
		};
		level.add(new LevelRectangles(vertices1, world, "postiv"));
		*/
		Vector2[] vertices2 = {
				new Vector2(0+5,0),
				new Vector2(5+5,0),
				new Vector2(5+5,5),
				new Vector2(0+5,5)
		};
		level.add(new LevelRectangles(vertices2, world, "negativ"));
		
		Vector2[] vertices3 = {
				new Vector2(0+5+5,0),
				new Vector2(5+5+5,0),
				new Vector2(5+5+5,5),
				new Vector2(0+5+5,5)
		};
		level.add(new LevelRectangles(vertices3, world, "postiv"));
		/*
		Vector2[] vertices4 = {
				new Vector2(0,0+5),
				new Vector2(5,0+5),
				new Vector2(5,5+5),
				new Vector2(0,5+5)
		};
		level.add(new LevelRectangles(vertices4, world, "postiv"));
		*/
		Vector2[] vertices5 = {
				new Vector2(0+5,0+5),
				new Vector2(5+5,0+5),
				new Vector2(5+5,5+5),
				new Vector2(0+5,5+5)
		};
		level.add(new LevelRectangles(vertices5, world, "postiv"));
		
		Vector2[] vertices6 = {
				new Vector2(0+5+5,0+5),
				new Vector2(5+5+5,0+5),
				new Vector2(5+5+5,5+5),
				new Vector2(0+5+5,5+5)
		};
		level.add(new LevelRectangles(vertices6, world,"postiv"));
	}

	@Override
	public void update(GameContainer gc) {
		player.update();
		
		for(LevelRectangles obj : level) {
			obj.update();
		}
		
		world.step(1/60f, 6, 2);
	}

	@Override
	public void render(GameContainer gc) {
		gc.gameCam.position.set(player.getPostion().x, player.getPostion().y, 0);
		gc.gameCam.update();
		gc.b.setProjectionMatrix(gc.gameCam.combined);
		gc.b.begin();
		
		gc.b.draw (backgroundRegion, -20, -20, 0, 0, 
				backgroundRegion.getRegionWidth(), backgroundRegion.getRegionHeight(), Box2DTestState.WORLD_TO_BOX * 1.2f, Box2DTestState.WORLD_TO_BOX * 2.2f, 0);
		//gc.b.draw(backgorund, 0, 0);
		
		debugRenderer.render(world, gc.gameCam.combined);
		player.draw(gc.b);
		
		gc.uiCam.update();
		gc.b.setProjectionMatrix(gc.uiCam.combined);
		/* DebugText */
		Canopus.getFont().draw(gc.b, "isGround " + player.isPlayerGrounded(), 20, 20);
		Canopus.getFont().draw(gc.b, "isJump " + player.isJump(), 20, 40);
		Canopus.getFont().draw(gc.b, "friction " + player.getFriction(), 20, 60);
		Canopus.getFont().draw(gc.b, "fps " + Gdx.graphics.getFramesPerSecond(), 20, 80);
		
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
