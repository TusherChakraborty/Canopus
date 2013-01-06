package de.datpixelstudio.statebasedgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public interface SimpleShape {
	public void setColor(final Color color);
	public void setPosition(final Vector2 position);
	public void setPosition(final float x, final float y);
	
	public void drawDebug(final ShapeRenderer sR);
}
