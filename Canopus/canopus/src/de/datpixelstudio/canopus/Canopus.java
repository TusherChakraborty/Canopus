package de.datpixelstudio.canopus;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.datpixelstudio.canopus.states.Box2DTestState;
import de.datpixelstudio.canopus.states.DrawTest;
import de.datpixelstudio.canopus.states.MenueState;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public class Canopus extends StateBasedGame {
	
	public static final int MENUE_STATE_ID = 0;
	
	public static final int BOX2D_TEST_STATE_ID = 1;
	public static final int DRAWTEST_STATE_ID = 2;
	
	private final MenueState menueState = new MenueState(MENUE_STATE_ID, this); // State erstellen
	private final Box2DTestState box2dTestState = new Box2DTestState(BOX2D_TEST_STATE_ID, this);
	private final DrawTest box2DdrawTest = new DrawTest(DRAWTEST_STATE_ID, this);
	
	/* Generel Font */
	private static BitmapFont FONT = null;
	
	public Canopus() {
		super();
		initStateList();  
	}
	
	@Override
	public void initStateList() {
		super.initStateList(); // dito 
		
		this.addState(menueState);
		this.addState(box2dTestState);
		this.addState(box2DdrawTest);
		
		this.startStateID(MENUE_STATE_ID);
	}
	
	public static BitmapFont getFont() {
		if(FONT == null) {
			FONT = new BitmapFont(Gdx.files.internal("assets/fonts/frutiger32.fnt"), 
					Gdx.files.internal("assets/fonts/frutiger32.png"), false);
			FONT.setScale(0.5f);
			FONT.getRegion().getTexture().setFilter(TextureFilter.Linear, 
												TextureFilter.MipMap);
		}
		return FONT;
	}
}
