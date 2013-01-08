/*  ---
 * 	Welcome to the 'Canopus' code!
 *  ---	
 * 
 *	Level
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 06.01.2013
 * 
 * @lastChange: 06.01.2013
 * @Info: creation
 */

package de.datpixelstudio.canopus;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.Settings;

public class Level {

	private World world = null;
	private Array<GameObject> gameObjects = null;
	
	private boolean isDebugDraw = true;
	private Box2DDebugRenderer box2dDebugRenderer = null;
	
	public Level(final String path, final World world) {
		this(path);
		this.world = world;
		
		if(isDebugDraw) {
			box2dDebugRenderer = new Box2DDebugRenderer();
		}
		createTestLevel();
	}
	
	/* For non physic world */
	public Level(final String path) {
		LevelLoader levelLoader = new LevelLoader(path);
		gameObjects = levelLoader.getGameObjects(); // empty
	}
	
	
	private void createTestLevel() {
		GameObject gObj1 = new GameObject(world);
		gObj1.setType(GameObject.Type.DYNAMIC, false);
		gObj1.setAsBox(new Vector2(1, 1));
		gObj1.setPositionBody(new Vector2(1, 30), 0);
		gObj1.setDensity(0.0f);
		gObj1.setFriction(0f);
		gObj1.setRestitution(0f);
		gObj1.create(world);
		gameObjects.add(gObj1);
		
		GameObject gObj2 = new GameObject(world);
		gObj2.setType(GameObject.Type.STATIC, false);
		Vector2[] vertices = {
				new Vector2(0, 0),
				new Vector2(3, 0),
				new Vector2(3, 5),
				new Vector2(0, 5)
		};
		gObj2.setPolygonVertices(vertices);
		gObj2.setPositionBody(new Vector2(0, 0), 0);
		gObj2.create(world);
		gameObjects.add(gObj2);
		
		GameObject gObj3 = new GameObject(world);
		gObj3.setType(GameObject.Type.STATIC, false);
		gObj3.setAsCircle(1);
		gObj3.setPositionBody(new Vector2(3, 3), 0);
		gObj3.create(world);
		gameObjects.add(gObj3);
	}
	
	public void update(final GameContainer gc) {
		if(world != null) {
			world.step(Settings.getP_TimeStep(), Settings.getP_velocityIterations(), 
					Settings.getP_positionIterations());
		}
	}
	
	public void draw(final GameContainer gc) {
	}
	
	public void drawDebug(final GameContainer gc) {
		if(world != null) {
			box2dDebugRenderer.render(world, gc.gameCam.combined);
		}
		
		for(GameObject gameObject : gameObjects) {
			gameObject.drawDebug(gc.sR);
		}
	}
}
