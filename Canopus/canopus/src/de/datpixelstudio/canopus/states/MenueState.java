package de.datpixelstudio.canopus.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.datpixelstudio.statebasedgame.Direction;
import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.Settings;
import de.datpixelstudio.statebasedgame.State;
import de.datpixelstudio.statebasedgame.StateBasedGame;
import de.datpixelstudio.statebasedgame.TextureSet;

public class MenueState extends State {
	
	private Texture testtex = null ;
	private Vector2 coordinates = null;
	private InputHandlerMaus bla = null;
	
	public MenueState(int stateID, StateBasedGame sbg) {
		super(stateID, "MenueState", sbg);
	}
	
	@Override
	public void init(GameContainer gc) {
		bla = new InputHandlerMaus(this);
		addInput(bla);
		setInput();
				
		Settings.changeScreenSizeAllCams(gc, 800, 600, false);
		gc.glClearColor = Color.BLACK;
		testtex = new Texture(Gdx.files.internal("assets/libgdx.png"));
		
		coordinates = new Vector2(0,0);
	}

	@Override
	public void update(GameContainer gc) {
		bla.update();
	}

	@Override
	public void render(GameContainer gc) {
		gc.b.begin();
		gc.b.setProjectionMatrix(gc.gameCam.combined);
		gc.b.draw(testtex, coordinates.x,coordinates.y);
		
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
	
	public void move(Direction dir){
		float speed = 240;
		if(dir == Direction.LEFT) {
			coordinates.x = coordinates.x-speed * Gdx.graphics.getDeltaTime();
		}
		if(dir == Direction.RIGHT) {
			coordinates.x = coordinates.x+speed * Gdx.graphics.getDeltaTime();
		}
	}

}
