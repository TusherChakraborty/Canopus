package de.datpixelstudio.canopus.states;

import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.State;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public class MenueState extends State{

	public MenueState(int stateID, StateBasedGame sbg) {
		super(stateID, "MenueState", sbg);
	}

	@Override
	public void init(GameContainer gc) {
		System.out.println("sup");
	}

	@Override
	public void update(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height, GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

}
