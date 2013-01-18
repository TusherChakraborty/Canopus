/*  ---
 * 	Welcome to the 'Canopus' code!
 *  ---	
 * 
 *	LevelObject
 *	GameObject with dimension. Standard dim
 *	is positive and active.
 *
 *	Level class should decide if its active or not
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 14.01.2013
 * 
 * @lastChange: 18.01.2013
 * @Info: SwitchAllowed not tested
 */

package de.datpixelstudio.canopus;

import com.badlogic.gdx.physics.box2d.World;

public class LevelObject extends GameObject {

	private Dimension dimension = Dimension.POSITIVE;
	private boolean isSwitchAllowed = true;
	
	public enum Dimension {
		POSITIVE("positive"), NEGATIVE("negative");
		private String value;
		private Dimension (String value) { this.value = value; }
		public String getValue() { return value; }
	}
	
	public LevelObject(World world) {
		super(world);
	}
	
	public void setUserData(final Object object) {
		getBody().setUserData(object);
	}

	public void setDimension(final Dimension dimension, final boolean isActive ) {
		this.dimension = dimension;
		setActive(isActive);
	}
	
	public void setActive(final boolean isActive) {
		if(isSwitchAllowed) getBody().setActive(isActive);
	}
	
	public void setSwitchAllowed(final boolean isAllowed) {
		this.isSwitchAllowed = isAllowed;
	}
	
	public boolean isActive() { return getBody().isActive(); }
	
	public Dimension getDimension() { return dimension; }
}
