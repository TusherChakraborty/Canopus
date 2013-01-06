package de.datpixelstudio.statebasedgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class SimpleCircle extends Circle implements SimpleShape {
	
	private static final long serialVersionUID = 7100457242554944557L;
	
	private Color color = null;

	public SimpleCircle(final float radius) {
		//TODO
	}
	
	public void drawDebug(final ShapeRenderer sR) {
		//TODO
	}
	
	public void setColor(final Color color) {
		this.color = color;
	}

	@Override
	public void setPosition(Vector2 position) {
	}

	@Override
	public void setPosition(float x, float y) {
	}
}
