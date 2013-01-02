package de.datpixelstudio.canopus.inputHandler;

import com.badlogic.gdx.Input.Keys;

import de.datpixelstudio.canopus.Player;
import de.datpixelstudio.canopus.states.Box2DTestState;
import de.datpixelstudio.statebasedgame.InputHandler;
import de.datpixelstudio.statebasedgame.State;

public class InputHandlerBox2DTestState extends InputHandler {

	private Box2DTestState state = null;
	private Player player = null;
	
	public InputHandlerBox2DTestState(final State state, final Player player) {
		super(state);
		this.state = (Box2DTestState) state;
		this.player = player;
	}
	
	 @Override
	 public boolean keyDown(int keycode) {
		 super.keyDown(keycode);
		 
		 if(keycode == Keys.UP) {
			 player.setJump(true);
			 player.setJump(true);
		 }
		 return false;
	 }
	 
	 @Override
	 public boolean keyUp(int keycode) {
		 super.keyDown(keycode);
		 
		 if(keycode == Keys.UP) {
			 player.setJump(false);
		 }
		 return false;
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
