/*  ---
 * 	Welcome to the 'Canopus' code!
 *  ---	
 * 
 *	GameObject
 *	Not sure ob bei Erstellung überprüft wird, ob bereits 
 *	simpleShape eine andere Form hat. 
 *
 *	-> Neue Formen erstellen neue Fixtures für body
 *	   um so komplexe Figuren erstellen zu können.
 *
 *	TODO ShapeTypes enum erleichtet die konsistenz Prüfung
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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	
	protected World world = null;
	
	private Type type = null;
	private boolean isSimpleShape = true;
	private Body body = null;
	private Array<Fixture> fixtures = null;
	
	private TextureRegion textureRegion = null;
	
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
	
	public GameObject(final World world) {
		this.world = world;
		this.position = new Vector2();
	}
	
	public void setType(final Type type, final boolean simpleShape) {
		this.type = type;
		this.isSimpleShape = simpleShape;
		
		if(type != Type.NON_PHYISC) {
			fixtures = new Array<Fixture>();
			BodyDef bodyDef = new BodyDef();
			if(type == Type.STATIC) {
				bodyDef.type = BodyType.StaticBody;
			}
			if(type == Type.DYNAMIC) {
				bodyDef.type = BodyType.DynamicBody;
			}
			body = world.createBody(bodyDef);
		}
	}
	
	public void setDensity(final float density) {
		if(type != Type.DYNAMIC) throw new IllegalStateException("Type is non-Physic");
		for(Fixture fixture : fixtures) {
			fixture.setDensity(density);
		}
	}
	
	public void setFriction(final float friction) {
		if(type != Type.DYNAMIC) throw new IllegalStateException("Type is non-Physic");
		for(Fixture fixture : fixtures) {
			fixture.setFriction(friction);
		}
	}
	
	public void setRestitution(final float restitiution) {
		if(type != Type.DYNAMIC) throw new IllegalStateException("Type is non-Physic");
		for(Fixture fixture : fixtures) {
			fixture.setRestitution(restitiution);
		}
	}
	
	public void setAsCircle(final float radius, final Vector2 position) {
		if(type == null) throw new IllegalStateException("No type given for GameObject");
		
		if(type != Type.NON_PHYISC) {
			circleShape = new CircleShape();
			circleShape.setRadius(radius);
			circleShape.setPosition(position);
			fixtures.add(body.createFixture(circleShape, 1f));
		}
	}
	
	public void setPolygonVertices(final Vector2[] vertices) {
		if(type == null) throw new IllegalStateException("No type given for GameObject");
		
		if(type != Type.NON_PHYISC) {
			polygonShape = new PolygonShape();
			polygonShape.set(vertices);
			fixtures.add(body.createFixture(polygonShape, 1f));
		}
		
		if(isSimpleShape) {
			float[] verticesFloat = new float[vertices.length * 2];
			for(int i = 0; i < vertices.length; i++) {
				verticesFloat[i] = vertices[i].x;
				verticesFloat[i++] = vertices[i].y;
			}
			System.out.println(verticesFloat.length + " input: " + vertices.length);
			simplePolygon = new SimplePolygon(verticesFloat);
			simpleShape = simplePolygon;
		}
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
			fixtures.add(body.createFixture(polygonShape, 1f));
		}
		
		if(isSimpleShape) {
			simpleQuad = new SimpleQuad(size);
			simpleShape = simpleQuad;
		}
	}
	
	public void addFixture(final Fixture fixture) {
		this.fixtures.add(fixture);
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
	
	public void setTexture(final TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
	}
	
	public void draw(final SpriteBatch b) {
		if(textureRegion != null)
		b.draw(textureRegion, body.getTransform().getPosition().x, 
				body.getTransform().getPosition().y);
	}
	
	public void setPositionSimpleShape(final Vector2 position) {
		this.position = position;
		
		if(isSimpleShape && simpleShape != null) {
			simpleShape.setPosition(position);
		}
	}
	
	public void setPositionBody(final Vector2 position, final float angle) {
		this.position = position;
		
		if(body != null) {
			body.setTransform(position, angle);
		}
	}
	
	public void setFixedRotation(final boolean isFixed) {
		body.setFixedRotation(isFixed);
	}
	
	public Vector2 getSize() {
		//TODO
		return null;
	}
	
	public Body getBody() { return body; }
	
	public Vector2 getLinearVelocity() {
		return body.getLinearVelocity();
	}
	
	/* Only first fixture */
	public float getFriction() {
		return body.getFixtureList().get(0).getFriction();
	}
	
	/* Methode only for physic objects needed */
	public void create(final World world) {
		if(type == Type.NON_PHYISC) 
			throw new IllegalStateException("Type is non-physic! World parameter must be deleted.");
		
		if(polygonShape != null) {
			polygonShape.dispose();
		}
		
		if(circleShape != null) {
			circleShape.dispose();
		}
		
		/*
		if(type == Type.KINEMATIC) {
			bodyDef.type = BodyType.DynamicBody;
			
		}
		if(type == Type.DYNAMIC) {
			bodyDef.type = BodyType.DynamicBody;
			
		}
		*/
		//TODO DAT REST MACHEN
		
		//if(isSimpleShape) create();
	}

	// TODO
	public void create() {
		if(!isSimpleShape) throw new IllegalStateException("GameObject has no simpleShape. Use only create(world);");
		
		if(simpleQuad != null) {
			simpleShape = simpleQuad;
		}
	}
	
	public void dispose() {
		//TODO
	}
}














