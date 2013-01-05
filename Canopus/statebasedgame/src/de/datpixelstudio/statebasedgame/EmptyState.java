/*  ---
 * 	Welcome to the 'Rings of Elements' code!
 *  ---	
 * 
 *	EmptyState
 *
 *	NEVER CHANGE THIS CLASS!
 *	Provide a clean libgdx start.
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 17.09.2012
 * 
 * @lastChange: 17.09.2012
 * @Info:
 */

package de.datpixelstudio.statebasedgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.State;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public final class EmptyState extends State
{
	public EmptyState(final int stateID, final StateBasedGame sbg)
	{
		super(stateID, "EmptyState", sbg);
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
		Settings.changeScreenSizeAllCams(gc, 800, 600, false);
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
