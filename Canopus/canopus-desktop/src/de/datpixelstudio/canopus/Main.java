package de.datpixelstudio.canopus;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "canopus";
		cfg.useGL20 = false;
		cfg.vSyncEnabled = true;
		cfg.width = 800;
		cfg.height = 600;
		
		new LwjglApplication(new Canopus(), cfg);
	}
}
