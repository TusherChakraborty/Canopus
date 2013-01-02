package de.datpixelstudio.canopus;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class LevelRectangles {
	
	private Vector2 position = null;
	private Vector2 size = null;
	
	private World world;
	private Body body = null;
	private Fixture fixture = null;
	
	public LevelRectangles(final Vector2 position, final Vector2 size, final boolean isDynamic ,World world) {
		this.position = position;
		this.size = size;
		this.world = world;
		
		if(!isDynamic) {
			createStatic();
		} else {
			createDynamic();
		}
	}
	
	private void createDynamic() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.x, size.y);
		body.setTransform(position, 0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0.0f;
		fixtureDef.shape = shape;
		fixture = body.createFixture(fixtureDef);
		shape.dispose();
	}
	
	private void createStatic() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.x, size.y);
		body.setTransform(position, 0);
		fixture = body.createFixture(shape, 0.0f);
		shape.dispose();
	}
}
