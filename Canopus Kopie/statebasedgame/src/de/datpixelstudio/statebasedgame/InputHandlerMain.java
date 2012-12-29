/*  ---
 * 	Welcome to the 'Rings of Elements' code!
 *  ---	
 * 
 *	Main inputHandler
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 20.09.2012
 * 
 * @lastChange: 23.09.2012
 * @Info: extends InputHandler
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
		/* Main Menue */
		if(keycode == Keys.F1)
		{
			state.closeState();
			state.enterState(0);
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
