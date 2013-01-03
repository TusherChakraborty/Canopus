package de.datpixelstudio.canopus;

public class FixtureDatas {

	private boolean isJumpable = true;
	
	public FixtureDatas() {}
	
	public FixtureDatas setJumpable(final boolean jumpable) {
		this.isJumpable = jumpable;
		return this;
	}
	
	public boolean isJumpable() {
		return isJumpable;
	}
}
