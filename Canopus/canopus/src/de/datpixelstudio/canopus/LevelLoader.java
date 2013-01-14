/*  ---
 * 	Welcome to the 'Canopus' code!
 *  ---	
 * 
 *	XML Level saver
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
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

import de.datpixelstudio.canopus.LevelObject.Dimension;

public class LevelLoader {
	
	private FileHandle file = null;
	
	private Array<GameObject> gameObjects = null;
	private HashMap<Integer, Array<Dimension>> availableDimensionSet = null;
	private TextureAtlas textureAtlas = null;
	
	public LevelLoader(final String path) {
		this.file = Gdx.files.internal(path);
		this.gameObjects = new Array<GameObject>();
		this.availableDimensionSet = new HashMap<Integer, Array<Dimension>>();
		
		loadXML();
	}
	
	private void loadXML() {
		//TODO load xml
	}
	
	public Array<GameObject> getGameObjects() { return gameObjects; }
	
	public HashMap<Integer, Array<Dimension>> getDimensions() { return availableDimensionSet; }
	
	public TextureAtlas getTextureAtlas() { return textureAtlas; }
}
