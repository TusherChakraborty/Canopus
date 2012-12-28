/*  ---
 * 	Welcome to the 'Rings of Elements' code!
 *  ---	
 * 
 *	Helper for text/ content align
 *	update() @ StateBasedGame.class
 *	
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 03.09.2012
 * 
 * @lastChange: 20.10.2012
 * @Info: Lokale Variabeln zu Member Variabeln geändert + update()
 */

package de.datpixelstudio.statebasedgame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import de.datpixelstudio.statebasedgame.Settings;

public class Align
{
	private static int BUTTOM = 0;
	private static int TOP = Settings.screenHeight();
	private static int RIGHT = Settings.screenWidth();
	
	private static int CENTER = Settings.screenWidth() / 2;
	
	private static int STRING_HEIGHT;
	private static int STRING_LENGTH;
	
	private Align()  { }
	
	public static int stringAlignCenterWidth(final String string, final BitmapFont font)
	{
		STRING_LENGTH = (int) font.getBounds(string).width;
		
		return CENTER - (STRING_LENGTH / 2);
	}
	
	public static int stringAlignRight(final String string, final BitmapFont font)
	{
		STRING_LENGTH = (int) font.getBounds(string).width;
		
		return RIGHT - STRING_LENGTH;
	}
	
	public static int stringAlignLeft(final String string, final BitmapFont font) { return 0; }
	
	public static int stringAlignDown(final String string, final BitmapFont font)
	{
		STRING_HEIGHT = (int) font.getBounds(string).height;
		
		return BUTTOM + STRING_HEIGHT;
	}
	
	public static int stringAlignTop(final String string, final BitmapFont font)
	{
		STRING_HEIGHT  = (int) font.getBounds(string).height;
		
		return TOP - STRING_HEIGHT ;
	}
	
	public static void update()
	{
		TOP = Settings.screenHeight();
		RIGHT = Settings.screenWidth();
		
		CENTER = Settings.screenWidth() / 2;
	}
}
