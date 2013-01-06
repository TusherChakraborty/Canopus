/*  ---
 * 	Welcome to the 'Canopus' code!
 *  ---	
 * 
 *	GameObject
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 06.01.2013
 * 
 * @lastChange: 06.01.2013
 * @Info: creation
 */

package de.datpixelstudio.canopus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import de.datpixelstudio.statebasedgame.SimpleCircle;
import de.datpixelstudio.statebasedgame.SimpleQuad;
import de.datpixelstudio.statebasedgame.SimplePolygon;

public class GameObject {
	
	private Type type = null;
	private boolean simpleShape = true;
	private Body body = null;
	private Array<Fixture> fixtures = null;
	
	private PolygonShape polygonShape = null;
	private SimpleQuad simpleQuad = null;
	private SimplePolygon simplePolygon = null;
	private CircleShape circleShape = null;
	private SimpleCircle simpleCircle = null;
	
	private Vector2 position = null;
	
	private World world = null;
	
	public enum Type {
		NON_PHYISC("nonPhysic"), STATIC("static"), KINEMATIC("kinematic"), DYNAMIC("dynamic");
		private String value;
		private Type(String value) { this.value = value; }
		public String getValue() { return value; }
	}
	
	public GameObject(final World world) {
		this.position = new Vector2();
		this.world = world;
		
		fixtures = new Array<Fixture>();
	}
	
	public void setType(final Type type, final boolean simpleShape) {
		this.type = type;
		this.simpleShape = simpleShape;
	}
	
	public void setAsCircle(final float radius) {
		//TODO
	}
	
	public void setAsBox(final Vector2 size) {
		if(type == null) throw new IllegalStateException("No type given for GameObject");
		if(circleShape != null) 
			throw new IllegalStateException("Can't create polygon. GameObject is already not a polygon");
		
		if(type != Type.NON_PHYISC) {
			polygonShape = new PolygonShape();
			Vector2[] vertices = {
					new Vector2(0, 0),
					new Vector2(0, size.y),
					new Vector2(size.x, size.y),
					new Vector2(size.x, 0)
			};
			polygonShape.set(vertices);
		}
		
		if(simpleShape) {
			float[] singleVertices = {
					0, 0,
					0, size.y,
					size.x, size.y,
					size.x, 0
			};
			simplePolygon = new SimplePolygon(singleVertices);
		}
	}
	
	public void drawDebug(final ShapeRenderer sR) {
		if(simpleShape) {
			if(simplePolygon != null) simplePolygon.drawDebug(sR);
			if(simpleQuad != null) simpleQuad.drawDebug(sR);
			if(simpleCircle != null) simpleCircle.drawDebug(sR);
		}
	}
	
	public void draw(final SpriteBatch b) {
		//TODO Texture
	}
	
	public void setPosition(final Vector2 position) {
		this.position = position;
	}
	
	public void create() {
		
	}
	
	public void dispose() {
		//TODO
	}
}














