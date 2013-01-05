package de.datpixelstudio.canopus.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

import de.datpixelstudio.canopus.Canopus;
import de.datpixelstudio.statebasedgame.Direction;
import de.datpixelstudio.statebasedgame.InputHandler;
import de.datpixelstudio.statebasedgame.State;

public class InputHandlerDraw extends InputHandler{
	private DrawTest datState = null;
	
	public InputHandlerDraw(State state) {
		super(state);
		datState = (DrawTest) state;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean keyDown(int key) {
		if(key == Keys.F6) {
			datState.closeState();
			datState.enterState(Canopus.BOX2D_TEST_STATE_ID);
		}
		if(key == Keys.F5) {
			datState.closeState();
			datState.enterState(Canopus.DRAWTEST_STATE_ID);
		}
		return false;
	}
	
	@Override
	public boolean keyTyped(char key) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if(Gdx.input.isTouched()){
			datState.coords(new Vector3(screenX,screenY,0), datState.getGameContainer().gameCam);
		}
		return false;
	}
	
	public void update(){
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			datState.moveCam(Direction.LEFT);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			datState.moveCam(Direction.RIGHT);
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			datState.moveCam(Direction.UP);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			datState.moveCam(Direction.DOWN);
		}
	}
}
