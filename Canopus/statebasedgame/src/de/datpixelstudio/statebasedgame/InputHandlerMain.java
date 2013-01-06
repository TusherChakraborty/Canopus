/*  ---
 * 	Welcome to the 'StateBasedGame' code!
 *  ---	
 * 
 *	Main inputHandler
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 20.09.2012
 * 
 * @lastChange: 06.01.2013
 * @Info: create Tab+Number state switch 
 */

package de.datpixelstudio.statebasedgame;

import com.badlogic.gdx.Input.Keys;

public class InputHandlerMain extends InputHandler
{
	public InputHandlerMain(final State state)
	{
		super(state);
	}
	
	@Override
	public boolean keyDown(int keycode)
	{
		/* Switch States with Tab+int
		 * If int not given go to first -> 0 
		 */
		if(isKeyHold(Keys.TAB))
		{
			if(keycode == Keys.NUM_1) {
				state.closeState();
				state.enterState(1);
			}
			
			if(keycode == Keys.NUM_2) {
				state.closeState();
				state.enterState(2);
			}
			
			if(keycode == Keys.NUM_3) {
				state.closeState();
				state.enterState(3);
			}
			
			if(keycode == Keys.NUM_4) {
				state.closeState();
				state.enterState(4);
			}
			
			if(keycode == Keys.NUM_5) {
				state.closeState();
				state.enterState(5);
			}
			
			if(keycode == Keys.NUM_6) {
				state.closeState();
				state.enterState(6);
			}
			return true;
		}
		
		/* Exit Game*/
		if(isKeyHold(Keys.A) && keycode == Keys.ESCAPE)
		{
			state.closeState();
			System.exit(0);
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
