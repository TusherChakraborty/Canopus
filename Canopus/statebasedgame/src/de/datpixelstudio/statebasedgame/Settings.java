/*  ---
 * 	Welcome to the 'Rings of Elements' code!
 *  ---	
 * 
 * Game settings
 * 
 *  ---
 * @author: Oczadly Simon <staxx6>
 * @date: 24.11.2012
 * 
 * @lastChange: 24.11.2012
 * @Info:
 */

package de.datpixelstudio.statebasedgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class Settings {
    
    private static int WIDTH = 100;
    private static int HEIGHT = 100;
    private static boolean FULLSCREEN = false;
	 	
    private static SimpleQuad SCREEN_FRAME = null;
    
    public static final int CHUNK_SIZE_CELLS_X = 13;
	public static final int CHUNK_SIZE_CELLS_Y = 8;
	
	public static int CELL_SIZE = 64;
	private static float WORLD_SCALE = 1;
	
	/* Physics */
	private static float TIMESTEP = 60f;
	private static int VELOCITY_ITERATIONS = 6;
	private static int POSITION_ITERATIONS = 2;
	
    private Settings() {

    }
    
    public static void setWorldScale(final float scale) {
    	WORLD_SCALE = scale;
    }
    
    public static float getWorldScale() { return WORLD_SCALE; }
    
    public static void setPhysic(final float timeStep, final int velocityIterations, final int positionIterations) {
    	TIMESTEP = 1 / timeStep;
    	VELOCITY_ITERATIONS = velocityIterations;
    	POSITION_ITERATIONS = positionIterations;
    }
    
    public static float getP_TimeStep() { return TIMESTEP; }
    public static int getP_velocityIterations() { return VELOCITY_ITERATIONS; }
    public static int getP_positionIterations() { return POSITION_ITERATIONS; }
	
	public static void changeScreenSize(final OrthographicCamera cam, final int width, final int height, final boolean fullScreen)
	{
		WIDTH = width;
		HEIGHT = height;
		FULLSCREEN = fullScreen;
		
		Gdx.app.getGraphics().setDisplayMode(width, height, fullScreen);
		
		cam.viewportWidth = width;
		cam.viewportHeight = height;
		cam.setToOrtho(false, width, height);
		cam.update();
		
		changeScreenFrame();
		Gdx.app.debug("Screen", "Changed to " + width + "x" +
						height + " " + fullScreen);
	}
	
	public static void changeScreenSize(final int width, final int height, final boolean fullScreen)
	{
		WIDTH = width;
		HEIGHT = height;
		FULLSCREEN = fullScreen;
		
		Gdx.app.getGraphics().setDisplayMode(width, height, fullScreen);
		
		changeScreenFrame();
		
		Gdx.app.debug("Screen", "Changed to " + width + "x" +
						height + " " + fullScreen);
	}
	
	public static void changeScreenSizeAllCams(final GameContainer gc, final int width, final int height, final boolean fullScreen)
	{
		WIDTH = width;
		HEIGHT = height;
		FULLSCREEN = fullScreen;
		
		Gdx.app.getGraphics().setDisplayMode(width, height, fullScreen);
		
		gc.uiCam.viewportWidth = width;
		gc.uiCam.viewportHeight = height;
		gc.uiCam.setToOrtho(false, width, height);
		gc.uiCam.update();
		
		gc.gameCam.viewportWidth = width;
		gc.gameCam.viewportHeight = height;
		gc.gameCam.setToOrtho(false, width, height);
		gc.gameCam.update();
		
		changeScreenFrame();
		
		Gdx.app.debug("Screen", "Changed to " + width + "x" +
						height + " " + fullScreen);
	}
	
	private static void changeScreenFrame()
	{
		if(SCREEN_FRAME == null) SCREEN_FRAME = new SimpleQuad();
		SCREEN_FRAME.setPosition(Vector2.Zero);
		SCREEN_FRAME.setSize(new Vector2(WIDTH, HEIGHT));
	}
	
	public static SimpleQuad getScreenFrame() { return SCREEN_FRAME; }
	
	public static int screenWidth() { return WIDTH; }
	public static int screenHeight() { return HEIGHT; }
	public static boolean isFullScreen() { return FULLSCREEN; }
}
