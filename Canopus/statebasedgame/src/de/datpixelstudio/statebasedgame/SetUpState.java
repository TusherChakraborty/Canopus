/*  ---
 * 	Welcome to the 'StateBasedGame' code!
 *  ---	
 * 
 *	EmptyState
 *
 *	Handle with care!
 *	Provide a clean libgdx start.
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 17.09.2012
 * 
 * @lastChange: 06.01.2013
 * @Info: changeScreenSize correct per main class
 */

package de.datpixelstudio.statebasedgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.State;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public final class SetUpState extends State
{
	public SetUpState(final int stateID, final StateBasedGame sbg)
	{
		super(stateID, "SetUpState", sbg);
	}

	@Override
	public void init(final GameContainer gc) { }

	@Override
	public void update(final GameContainer gc) {}

	@Override
	public void render(final GameContainer gc)
	{
		Gdx.app.setLogLevel(Logger.DEBUG);
		StateBasedGame.setGdxStarted();
		Settings.changeScreenSizeAllCams(gc, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Gdx.graphics.isFullscreen());
		pause(gc);
		dispose(gc);
		enterStartState();
	}
	
	public void resize(final int width, final int height, final GameContainer gc) {}

	@Override
	public void pause(final GameContainer gc) {}

	@Override
	public void resume(final GameContainer gc) {}

	@Override
	public void dispose(final GameContainer gc) { }
}
