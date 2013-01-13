package de.datpixelstudio.canopus.inputHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import de.datpixelstudio.canopus.Player;
import de.datpixelstudio.statebasedgame.Direction;
import de.datpixelstudio.statebasedgame.InputHandler;
import de.datpixelstudio.statebasedgame.State;

public class GameInputHandler extends InputHandler {

	private Array<Controller> controllers = null;
	private Controller controllerOne = null;
	private ControllerMapping controllerMappingOne = null;
	
	private Player player = null;
	
	public GameInputHandler(State state) {
		super(state);
		
		if(Controllers.getControllers().get(0) != null) {
			for(Controller controller : Controllers.getControllers()) {
				Gdx.app.log(state.getName(), controller.getName());
			}
			controllers = Controllers.getControllers();
			controllerOne = controllers.get(0);
			controllerMappingOne = new ControllerMapping(0, controllerOne.getName());
			controllerMappingOne.setStickDeadZone(0.3f);
		}
		
		createControllerListener();
	}
	
	public void setPlayer(final Player player) {
		this.player = player;
	}
	
	public void update() {
		
		if(controllerOne.getButton(controllerMappingOne.button(ControllerMapping.BUTTON_U))) {
			System.out.println("Button U");
		}
		
		/* First stick */
		// Axis 2 x 3 y left pad
		
		if(!controllerOne.getName().equals("Sony PLAYSTATION(R)3 Controller")) {
			if(controllerOne.getAxis(1) < -controllerMappingOne.getStickDeadZone() 
					|| controllerOne.getAxis(3) < -controllerMappingOne.getStickDeadZone()) {
				player.move(Direction.LEFT);
				System.out.println("Links");
			}
			
			if(controllerOne.getAxis(1) > controllerMappingOne.getStickDeadZone() 
					|| controllerOne.getAxis(3) > controllerMappingOne.getStickDeadZone()) {
				player.move(Direction.RIGHT);
				System.out.println("Rechts");
			}
			
			if(controllerOne.getAxis(0) < -0.2f) {
				System.out.println("Oben");
			}
			
			if(controllerOne.getAxis(0) > 0.2f) {
				System.out.println("Unten");
			}
		} else {
			if(controllerOne.getAxis(0) < -controllerMappingOne.getStickDeadZone() 
					|| controllerOne.getAxis(2) < -controllerMappingOne.getStickDeadZone()) {
				player.move(Direction.LEFT);
				System.out.println("Links");
			}
			
			if(controllerOne.getAxis(0) > controllerMappingOne.getStickDeadZone() 
					|| controllerOne.getAxis(2) > controllerMappingOne.getStickDeadZone()) {
				player.move(Direction.RIGHT);
				System.out.println("Rechts");
			}
			
			if(controllerOne.getAxis(1) < -0.2f) {
				System.out.println("Oben");
			}
			
			if(controllerOne.getAxis(1) > 0.2f) {
				System.out.println("Unten");
			}
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
}