package de.datpixelstudio.canopus;

import com.badlogic.gdx.math.Vector2;

import de.datpixelstudio.canopus.states.DrawTest;

public class VerticePoints {
	
	private LevelRectangles LevelRectangles = null;
	private Vector2[] vertices = null;
	private DrawTest state = null;
	
	public VerticePoints(LevelRectangles LevelRectangles, DrawTest state){
		this.LevelRectangles = LevelRectangles;
		this.vertices = LevelRectangles.getVertices();
		this.state = state;
		
		createRecs();
	}
	
	private void createRecs(){
		float verticeRadiusSize = 0.5f;
		for(Vector2 obj : vertices){
			Vector2[] addvertices = {
					new Vector2(obj.x + verticeRadiusSize,obj.y - verticeRadiusSize),
					new Vector2(obj.x + verticeRadiusSize,obj.y + verticeRadiusSize),
					new Vector2(obj.x - verticeRadiusSize,obj.y + verticeRadiusSize),
					new Vector2(obj.x - verticeRadiusSize,obj.y - verticeRadiusSize)
			};
			state.verticePoints(addvertices);
		}
	}
}
