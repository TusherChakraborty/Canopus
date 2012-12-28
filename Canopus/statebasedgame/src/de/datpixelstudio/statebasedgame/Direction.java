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

package de.datpixelstudio.ringsofelements.utils;

public enum Direction
{	
	LEFT(0),
	RIGHT(1),
	UP(2),
	DOWN(3),
	NONE(4);
	
	private int value;
	
	private Direction(int value)
	{
		this.value = value;
	}
	
	public int getValue() { return value; }
}
