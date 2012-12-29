package de.datpixelstudio.canopus.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

import de.datpixelstudio.canopus.Canopus;
import de.datpixelstudio.statebasedgame.Direction;
import de.datpixelstudio.statebasedgame.InputHandler;
import de.datpixelstudio.statebasedgame.State;

public class InputHandlerMaus extends InputHandler{
	private MenueState datState = null;
	
	public InputHandlerMaus(State state) {
		super(state);
		datState = (MenueState) state;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean keyDown(int key) {
		if(key == Keys.F6) {
			datState.enterState(Canopus.BOX2D_TEST_STATE_ID);
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
		if(button == Buttons.LEFT){
			datState.move(Direction.LEFT);
		}
	return false;
	}
	
	public void update(){
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			datState.move(Direction.LEFT);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			datState.move(Direction.RIGHT);
		}
	}
}
