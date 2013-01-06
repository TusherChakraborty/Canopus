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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class LevelLoader {
	
	private FileHandle file = null;
	
	private Array<GameObject> gameObjects = null;
	
	public LevelLoader(final String path) {
		this.file = Gdx.files.internal(path);
		this.gameObjects = new Array<GameObject>();
		
		loadXML();
	}
	
	private void loadXML() {
		//TODO load xml
	}
	
	public Array<GameObject> getGameObjects() {
		return gameObjects;
	}
}
