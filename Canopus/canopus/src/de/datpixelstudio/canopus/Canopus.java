/*  ---
 * 	Welcome to the 'Canopus' code!
 *  ---	
 * 
 *	Main class with the the game states
 *	inspired by Slick2D GameStates 
 * 
 * 	Game start date: 06.01.13
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 06.01.2013
 * 
 * @lastChange: 06.01.2013
 * @Info: creation
 */

package de.datpixelstudio.canopus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.datpixelstudio.canopus.states.EditorState;
import de.datpixelstudio.canopus.states.GameState;
import de.datpixelstudio.canopus.states.LoseState;
import de.datpixelstudio.canopus.states.MenueState;
import de.datpixelstudio.canopus.states.ScoreState;
import de.datpixelstudio.canopus.states.WinState;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public class Canopus extends StateBasedGame {
	
	public static final int MENUE_STATE_ID = 0;
	public static final int GAME_STATE_ID = 1;
	public static final int WIN_STATE_ID = 2;
	public static final int LOSE_STATE_ID = 3;
	public static final int SCORE_STATE_ID = 4;
	public static final int EDITOR_STATE_ID = 5;
	
	
	private final MenueState menueState = new MenueState(MENUE_STATE_ID, this);
	private final GameState gameState = new GameState(GAME_STATE_ID, this);
	private final WinState winState = new WinState(WIN_STATE_ID, this);
	private final LoseState loseState = new LoseState(LOSE_STATE_ID, this);
	private final ScoreState scoreState = new ScoreState(SCORE_STATE_ID, this);
	private final EditorState editorState = new EditorState(EDITOR_STATE_ID, this);
	
	/* Debug Font*/
	private static BitmapFont FONT = null;
	
	public Canopus() {
		super();
		initStateList();  
	}
	
	@Override
	public void initStateList() {
		super.initStateList();
		
		this.addState(menueState);
		this.addState(gameState);
		this.addState(winState);
		this.addState(loseState);
		this.addState(scoreState);
		this.addState(editorState);
		
		this.startStateID(MENUE_STATE_ID);
	}
	
	public static BitmapFont getFont() {
		if(FONT == null) {
			FONT = new BitmapFont(Gdx.files.internal("fonts/frutiger32.fnt"), 
					Gdx.files.internal("fonts/frutiger32.png"), false);
			FONT.setScale(0.5f);
			FONT.getRegion().getTexture().setFilter(TextureFilter.Linear, 
												TextureFilter.MipMap);
		}
		return FONT;
	}
}
