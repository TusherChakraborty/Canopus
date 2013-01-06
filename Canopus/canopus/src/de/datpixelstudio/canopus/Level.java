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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import de.datpixelstudio.statebasedgame.Settings;

public class Level {

	private World world = null;
	private Array<GameObject> gameObjects = null;
	
	public Level(final String path, final World world) {
		this.world = world;
		
		LevelLoader levelLoader = new LevelLoader(path);
		gameObjects = levelLoader.getGameObjects(); // empty
		
		createTestLevel();
	}
	
	private void createTestLevel() {
		GameObject gObj1 = new GameObject(world);
		gObj1.setType(GameObject.Type.STATIC, true);
		gObj1.setAsBox(new Vector2(1, 1));
		gObj1.setPosition(new Vector2(0, 0));
		gObj1.create();
		gameObjects.add(gObj1);
	}
	
	public void draw(final SpriteBatch b) {
	}
	
	public void drawDebug(final ShapeRenderer sR) {
		for(GameObject gameObject : gameObjects) {
			gameObject.drawDebug(sR);
		}
	}
}
