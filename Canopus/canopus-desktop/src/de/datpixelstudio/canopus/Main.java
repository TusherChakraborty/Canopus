/*  ---
 * 	Welcome to the 'Rings of Elements' code!
 *  ---	
 * 
 *	Main start class
 *
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 17.09.2012
 * 
 * @lastChange: 17.09.2012
 * @Info:
 */

package de.datpixelstudio.canopus;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "canopus";
		cfg.useGL20 = false;
		cfg.vSyncEnabled = true;
		cfg.width = 1280;
		cfg.height = 720;
		cfg.fullscreen = false;
		
		new LwjglApplication(new Canopus(), cfg);
	}
}
