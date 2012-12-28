package de.datpixelstudio.canopus;

import de.datpixelstudio.canopus.states.MenueState;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public class Canopus extends StateBasedGame {
	
	public static final int MENUE_STATE_ID = 0;
	
	private final MenueState menueState = new MenueState(MENUE_STATE_ID, this);
	
	public Canopus() {
		super();
		initStateList();
		// FUsd
	}
	
	public void initStateList() {
		super.initStateList();
		
		this.addState(menueState);
		this.startStateID(MENUE_STATE_ID);
	}
 
}
