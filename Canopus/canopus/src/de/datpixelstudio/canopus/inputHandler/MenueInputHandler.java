package de.datpixelstudio.canopus.inputHandler;

import com.badlogic.gdx.Gdx;

import de.datpixelstudio.canopus.Canopus;
import de.datpixelstudio.statebasedgame.InputHandler;
import de.datpixelstudio.statebasedgame.State;

public class MenueInputHandler extends InputHandler {

	public MenueInputHandler(final State state) {
		super(state);
	}
	
	public void update() {
		if(Gdx.input.isTouched()) {
			state.closeState();
			state.enterState(Canopus.GAME_STATE_ID);
		}
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
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

}
