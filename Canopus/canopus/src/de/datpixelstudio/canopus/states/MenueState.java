package de.datpixelstudio.canopus.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.datpixelstudio.statebasedgame.Direction;
import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.Settings;
import de.datpixelstudio.statebasedgame.State;
import de.datpixelstudio.statebasedgame.StateBasedGame;
import de.datpixelstudio.statebasedgame.TextureSet;

public class MenueState extends State{
//gleiche Zeile
	Texture testtex = null ;
	private Vector2 coordinates = null;
	private InputHandlerMaus bla = null;
	
	private TextureSet textureSet = null;

	public MenueState(int stateID, StateBasedGame sbg) {
		super(stateID, "MenueState", sbg);
	}
// U WILL C
	@Override
	public void init(GameContainer gc) {
		System.out.println("sup");
		
		bla = new InputHandlerMaus(this);
		addInput(bla);
		setInput();
				
		Settings.changeScreenSizeAllCams(gc, 800, 600, false);
		//CHANGE
		// CHANGE NOOBOOOOOOOOOOOOOOOOOOOOOOOOOOB
		textureSet = new TextureSet();
		textureSet.setCellSize(64);jh
		textureSet.loadTextureSet(Gdx.files.internal("assets/test_set.png"), 63); // fängt von 0 an
		
		coordinates = new Vector2(0,0);
		//testtex = new Texture(Gdx.files.internal("assets/libgdx.png"));
	}

	@Override
	public void update(GameContainer gc) {
		bla.update();
	}

	@Override
	public void render(GameContainer gc) {
		// TODO Auto-generated method stub
		gc.b.begin();
		gc.b.setProjectionMatrix(gc.gameCam.combined);
		//gc.b.draw(testtex, coordinates.x,coordinates.y);
		
		gc.b.draw(textureSet.getTexture(1), 10, 10);
		gc.b.draw(textureSet.getTexture(3), 100, 50);
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
