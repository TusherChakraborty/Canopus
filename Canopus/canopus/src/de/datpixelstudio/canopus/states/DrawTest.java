package de.datpixelstudio.canopus.states;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

import de.datpixelstudio.canopus.LevelRectangles;
import de.datpixelstudio.canopus.VectorComparator;
import de.datpixelstudio.canopus.VerticePoints;
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
	
	public ArrayList<LevelRectangles> level = null;
	private ArrayList<Vector3> step = null;
	
	private TextureRegion pointTexture = null;
	
	private InputHandlerDraw handler = null;
	
	private Body mouseRecBody = null;
	private Fixture sensorFixtureMiddle = null;


	
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
		
		pointTexture = TextureSet.MISC_TEX.getTexture(1);
		
		handler = new InputHandlerDraw(this);
		addInput(handler);
		setInput();
		
		createWorld();
		
	}
	
	private void createWorld() {
		level = new ArrayList<LevelRectangles>();
		step = new ArrayList<Vector3>();
		
		//Maus Rectangle
		BodyDef mouseRecBodyDef = new BodyDef();
		mouseRecBodyDef.type = BodyType.KinematicBody;
		mouseRecBodyDef.position.set(0,0);
		mouseRecBody = world.createBody(mouseRecBodyDef);
		
		float mouseRecSize = 0.3f;
		
		PolygonShape mouseRecShape = new PolygonShape();
		Vector2[] vertices = {
				new Vector2(mouseRecSize,-mouseRecSize),
				new Vector2(mouseRecSize,mouseRecSize),
				new Vector2(-mouseRecSize,mouseRecSize),
				new Vector2(-mouseRecSize,-mouseRecSize)
		};
		mouseRecShape.set(vertices);
		
		sensorFixtureMiddle = mouseRecBody.createFixture(mouseRecShape, 0);
		
		mouseRecBody.setGravityScale(0);
		mouseRecBody.setBullet(true);
		mouseRecBody.setFixedRotation(true);
		
		//Levelrectangle
		Vector2[] vertices2 = {
				new Vector2(0+5+5,0+5),
				new Vector2(5+5+5,0+5),
				new Vector2(5+5+5,5+5),
				new Vector2(0+5+5,5+5)
		};
		LevelRectangles l = new LevelRectangles(vertices2, world,"postiv");
		VerticePoints v = new VerticePoints(l,this);
				
		level.add(l);

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
		
		// Debug
		debugRenderer.render(world, gc.gameCam.combined);
		
		gc.b.begin();
		
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
	
	//Add VerticeRadius to world
	public void verticePoints(Vector2[] addvertices){
		level.add(new LevelRectangles(addvertices, this.world,"postiv"));
	}
	
	public void getCoords(Vector3 mouse){
		this.getGameContainer().gameCam.unproject(mouse.set(mouse));
		System.out.println(mouse);
		mouseRecBody.setTransform(new Vector2(mouse.x,mouse.y), 0);
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
