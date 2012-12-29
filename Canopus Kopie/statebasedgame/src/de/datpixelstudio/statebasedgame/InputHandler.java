/*  ---
 * 	Welcome to the 'Rings of Elements' code!
 *  ---	
 * 
 *	InputHandler
 *	is keyDown() / .up() overrided you should use super.isKeyDown/Up()
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 23.09.2012
 * 
 * @lastChange: 02.11.2012
 * @Info: Added real isKeyPressed methode
 */

package de.datpixelstudio.statebasedgame;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public abstract class InputHandler implements InputProcessor
{
	protected State state = null;
	private HashMap<Integer, Boolean> isKeyPressedList = null;
	private HashMap<Integer, Boolean> isButtonPressedList = null;
	private boolean isKeyPressed = false, isButtonPressed = false;
	
	public InputHandler(final State state)
	{
		this.state = state;
		isKeyPressedList = new HashMap<Integer, Boolean>();
		isButtonPressedList = new HashMap<Integer, Boolean>();
	}
	
	public boolean keyDown(int keycode)
	{
		isKeyPressedList.put(keycode, Boolean.valueOf(true));
		
		return false;
	}
	
	public boolean isKeyPressed(final int key)
	{
		if(isKeyPressedList.containsKey(key))
		{
			isKeyPressed = isKeyPressedList.get(key).booleanValue();
			isKeyPressedList.remove(key);
		} else
		{
			isKeyPressed = false;
			
		}
		return isKeyPressed;
	}
	
	public boolean isKeyHold(final int key)
	{
		return Gdx.input.isKeyPressed(key);
	}

	public boolean keyUp(int keycode)
	{
		if(isKeyPressedList.containsKey(keycode))
		{
			isKeyPressedList.remove(keycode);
		}
		return false; 
	}

	public abstract boolean keyTyped(char character);

	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		isButtonPressedList.put(button, Boolean.valueOf(true));
		
		return false;
	}
	
	public boolean isButtonPressed(final int button)
	{
		if(isButtonPressedList.containsKey(button))
		{
			isButtonPressed = isButtonPressedList.get(button).booleanValue();
			isButtonPressedList.remove(button);
		} else
		{
			isButtonPressed = false;
			
		}
		return isButtonPressed;
	}

	public boolean isButtonHold(final int button)
	{
		return Gdx.input.isButtonPressed(button);
	}
	
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		if(isButtonPressedList.containsKey(button))
		{
			isButtonPressedList.remove(button);
		}
		return false;
	}

	public abstract boolean touchDragged(int screenX, int screenY, int pointer);

	public abstract boolean mouseMoved(int screenX, int screenY);

	public abstract boolean scrolled(int amount);
}
