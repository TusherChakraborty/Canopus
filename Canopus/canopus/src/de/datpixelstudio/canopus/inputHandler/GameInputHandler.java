package de.datpixelstudio.canopus.inputHandler;

<<<<<<< HEAD
public class GameInputHandler {

=======
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import de.datpixelstudio.statebasedgame.InputHandler;
import de.datpixelstudio.statebasedgame.State;

public class GameInputHandler extends InputHandler {

	private Array<Controller> controllers = null;
	private Controller controllerOne = null;
	private ControllerMapping controllerMappingOne = null;
	
	public GameInputHandler(State state) {
		super(state);
		
		if(Controllers.getControllers().get(0) != null) {
			for(Controller controller : Controllers.getControllers()) {
				Gdx.app.log(state.getName(), controller.getName());
			}
			controllers = Controllers.getControllers();
			controllerOne = controllers.get(0);
			controllerMappingOne = new ControllerMapping(0, controllerOne.getName());
		}
		
		createControllerListener();
	}
	
	public void update() {
		
		if(controllerOne.getButton(controllerMappingOne.button(ControllerMapping.BUTTON_U))) {
			System.out.println("Button U");
		}
		
		if(controllerOne.getButton(controllerMappingOne.button(ControllerMapping.BUTTON_O))) {
			System.out.println("Button O");
		}
		
		if(controllerOne.getButton(controllerMappingOne.button(ControllerMapping.BUTTON_Y))) {
			System.out.println("Button Y");
		}
		
		if(controllerOne.getButton(controllerMappingOne.button(ControllerMapping.BUTTON_A))) {
			System.out.println("Button A");
		}
		
		// Axis 2 x 3 y left pad
		if(controllerOne.getAxis(3) < -0.2f) {
			System.out.println("Links");
		}
		
		if(controllerOne.getAxis(3) > 0.2f) {
			System.out.println("Rechts");
		}
		
		if(controllerOne.getAxis(2) < -0.2f) {
			System.out.println("Oben");
		}
		
		if(controllerOne.getAxis(2) > 0.2f) {
			System.out.println("Unten");
		}
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void createControllerListener() {
		ControllerListener controllerListener = new ControllerListener() {
			
			@Override
			public boolean ySliderMoved(Controller arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean xSliderMoved(Controller arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean povMoved(Controller arg0, int arg1, PovDirection arg2) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void disconnected(Controller arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void connected(Controller arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean buttonUp(Controller arg0, int arg1) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean buttonDown(Controller controller, int button) {
				System.out.println("ButtonDown: " + button);
				return false;
			}
			
			@Override
			public boolean axisMoved(Controller arg0, int arg1, float arg2) {
				//System.out.println("axis: " + arg1 + " " + arg2);
				return false;
			}
			
			@Override
			public boolean accelerometerMoved(Controller arg0, int arg1, Vector3 arg2) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		Controllers.addListener(controllerListener);
	}
>>>>>>> branch 'master' of https://github.com/kinxz/Canopus.git
}
