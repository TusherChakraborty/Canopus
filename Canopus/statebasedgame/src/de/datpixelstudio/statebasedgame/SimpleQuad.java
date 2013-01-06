/*  ---
 * 	Welcome to the 'StateBasedGame' code!
 *  ---	
 * 
 *	Quad class extend the rectangle class
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 22.09.2012
 * 
 * @lastChange: 06.01.2013
 * @Info: Changed name
 */

package de.datpixelstudio.statebasedgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class SimpleQuad extends Rectangle
{
	private static final long serialVersionUID = 6195916908170738164L;
	
	private boolean isZero = true;
	private Color color = Color.YELLOW;
	
	private Vector2 pos = null;
	private Vector2 size = null;

	public SimpleQuad(final Vector2 pos, final Vector2 size)
	{
		super(pos.x, pos.y, size.x, size.y);
		isZero = false;
		
		this.pos = pos;
		this.size = size;
	}
	
	public SimpleQuad(final Vector2 pos, final int size)
	{
		super(pos.x, pos.y, size, size);
		isZero = false;
		
		this.pos = pos;
		this.size = new Vector2(size, size);
	}
	
	public SimpleQuad(final Vector2 size)
	{
		super(0, 0, size.x, size.y);
		isZero = false;
		
		this.pos = Vector2.Zero;
		this.size = size;
	}
	
	public SimpleQuad()
	{
		super();
		
		this.pos = Vector2.Zero;
		this.size = Vector2.Zero;
	}
	
	/* First coords Quad last two point to check */
	public static boolean PointInAQuad(final float x, final float y,
			final float x2, final float y2, final float x3, final float y3) {
		if(x3 > x && x3 < x2
				&& y3 > y && y3 < y2) {
			return true;
		}
		return false;
	}
	
	public void drawCenter(final ShapeRenderer sR)
	{
		sR.begin(ShapeType.Rectangle);
		sR.setColor(color);
		sR.rect(centerX(x), centerY(y), width, height);
		sR.end();
	}
	
	public void drawDebug(final ShapeRenderer sR)
	{
		sR.begin(ShapeType.Rectangle);
		sR.setColor(color);
		sR.rect(x, y, width, height);
		sR.end();
	}
	
	public void setColor(final Color color)
	{
		this.color = color;
	}
	
	public boolean isZero() { return isZero; }
	
	public Vector2 getPos() { return pos; }
	public Vector2 getSize() { return size; }
	
	public void setPosition(final float x, final float y)
	{
		this.x = x;
		this.y = y;
		
		this.pos.x = x;
		this.pos.y = y;
	}
	
	public void setPosition(final Vector2 pos)
	{
		this.x = pos.x;
		this.y = pos.y;
		
		this.pos = pos;
	}
	
	public void setSize(final Vector2 size)
	{
		this.width = size.x;
		this.height = size.y;
		
		this.size = size;
	}
	
	private float centerX(final float x)
	{
		return x - (width / 2);
	}
	
	private float centerY(final float y)
	{
		return y - (height / 2);
	}
}
