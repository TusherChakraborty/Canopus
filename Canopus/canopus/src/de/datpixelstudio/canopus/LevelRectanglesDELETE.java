package de.datpixelstudio.canopus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import de.datpixelstudio.BodyDatas;

public class LevelRectangles {
	
	private Vector2 position = null;
	private Vector2 size = null;
	
	private World world;
	private Body body = null;
	private Fixture fixture = null;
	
	private boolean isJumpable = true;
	
	private String space = "positiv";
	
	public LevelRectangles(final Vector2 position, final Vector2 size, final boolean isDynamic ,World world, final String space) {
		this.position = position;
		this.size = size;
		this.world = world;
		this.space = space;
		
		if(!isDynamic) {
			createStatic();
		} else {
			createDynamic();
		}
		fixture.setUserData("LevelObject:" + size.toString() + position.toString() + System.nanoTime());
		body.setUserData(new BodyDatas().setSpace(space));
		
		if(space.equals("negativ")) {
			body.setActive(false);
		}
	}
	
	public LevelRectangles(final Vector2[] vertices, final World world, final String space) {
		this.world = world;
		this.space = space;
		
		/*
		Vector2[] vertices = {
				new Vector2(0,0),
				new Vector2(100,0),
				new Vector2(100,10f)
		};
		isJumpable = true;
		createRectangle(vertices);
		
		Vector2[] vertices2 = {
				new Vector2(10,0),
				new Vector2(100,0),
				new Vector2(100,50f)
		};
		isJumpable = true;
		createRectangle(vertices2);
		
		Vector2[] vertices3 = {
				new Vector2(20,0),
				new Vector2(100,0),
				new Vector2(100,100f)
		};
		isJumpable = false;
		createRectangle(vertices3);
		
		Vector2[] vertices4 = {
				new Vector2(30,0),
				new Vector2(100,0),
				new Vector2(100,200f)
		};
		isJumpable = false;
		createRectangle(vertices4);
		
		Vector2[] vertices5 = {
				new Vector2(40,0),
				new Vector2(100,0),
				new Vector2(100,300f)
		};
		isJumpable = false;
		createRectangle(vertices5);
		
		*/
		createRectangle(vertices);
	}
	
	private void createRectangle(final Vector2[] vertices) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		body = world.createBody(bodyDef);
		body = world.createBody(bodyDef);
		body.setUserData(new BodyDatas().setSpace(space));
		if(space.equals("negativ")) body.setActive(false);
		
		size = new Vector2();
		
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);
		body.setTransform(0, 1, 0);
		fixture = body.createFixture(shape, 0.0f);
		fixture.setUserData(new FixtureDatas().setJumpable(isJumpable));
		shape.dispose();
	}
	
	private void createDynamic() {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		body = world.createBody(bodyDef);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(0.3f);
		//PolygonShape shape = new PolygonShape();
		//shape.setAsBox(size.x, size.y);
		body.setTransform(position, 0);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 0f;
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 0.5f;
		fixtureDef.shape = shape;
		fixture = body.createFixture(fixtureDef);
		shape.dispose();
		body.setLinearVelocity(new Vector2(10, 10));
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
	
	public void update() {
		if(Gdx.input.isKeyPressed(Keys.C) && body != null) {
			body.setActive(!body.isActive());
		}
	}
	
	public Fixture getFixture() {
		return fixture;
	}
	
	public Vector2 getSize() {
		return size;
	}
}
