package de.datpixelstudio.statebasedgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class SimplePolygon extends Polygon implements SimpleShape {

	private Color color = null;
	
	public SimplePolygon(float[] vertices) {
		super(vertices);
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
}
