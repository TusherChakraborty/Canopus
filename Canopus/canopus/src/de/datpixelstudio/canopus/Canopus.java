package de.datpixelstudio.canopus;

import de.datpixelstudio.canopus.states.MenueState;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public class Canopus extends StateBasedGame {
	
	public static final int MENUE_STATE_ID = 0; // Simple ID für die State ?
	
	private final MenueState menueState = new MenueState(MENUE_STATE_ID, this); // State erstellen
	
	public Canopus() {
		super(); // erben geht eher drum was es genau macht^^KK
		initStateList();  
	}
	
	public void initStateList() {
		super.initStateList(); // dito 
		
		this.addState(menueState); // EINFACH alle states adden (
		
		this.startStateID(MENUE_STATE_ID);
	}
 
}
