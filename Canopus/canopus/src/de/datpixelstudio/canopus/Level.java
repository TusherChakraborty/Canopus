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

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import de.datpixelstudio.canopus.LevelObject.Dimension;
import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.Settings;

public class Level {

	private World world = null;
	private Array<GameObject> gameObjects = null;
	private Array<Dimension> activeDimensions = null;
	// The Number hold the set for ONE ACTIVE set of different dimensions
	// all other should be deactivated!
	private HashMap<Integer, Array<Dimension>> availableDimensionSet = null;
	private int activeDimensionSetIndex = 0;
	private boolean isDimensionSetChanged = false;
	
	private boolean isDebugDraw = true;
	private Box2DDebugRenderer box2dDebugRenderer = null;
	
	private TextureAtlas textureAtlas = null;
	
	public Level(final String path, final World world) {
		this.world = world;
		
		LevelLoader levelLoader = new LevelLoader(path);
		gameObjects = levelLoader.getGameObjects(); // empty
		availableDimensionSet = levelLoader.getDimensions(); // empty
		textureAtlas = levelLoader.getTextureAtlas(); // empty
		activeDimensions = availableDimensionSet.get(0); // null;
		activeDimensionSetIndex = 0;
		
		/* Test */
		textureAtlas = new TextureAtlas(
				Gdx.files.internal("textures/bw/bwpack.atlas"));
		availableDimensionSet = new HashMap<Integer, Array<Dimension>>();
		
		if(isDebugDraw) {
			box2dDebugRenderer = new Box2DDebugRenderer();
		}
		createTestLevel();
	}
	
	private void createTestLevel() {
		LevelObject gObj1 = new LevelObject(world);
		gObj1.setType(GameObject.Type.DYNAMIC, false);
		gObj1.setAsBox(new Vector2(1, 1));
		gObj1.setPositionBody(new Vector2(-5, 1), 20);
		gObj1.setDensity(0.5f);
		gObj1.setFriction(0.5f);
		gObj1.setRestitution(0.5f);
		gObj1.setTexture(textureAtlas.findRegion("white"));
		gObj1.create(world);
		gameObjects.add(gObj1);
		
		LevelObject gObj2 = new LevelObject(world);
		gObj2.setType(GameObject.Type.STATIC, false);
		Vector2[] vertices = {
				new Vector2(0, 0),
				new Vector2(5, 0),
				new Vector2(5, 1),
		};
		gObj2.setPolygonVertices(vertices);
		
		Vector2[] vertices2 = {
				new Vector2(5, 1),
				new Vector2(10, 0),
				new Vector2(10, 3),
		};
		gObj2.setPolygonVertices(vertices2);
		
		Vector2[] vertices3 = {
				new Vector2(10, 3),
				new Vector2(15, 0),
				new Vector2(15, 6),
		};
		gObj2.setPolygonVertices(vertices3);
		
		Vector2[] vertices4 = {
				new Vector2(15, 6),
				new Vector2(20, 0),
				new Vector2(20, 10),
		};
		gObj2.setPolygonVertices(vertices4);
		
		Vector2[] vertices5 = {
				new Vector2(20, 10),
				new Vector2(25, 0),
				new Vector2(25, 17),
		};
		gObj2.setPolygonVertices(vertices5);
		
		gObj2.setPositionBody(new Vector2(0, 0), 0);
		gObj2.create(world);
		gameObjects.add(gObj2);
		
		/* Boden 1 */
		LevelObject gObj4 = new LevelObject(world);
		gObj4.setType(GameObject.Type.STATIC, false);
		gObj4.setAsBox(new Vector2(20, 2f));
		gObj4.setPositionBody(new Vector2(-20, -2f), 0);
		gObj4.create(world);
		gObj4.setUserData(gObj4);
		gObj4.setDimension(Dimension.POSITIVE, true);
		gameObjects.add(gObj4);
		
		/* Wand links */
		LevelObject gObj5 = new LevelObject(world);
		gObj5.setType(GameObject.Type.STATIC, false);
		gObj5.setAsBox(new Vector2(10, 10));
		gObj5.setPositionBody(new Vector2(-21, 0), 0);
		gObj5.create(world);
		gObj5.setUserData(gObj5);
		gObj5.setDimension(Dimension.POSITIVE, true);
		gameObjects.add(gObj5);
		
		/* Wand rechts */
		LevelObject gObj6 = new LevelObject(world);
		gObj6.setType(GameObject.Type.STATIC, false);
		gObj6.setAsBox(new Vector2(10, 10));
		gObj6.setPositionBody(new Vector2(-11, 0), 0);
		gObj6.create(world);
		gObj6.setUserData(gObj6);
		gObj6.setDimension(Dimension.NEGATIVE, false);
		gameObjects.add(gObj6);
		
		/* Boden 2 */
		LevelObject gObj7 = new LevelObject(world);
		gObj7.setType(GameObject.Type.STATIC, false);
		gObj7.setAsBox(new Vector2(20, 2f));
		gObj7.setPositionBody(new Vector2(-20, -2), 0);
		gObj7.create(world);
		gObj7.setUserData(gObj7);
		gObj7.setDimension(Dimension.NEGATIVE, false);
		gameObjects.add(gObj7);
		
		
		/* Dimensions */
		Array<Dimension> dim0 = new Array<Dimension>();
		dim0.add(Dimension.POSITIVE);
		availableDimensionSet.put(0, dim0);
		
		Array<Dimension> dim1 = new Array<Dimension>();
		dim1.add(Dimension.NEGATIVE);
		availableDimensionSet.put(1, dim1);
		
		setDimensionSet(0);
	}
	
	
	public void update(final GameContainer gc) {
		if(world != null) {
			world.step(Settings.getP_TimeStep(), Settings.getP_velocityIterations(), 
					Settings.getP_positionIterations());
		}
		
		if(isDimensionSetChanged) updateLevelObjectDimension();
	}
	
	public void draw(final GameContainer gc) {
	}
	
	private void updateLevelObjectDimension() {
		isDimensionSetChanged = false;
		
		for(GameObject gameObject : gameObjects) {
			if(gameObject instanceof LevelObject) {
				LevelObject levelObject = (LevelObject) gameObject;
				for(Dimension dimension : activeDimensions) {
					if(levelObject.getDimension() == dimension) {
						levelObject.setActive(true);
						break;
					} else {	
						levelObject.setActive(false);
					}
				}
			}
		}
		Gdx.app.log("Level", "Dimension changed");
	}
	
	public void setDimensionSet(final int index) {
		if(availableDimensionSet.get(index) != null) {
			activeDimensions = availableDimensionSet.get(index);
			activeDimensionSetIndex = index;
			
			isDimensionSetChanged = true;
		} else {
			Gdx.app.log("Level", "DimensionSet " + index + " is not given");
		}
	}
	
	public void addActiveDimension(final Dimension dimension) {
		for(Dimension dimensionObj : activeDimensions) {
			if(dimensionObj == dimension) {
				Gdx.app.log("Level", "Dimension \"" + dimension.getValue() + "\" is allready active");
				return;
			}
		}
		activeDimensions.add(dimension);
		isDimensionSetChanged = true;
	}
	
	public void removeActiveDimension(final Dimension dimension) {
		for(int i = 0; i < activeDimensions.size; i++) {
			if(activeDimensions.get(i) == dimension) {
				activeDimensions.removeIndex(i);
				isDimensionSetChanged = true;
				return;
			}
		}
		Gdx.app.log("Level", "Dimension \"" + dimension.getValue() + "\" is allready deactivated");
	}
	
	public Array<Dimension> getDimensionSet(final int index) { 
		return availableDimensionSet.get(index);
	}
	
	public Array<Dimension> getActiveDimensions() { return activeDimensions; }
	
	public int getActiveDimensionSetIndex() { return activeDimensionSetIndex; }
	
	public void drawDebug(final GameContainer gc) {
		if(world != null) {
			box2dDebugRenderer.render(world, gc.gameCam.combined);
		}
		
		for(GameObject gameObject : gameObjects) {
			gameObject.drawDebug(gc.sR);
		}
	}
}
