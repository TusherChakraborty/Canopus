package de.datpixelstudio.canopus;

import de.datpixelstudio.canopus.states.Box2DTestState;
import de.datpixelstudio.canopus.states.MenueState;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public class Canopus extends StateBasedGame {
	
	public static final int MENUE_STATE_ID = 0;
	
	public static final int BOX2D_TEST_STATE_ID = 1;
	
	private final MenueState menueState = new MenueState(MENUE_STATE_ID, this); // State erstellen
	private final Box2DTestState box2dTestState = new Box2DTestState(BOX2D_TEST_STATE_ID, this);
	
	public Canopus() {
		super();
		initStateList();  
	}
	
	public void initStateList() {
		super.initStateList(); // dito 
		
		this.addState(menueState);
		this.addState(box2dTestState);
		
		this.startStateID(MENUE_STATE_ID);
	}
 
}
