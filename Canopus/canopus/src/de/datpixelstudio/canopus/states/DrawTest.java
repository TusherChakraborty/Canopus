package de.datpixelstudio.canopus.states;

import java.util.ArrayList;
import java.util.Collections;

import sun.misc.GC;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
import de.datpixelstudio.canopus.VectorComparator;
import de.datpixelstudio.canopus.inputHandler.InputHandlerBox2DTestState;
import de.datpixelstudio.statebasedgame.Direction;
import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.Settings;
import de.datpixelstudio.statebasedgame.State;
import de.datpixelstudio.statebasedgame.StateBasedGame;
import de.datpixelstudio.statebasedgame.TextureSet;

public class DrawTest extends State {

	public static final float WORLD_TO_BOX = 0.05f;
	public static final float BOX_TO_WORLD = 500f;
	
	private World world = null;
	private Box2DDebugRenderer debugRenderer = null;
	
	private ArrayList<LevelRectangles> level = null;
	private ArrayList<Vector3> step = null;
	
	private TextureRegion pointTexture = null;
	
	private InputHandlerDraw handler = null;
	
	private float camX, camY;
	
	public DrawTest(int stateID, StateBasedGame sbg) {
		super(stateID, "DrawTest", sbg);
	}

	@Override
	public void init(GameContainer gc) {
				
		//gc.glClearColor = new Color(0.2f, 0, 0.5f, 1f);
		gc.glClearColor = Color.BLACK;
		gc.gameCam.zoom = WORLD_TO_BOX;
		gc.gameCam.update();
		
		world = new World(new Vector2(0, -10), true);
		debugRenderer = new Box2DDebugRenderer();
		
		pointTexture = TextureSet.MISC_TEX.getTexture(9);
		
		handler = new InputHandlerDraw(this);
		addInput(handler);
		setInput();
		
		createWorld();
		
	}
	
	private void createWorld() {
		level = new ArrayList<LevelRectangles>();
		step = new ArrayList<Vector3>();
		
	}

	@Override
	public void update(GameContainer gc) {	
		handler.update();
		world.step(1/60f, 6, 2);
		
		for(LevelRectangles obj : level) {
			obj.update();
		}
	}

	@Override
	public void render(GameContainer gc) {
		gc.gameCam.update();
		gc.gameCam.position.set(camX, camY, 0);
		gc.b.setProjectionMatrix(gc.gameCam.combined);
		
		debugRenderer.render(world, gc.gameCam.combined);
		
		gc.b.begin();	
		
		for(Vector3 obj : step) {
			gc.b.draw (pointTexture, obj.x - ((64 * 0.01f) / 2), 
				obj.y + 0.5f, 0, 0, 64, 64, 0.01f, 0.01f, 0);
		}
		
		gc.uiCam.update();
		gc.b.setProjectionMatrix(gc.uiCam.combined);
		
		gc.b.end();
	}

	public void moveCam(Direction dir){
		if(dir == Direction.LEFT){
			camX = camX - 0.1f;
		}
		else if(dir == Direction.RIGHT){
			camX = camX + 0.1f;
		}
		else if(dir == Direction.DOWN){
			camY = camY - 0.1f;
		}
		else if(dir == Direction.UP){
			camY = camY + 0.1f;
		}
	}
	
	public void coords(Vector3 mouse, OrthographicCamera cam){
		cam.unproject(mouse.set(mouse));
		step.add(new Vector3(mouse.x, mouse.y - 1f, 0));
		if(!step.isEmpty() && step.size() == 4){
			Collections.sort(step, new VectorComparator());
			Vector2[] vertices2 = {
					new Vector2(step.get(0).x, step.get(0).y),
					new Vector2(step.get(1).x, step.get(1).y),
					new Vector2(step.get(2).x, step.get(2).y),
					new Vector2(step.get(3).x, step.get(3).y)
			};
			level.add(new LevelRectangles(vertices2, world, "postiv"));
			step.clear();
		}
		System.out.println(mouse);
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
		gc.gameCam.zoom = 1f;
		gc.gameCam.position.set(Settings.screenWidth() / 2, Settings.screenHeight() / 2, 0);
	}
}
