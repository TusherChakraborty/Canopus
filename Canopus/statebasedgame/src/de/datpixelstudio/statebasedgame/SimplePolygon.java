package de.datpixelstudio.statebasedgame;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class SimplePolygon extends Polygon implements SimpleShape {

	private Color color = Color.YELLOW;
	
	public SimplePolygon(float[] vertices) {
		super(vertices);
	}
	
	/* TODO USE STACK */
	public void drawDebug(final ShapeRenderer sR) {
		sR.begin(ShapeType.Line);
		sR.setColor(color);
		
		ArrayList<Vector2> vertices = new ArrayList<Vector2>();
		for(int i = 0; i < getVertices().length; i++) {
			vertices.add(new Vector2(getVertices()[i], getVertices()[i++]));
		}
		for(int i = 0; i < vertices.size(); i++) {
			if(vertices.get(i++) == null) {
				sR.line(vertices.get(i).x, vertices.get(i).y, 
						vertices.get(0).x, vertices.get(0).y);
			} else {
				sR.line(vertices.get(i).x, vertices.get(i).y, 
						vertices.get(i++).x, vertices.get(i++).y);
			}
			if(vertices.get(i--) != null) i--;
		}
		
		sR.end();
	}
	
	public void setColor(final Color color) {
		this.color = color;
	}

	@Override
	public void setPosition(Vector2 position) {
	}
}
