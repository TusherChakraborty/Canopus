/*  ---
 * 	Welcome to the 'Canopus' code!
 *  ---	
 * 
 *	GameObject
 *	Not sure ob bei Erstellung überprüft wird, ob bereits 
 *	simpleShape eine andere Form hat. 
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 06.01.2013
 * 
 * @lastChange: 06.01.2013
 * @Info: creation
 */

package de.datpixelstudio.canopus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;

import de.datpixelstudio.statebasedgame.SimpleCircle;
import de.datpixelstudio.statebasedgame.SimpleQuad;
import de.datpixelstudio.statebasedgame.SimplePolygon;
import de.datpixelstudio.statebasedgame.SimpleShape;

public class GameObject {
	
	private Type type = null;
	private boolean isSimpleShape = true;
	private Body body = null;
	private Array<Fixture> fixtures = null;
	
	private PolygonShape polygonShape = null;
	private SimpleQuad simpleQuad = null;
	private SimplePolygon simplePolygon = null;
	private CircleShape circleShape = null;
	private SimpleCircle simpleCircle = null;
	private SimpleShape simpleShape = null;
	
	private Vector2 position = null;
	//private Vector2 size = null;
	
	public enum Type {
		NON_PHYISC("nonPhysic"), STATIC("static"), KINEMATIC("kinematic"), DYNAMIC("dynamic");
		private String value;
		private Type(String value) { this.value = value; }
		public String getValue() { return value; }
	}
	
	public GameObject() {
		this.position = new Vector2();
	}
	
	public void setType(final Type type, final boolean simpleShape) {
		this.type = type;
		this.isSimpleShape = simpleShape;
		
		if(type != Type.NON_PHYISC) {
			fixtures = new Array<Fixture>();
		}
	}
	
	
	public void setAsCircle(final float radius) {
		//TODO
	}
	
	public void setPolygonVertices(final Vector2[] vertices) {
		
	}
	
	public void setAsBox(final Vector2 size) {
		if(type == null) throw new IllegalStateException("No type given for GameObject");
		
		
		if(type != Type.NON_PHYISC) {
			polygonShape = new PolygonShape();
			Vector2[] vertices = {
					new Vector2(0, 0),
					new Vector2(size.x, 0),
					new Vector2(size.x, size.y),
					new Vector2(0, size.y)
			};
			polygonShape.set(vertices);
		}
		
		if(isSimpleShape) {
			simpleQuad = new SimpleQuad(size);
			simpleShape = simpleQuad;
		}
	}
	
	/* Only for simpleShapes */
	public void setSimpleColor(final Color color) {
		if(isSimpleShape && simpleShape != null) {
			simpleShape.setColor(color);
		}
	}
	
	/* Only for simpleShapes */
	public void drawDebug(final ShapeRenderer sR) {
		if(isSimpleShape && simpleShape != null) {
			simpleShape.drawDebug(sR);
		}
	}
	
	public void draw(final SpriteBatch b) {
		//TODO Texture
	}
	
	public void setPosition(final Vector2 position) {
		this.position = position;
		
		if(isSimpleShape && simpleShape != null) {
			simpleShape.setPosition(position);
		}
	}
	
	public Vector2 getSize() {
		//TODO
		return null;
	}
	
	/* Methode only for physic objects needed */
	public void create(final World world) {
		if(type == Type.NON_PHYISC) 
			throw new IllegalStateException("Type is non-physic! World parameter must be given.");
		
		BodyDef bodyDef = new BodyDef();
		if(type == Type.STATIC) {
			bodyDef.type = BodyType.StaticBody;
			body = world.createBody(bodyDef);
			//body.setUserData(this); //TODO TEST
			fixtures.add(body.createFixture(polygonShape, 0.0f));
			
			polygonShape.dispose();
		}
		if(type == Type.KINEMATIC) {
			bodyDef.type = BodyType.DynamicBody;
			
		}
		if(type == Type.DYNAMIC) {
			bodyDef.type = BodyType.DynamicBody;
			
		}
		//TODO DAT REST MACHEN
		
		//if(isSimpleShape) create();
	}

	/*
	public void create() {
		if(!isSimpleShape) throw new IllegalStateException("GameObject has no simpleShape. Use only create(world);");
		
		if(simpleQuad != null) {
			simpleShape = simpleQuad;
		}
	}
	*/
	
	public void dispose() {
		//TODO
	}
}














