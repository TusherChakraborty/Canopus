/*  ---
 * 	Welcome to the 'Rings of Elements' code!
 *  ---	
 * 
 *	Directions describe the directions for the
 *	movements
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 09.10.2012
 * 
 * @lastChange: 09.10.2012
 * @Info: Added HeadCmt
 */

package de.datpixelstudio.statebasedgame;

public enum Direction
{	
	LEFT("left"),
	RIGHT("right"),
	UP("up"),
	DOWN("down"),
	NONE("none");
	
	private String value;
	
	private Direction(String value)
	{
		this.value = value;
	}
	
	public String getValue() { return value; }
}
