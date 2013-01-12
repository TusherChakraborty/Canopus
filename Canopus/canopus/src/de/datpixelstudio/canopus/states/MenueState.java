/*  ---
 * 	Welcome to the 'Canopus' code!
 *  ---	
 * 
 *	MenueState
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 06.01.2013
 * 
 * @lastChange: 06.01.2013
 * @Info: creation
 */

package de.datpixelstudio.canopus.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import de.datpixelstudio.canopus.inputHandler.InputHandlerMenue;
import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.State;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public class MenueState extends State {
	
	public MenueState(int stateID, StateBasedGame sbg) {
		super(stateID, "MenueState", sbg);
	}
	
	@Override
	public void init(GameContainer gc) {
		System.out.println(Gdx.files.internal("assets/textures/bw").exists());
		addInput(new InputHandlerMenue(this));
		setInput();
				
		gc.glClearColor = new Color(0.1f, 0.1f, 0.2f, 1);
	}

	@Override
	public void update(GameContainer gc) {
	}

	@Override
	public void render(GameContainer gc) {
		gc.b.begin();
		gc.gameCam.update();
		gc.b.setProjectionMatrix(gc.gameCam.combined);
		
		
		
		gc.b.end();
	}

	@Override
	public void resize(int width, int height, GameContainer gc) {
	}

	@Override
	public void pause(GameContainer gc) {
	}

	@Override
	public void resume(GameContainer gc) {
	}

	@Override
	public void dispose(GameContainer gc) {
		
	}
}
