/*  ---
 * 	Welcome to the 'Rings of Elements' code!
 *  ---	
 * 
 *	TextureSet holds the textures for fast acces and give only one
 *	object
 *	
 *	loadTextureSet count, dont forget count -1
 *	GetTexture(id) 0 TO given count -1 (loadTexture(.., count)) 
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 29.09.2012
 * 
 * @lastChange: 27.12.2012
 * @Info: Added getDrawable()
 */

package de.datpixelstudio.ringsofelements.world;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.datpixelstudio.ringsofelements.Settings;

public class TextureSet
{
	private String textureSetName = null;
	private Vector2 textureSetSize = null;
	private int cellSize = Settings.CELL_SIZE;
	private float textureScale = 1f;
	
	private HashMap<Integer, TextureRegion> textureRegions = null;
	private HashMap<Integer, TextureRegionDrawable> textureRegionsDrawable = null;
	private Texture textureSet = null;
	
	public static TextureSet MISC_TEX = new TextureSet(
			Gdx.files.internal("misc.png"), 64);
	
	public enum TextureMisc {
		BLACK(0), WHITE(1), LIGHT_GREY(2), GREY(3), DARK_GREY(4), RED(5), GREEN(6),
		BLUE(7), YELLOW(8), MAGENTA(9), CYAN(10), BROWN(11), ORANGE(12);
		private int value;
		private TextureRegionDrawable drawable;
		
		private TextureMisc(int value) { this.value = value; }
		public int id() { return value; }
		public TextureRegionDrawable drawable() {
			TextureRegion texture = MISC_TEX.getTexture(value);
			texture.setRegionWidth(2);
			texture.setRegionHeight(2);
			if(drawable == null) drawable = new TextureRegionDrawable
					(texture);
			return drawable;
		}
	}
	
	public TextureSet(final FileHandle file, final int number) {
		this.cellSize = Settings.CELL_SIZE;  
		loadTextureSet(file, number);
		// Nicht so toll? oO 
		if(file.path().equals("misc/colors.jpg")) 
			setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	}
	
	public TextureSet() { }
	
	public void loadTextureSet(final FileHandle file, final int number) {
		loadTextureSet(file.path(), number);
	}
	
	public void setCellSize(final int size) {
		this.cellSize = size;
	}
	
	public void setScale(final int scale) {
		this.textureScale = scale;
	}
	
	public void loadTextureSet(final String location, final int number) {
		if(textureRegions == null) textureRegions = new HashMap<Integer, TextureRegion>();
		
		// Should cut the "," not per number
		textureSetName = location.substring(0, location.length() - 4);
		textureSet = new Texture(Gdx.files.internal(location), true);
		//textureSet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		textureSetSize = new Vector2(textureSet.getWidth(), 
										textureSet.getHeight());
		
		int actualNumber = 0;
		
		int startPixel_X = 0, startPixel_Y = 0;
		int cell_X, cell_Y;
		
		int lineNumber = (int) (textureSetSize.x / cellSize);
		int columnNumber = (int) (textureSetSize.y / cellSize);
		//System.out.println("LineNumber: " + lineNumber + " ColumnNumber: " + columnNumber);
		
		for(cell_Y = 0; cell_Y < columnNumber; cell_Y++) {
			for(cell_X = 0; cell_X < lineNumber; cell_X++) {
				TextureRegion textureRegion = new TextureRegion(textureSet, startPixel_X, startPixel_Y,
						cellSize, cellSize);
				textureRegions.put(actualNumber, textureRegion);
				//System.out.println("number " + actualNumber + " startPixel_X: " + startPixel_X + " startPixel_Y_ " + 
				//		startPixel_Y + " cellSize: " + cellSize);
				
				actualNumber++;
				startPixel_X += cellSize;
				
				if(textureRegions.size() > number) { break; }
			}
			// new line
			startPixel_X = 0;
			startPixel_Y += cellSize;
			//if(tileTextures.size > number) { break; } 
			if(textureRegions.size() > number) { break; }
		}
		getInfo();
	}
	
	public void setFilter(final TextureFilter minFilter, final TextureFilter magFilter) {
		textureSet.setFilter(minFilter, magFilter);
		System.out.println("@TextureSet setFilter: min-" + minFilter + " mag-" + magFilter + " for set: "
				+ textureSetName);
	}
	
	public int getSize() { return cellSize; }
	
	public TextureRegion getTexture(final int id) {
		if(textureRegions.get(id) != null) {
			return textureRegions.get(id);
		} else {
			// Tile will not render if no tile with given id found
			Gdx.app.error("TextureManager", "Texture id: " + id + 
					" not found on textureSet: " + textureSetName);
			Gdx.app.error("TextureManager", "Maybe forget texture -1");
			return null;
		}
	}
	
	public Drawable getDrawable(final int id) {
		if(textureRegionsDrawable == null) {
			textureRegionsDrawable  = new HashMap<Integer, TextureRegionDrawable>();
		}
		if(textureRegionsDrawable.get(id) == null) {
			TextureRegionDrawable drawable = new TextureRegionDrawable(getTexture(id));
			textureRegionsDrawable.put(id, drawable);
		}
		return textureRegionsDrawable.get(id);
	}
	
	public void dispose() {
		textureRegions.clear();
		textureRegions = null;
		textureSet.dispose();
		Gdx.app.debug("TextureManager", "TextureSet: " + getName() + " disposed");
	}
	
	public static void disposeStatic() {
		MISC_TEX.dispose();
	}
	
	public int getNumber() { return textureRegions.size(); }
	
	public String getName() { return textureSetName; }
	
	public void getInfo() {
		Gdx.app.debug("TextureManager", "Loaded: " + textureSetName + " " + textureRegions.size() + 
						" textureRegions by a: " + textureSet.getWidth() + "px x " +
							textureSet.getHeight() + "px textureSet");
	}
}





