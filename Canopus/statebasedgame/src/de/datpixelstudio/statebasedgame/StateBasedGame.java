/*  ---
 * 	Welcome to the 'Rings of Elements' code!
 *  ---	
 * 
 *	StateBasedGame
 *	inspired by Slick2D GameStates 
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 16.09.2012
 * 
 * @lastChange: 16.09.2012
 * @Info:
 */

package de.datpixelstudio.statebasedgame;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public abstract class StateBasedGame implements ApplicationListener
{
	/* Contains the states */
	private HashMap<Number, State> states = null;
	private State activeState = null;
	
	private boolean isCreated = false;
	private static boolean gdxStarted = false; // after first init() created
	
	private String fps = null;
	
	/* Input handling */
	private InputMultiplexer inputMultiplexer = null;
	
	/* A clean Libgdx start... */
	private SetUpState setUpState = new SetUpState(-1, this);
	private int startStateID = 0;
	
	/* GameContainer */
	private GameContainer gc = null;
	
	public StateBasedGame() 
	{
		states = new HashMap<Number, State>();
		
		this.addState(setUpState);
		
		/* Input */
		inputMultiplexer = new InputMultiplexer();
		
		/* GameContainer */
		gc = new GameContainer();
	} 
	
	public void initStateList()
	{
		this.enterState(-1);
	}
	
	public void startStateID(final int stateID)
	{
		this.startStateID = stateID;
	}
	
	public void startState()
	{
		gdxStarted = true;
		this.enterState(startStateID);
	}
	
	public void addState(State state)
	{
		states.put(state.getStateID(), state);
	}
	
	public void enterState(final int stateID)
	{
		if(states.get(stateID) == null) {
			throw new IllegalArgumentException("\n StateBased: No state found with ID: " 
					+ stateID + ". State may be not initiated."); 
		}
		activeState = states.get(stateID);
		isCreated = true;
		
		if(activeState.isDisposed())
		{
			if(gdxStarted) Gdx.app.debug(activeState.getName(), "---Init---");
			activeState.init(gc);
		}
		if(gdxStarted) Gdx.app.debug(activeState.getName(), "---Resume---");
		activeState.resume(gc);
		if(gdxStarted) Gdx.app.debug(activeState.getName(), "---Update and Render...---");
		
		activeState.setActive(true);
	}
	
	private boolean isAStateActive()
	{
		if(activeState != null)
		{
			return true;
		}
		return false;
	}
	
	public String fps() { return fps; }
	
	public void setNewStateInput()
	{
		for(int i = 0; i < inputMultiplexer.size(); i++)
		{
			inputMultiplexer.clear();
		}
		
		Array<InputProcessor> inputProcessors = activeState.getInputProcessors();
		// first input go to the first processor
		
		if(inputProcessors.size > 0)
		{
			for(int i = 0; i < inputProcessors.size; i++)
			{
				inputMultiplexer.addProcessor(i, inputProcessors.get(i));
			}
		}
		inputMultiplexer.addProcessor(new InputHandlerMain(activeState));
		Gdx.input.setInputProcessor(inputMultiplexer);
	}
	
	@Override
	public void resize(int width, int height)
	{
		if(isAStateActive())
		{
			if(gdxStarted) Gdx.app.debug(activeState.getName(), "---Resize---");
			activeState.resize(width, height, gc);
			if(gdxStarted) Gdx.app.debug(activeState.getName(), "---Update and Render...---");
		}
	}
	
	// WARNING: create() is only for first state! (EmptyState)
	// look @enterState() 
	@Override
	public void create()
	{
		if(isAStateActive() && !isCreated)
		{
			activeState.init(gc);
			isCreated = false;
			activeState.setActive(true);
		}
	}

	@Override
	public void render()
	{
		if(gc.b == null)
		{
			gc.setOrthographicCameraGame(new OrthographicCamera());
			gc.gameCam.setToOrtho(false, Settings.screenWidth(), Settings.screenHeight());
			
			gc.setOrthographicCameraUI(new OrthographicCamera());
			gc.uiCam.setToOrtho(false, Settings.screenWidth(), Settings.screenHeight());
			
			gc.setSpriteBatch(new SpriteBatch());
			gc.b.setProjectionMatrix(gc.gameCam.combined);
			
			gc.setShapeRenderer(new ShapeRenderer());
			gc.sR.setProjectionMatrix(gc.gameCam.combined);
		}
		
		if(isAStateActive())
		{
			// Clear screen 
			Gdx.gl.glClearColor(gc.glClearColor.r, gc.glClearColor.g
					, gc.glClearColor.b, gc.glClearColor.a);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			// Update
			gc.setFps(fps());
			Align.update();
			activeState.update(gc);

			// Render
			activeState.render(gc);
		}
		//System.out.println(Gdx.app.getJavaHeap());
	}
	
	// WARNING: pause() is only for first state! (EmptyState) 
	// look @enterState() 
	@Override
	public void pause()
	{
		activeState.setActive(false);
		if(isAStateActive())
		{
			if(gdxStarted) Gdx.app.debug(activeState.getName(), "---Pause---");
			activeState.pause(gc);
		}
	}

	@Override
	public void resume()
	{
		activeState.setActive(true);
		if(isAStateActive())
		{
			if(gdxStarted) Gdx.app.debug(activeState.getName(), "---Resume---");
			activeState.resume(gc);
			if(gdxStarted) Gdx.app.debug(activeState.getName(), "---Update and Render...---");
		}
	}

	@Override
	public void dispose()
	{
		activeState.setActive(false);
		if(isAStateActive())
		{
			if(gdxStarted) Gdx.app.debug(activeState.getName(), "---Dispose---");
			activeState.dispose(gc);
			activeState.resetInput();
			activeState.markDisposed(true);
		}
	}
	
	public static void setGdxStarted()
	{
		gdxStarted = true;
	}
	
	public GameContainer getGameContainer() { return gc; }
}
