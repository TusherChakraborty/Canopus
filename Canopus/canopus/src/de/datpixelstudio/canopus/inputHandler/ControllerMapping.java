/*  ---
 * 	Welcome to the 'Canopus' code!
 *  ---	
 * 
 *	ControllerSettings
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 13.01.2013
 * 
 * @lastChange: 13.01.2013
 * @Info: creation
 */

package de.datpixelstudio.canopus.inputHandler;

import java.util.HashMap;

import com.badlogic.gdx.controllers.mappings.Ouya;

public class ControllerMapping {

	private final int id;
	private final String name;
	
	public static String BUTTON_O = "button_o";
	public static String BUTTON_U = "button_u";
	public static String BUTTON_Y = "button_y";
	public static String BUTTON_A = "button_a";
	public static String BUTTON_DPAD_UP = "buttonDpadUp";
	public static String BUTTON_DPAD_DOWN = "buttonDpadDown";
	public static String BUTTON_DPAD_RIGHT = "buttonDpadRight";
	public static String BUTTON_DPAD_LEFT = "buttonDpadLeft";
	public static String BUTTON_L1 = "buttonL1";
	public static String BUTTON_L2 = "buttonL2";
	public static String BUTTON_L3 = "buttonL3";
	public static String BUTTON_R1 = "buttonR1";
	public static String BUTTON_R2 = "buttonR2";
	public static String BUTTON_R3 = "buttonR3";
	public static String AXIS_LEFT_X = "axisLeftX";
	public static String AXIS_LEFT_Y = "axisLeftY";
	public static String AXIS_LEFT_TRIGGER = "axisLeftTrigger";
	public static String AXIS_RIGHT_X = "axisRightX";
	public static String AXIS_RIGHT_Y = "axisRightY";
	public static String AXIS_RIGHT_TRIGGER = "axisRightTrogger";
	
	private HashMap<String, Integer> mapping = null;
	
	private static float STICK_DEAD_ZONE = 0.0f;
	
	public ControllerMapping(final int id, final String controllerName) {
		this.id = id;
		this.name = controllerName;
		
		mapping = new HashMap<String, Integer>();
		
		if(name.equals(Ouya.ID)) {
			mapping.put(BUTTON_O, Ouya.BUTTON_O);
			mapping.put(BUTTON_U, Ouya.BUTTON_U);
			mapping.put(BUTTON_Y, Ouya.BUTTON_Y);
			mapping.put(BUTTON_A, Ouya.BUTTON_A);
			mapping.put(BUTTON_DPAD_UP, Ouya.BUTTON_DPAD_UP);
			mapping.put(BUTTON_DPAD_DOWN, Ouya.BUTTON_DPAD_DOWN);
			mapping.put(BUTTON_DPAD_RIGHT, Ouya.BUTTON_DPAD_RIGHT);
			mapping.put(BUTTON_DPAD_LEFT, Ouya.BUTTON_DPAD_LEFT);
			mapping.put(BUTTON_L1, Ouya.BUTTON_L1);
			mapping.put(BUTTON_L2, Ouya.BUTTON_L2);
			mapping.put(BUTTON_L3, Ouya.BUTTON_L3);
			mapping.put(BUTTON_R1, Ouya.BUTTON_R1);
			mapping.put(BUTTON_R2, Ouya.BUTTON_R2);
			mapping.put(BUTTON_R3, Ouya.BUTTON_L3);
			mapping.put(AXIS_LEFT_X, Ouya.AXIS_LEFT_X);
			mapping.put(AXIS_LEFT_Y, Ouya.AXIS_LEFT_Y);
			mapping.put(AXIS_LEFT_TRIGGER, Ouya.AXIS_LEFT_TRIGGER);
			mapping.put(AXIS_RIGHT_X, Ouya.AXIS_RIGHT_X);
			mapping.put(AXIS_RIGHT_Y, Ouya.AXIS_RIGHT_Y);
			mapping.put(AXIS_RIGHT_TRIGGER, Ouya.AXIS_RIGHT_TRIGGER);
		}
		
		if(name.equals("Logitech Cordless RumblePad 2 USB")) {
			mapping.put(BUTTON_O, 1);
			mapping.put(BUTTON_U, 0);
			mapping.put(BUTTON_Y, 3);
			mapping.put(BUTTON_A, 2);
			mapping.put(BUTTON_DPAD_UP, -1);
			mapping.put(BUTTON_DPAD_DOWN, -1);
			mapping.put(BUTTON_DPAD_RIGHT, -1);
			mapping.put(BUTTON_DPAD_LEFT, -1);
			mapping.put(BUTTON_L1, 4);
			mapping.put(BUTTON_L2, 6);
			mapping.put(BUTTON_L3, 10);
			mapping.put(BUTTON_R1, 5);
			mapping.put(BUTTON_R2, 7);
			mapping.put(BUTTON_R3, 11);
			mapping.put(AXIS_LEFT_X, -1);
			mapping.put(AXIS_LEFT_Y, -1);
			mapping.put(AXIS_LEFT_TRIGGER, -1);
			mapping.put(AXIS_RIGHT_X, -1);
			mapping.put(AXIS_RIGHT_Y, -1);
			mapping.put(AXIS_RIGHT_TRIGGER, -1);
		}
		
		if(name.equals("Sony PLAYSTATION(R)3 Controller")) {
			mapping.put(BUTTON_O, 14);
			mapping.put(BUTTON_U, 15);
			mapping.put(BUTTON_Y, 12);
			mapping.put(BUTTON_A, 13);
			mapping.put(BUTTON_DPAD_UP, 4);
			mapping.put(BUTTON_DPAD_DOWN, 6);
			mapping.put(BUTTON_DPAD_RIGHT, 5);
			mapping.put(BUTTON_DPAD_LEFT, 7);
			mapping.put(BUTTON_L1, 10);
			mapping.put(BUTTON_L2, 8);
			mapping.put(BUTTON_L3, 1);
			mapping.put(BUTTON_R1, 11);
			mapping.put(BUTTON_R2, 9);
			mapping.put(BUTTON_R3, 2);
			mapping.put(AXIS_LEFT_X, -1);
			mapping.put(AXIS_LEFT_Y, -1);
			mapping.put(AXIS_LEFT_TRIGGER, -1);
			mapping.put(AXIS_RIGHT_X, -1);
			mapping.put(AXIS_RIGHT_Y, -1);
			mapping.put(AXIS_RIGHT_TRIGGER, -1);
		}
	}
	
	public String getName() { return name; }
	
	public int getID() { return id; }
	
	public int button(final String button) {
		if(mapping.get(button) == null) return -1;
		return mapping.get(button);
	}
	
	public static void setStickDeadZone(final float stickDeadZone) {
		STICK_DEAD_ZONE = stickDeadZone;
	}
	
	public static float getStickDeadZone() { return STICK_DEAD_ZONE; }
}
