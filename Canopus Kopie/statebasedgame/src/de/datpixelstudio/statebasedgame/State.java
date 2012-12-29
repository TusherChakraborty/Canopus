/*  ---
 * 	Welcome to the 'Rings of Elements' code!
 *  ---	
 * 
 *	State
 *
 *	To change the state ALWAYS
 *	first pause()/ pause() + dispose() then enterState()
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 16.09.2012
 * 
 * @lastChange: 16.09.2012
 * @Info:
 */

package de.datpixelstudio.statebasedgame;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;

public abstract class State
{
	private final int stateID;
	private final String stateName;
	private boolean isActive = false, isDisposed = true;

	private Array<InputProcessor> inputProcessors = null;
	
	private StateBasedGame sbg = null;
	
	public State(final int stateID, final String stateName, final StateBasedGame sbg)
	{
		this.stateID = stateID;
		this.stateName = stateName;
		this.sbg = sbg;
		
		inputProcessors = new Array<InputProcessor>();
	}
	
	/* State based */
	public int getStateID() { return stateID; }
	
	public void setActive(final boolean isActive)
	{
		this.isActive = isActive;
	}
	public boolean isActive() { return isActive; }
	
	public void enterState(final int stateID)
	{
		this.isActive = false;
		sbg.enterState(stateID);
	}
	
	public void closeState()
	{
		this.isActive = false;
		sbg.pause();
		markDisposed(true);
		sbg.dispose();
	}
	
	public void pauseState()
	{
		this.isActive = false;
		sbg.pause();
	}
	
	public void enterStartState()
	{
		sbg.startState();
	}
	
	public void markDisposed(final boolean isDisposed)
	{
		this.isDisposed = isDisposed;
	}
	
	public boolean isDisposed() { return isDisposed; }
	
	/* Input handler 
	 *  
	 * It will only attach the main input if processor is null
	 */
	public void resetInput()
	{
		inputProcessors.clear();
		setInput();
	}
	
	public void addInput(final InputProcessor inputProcessor)
	{
		inputProcessors.add(inputProcessor);
	}
	
	public void setInput()
	{
		sbg.setNewStateInput();
	}
	
	public Array<InputProcessor> getInputProcessors() { return inputProcessors; } // return LIST
	
	/* Game Logic */
	public abstract void init(final GameContainer gc);
	public abstract void update(final GameContainer gc);
	public abstract void render(final GameContainer gc);
	
	/* Android specific */
	public abstract void resize(final int width, final int height, final GameContainer gc);
	public abstract void pause(final GameContainer gc);
	public abstract void resume(final GameContainer gc);
	public abstract void dispose(final GameContainer gc);
	
	/* Other */
	public GameContainer getGameContainer() { return sbg.getGameContainer(); }
	public String getName() { return stateName; }
}
