package de.datpixelstudio.canopus;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class LevelRectangles {
	
	private Vector2 position = null;
	private Vector2 size = null;
	
	private World world;
	private Body body = null;
	private Fixture fixture = null;
	
	public LevelRectangles(final Vector2 position, final Vector2 size, World world) {
		this.position = position;
		this.size = size;
		this.world = world;
		
		create();
	}
	
	private void create() {
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
