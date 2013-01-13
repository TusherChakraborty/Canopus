/*  ---
 * 	Welcome to the 'Canopus' code!
 *  ---	
 * 
 *	GameState
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 06.01.2013
 * 
 * @lastChange: 06.01.2013
 * @Info: creation
 */

package de.datpixelstudio.canopus.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import de.datpixelstudio.canopus.Level;
import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.Settings;
import de.datpixelstudio.statebasedgame.State;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public class GameState extends State {

	private World world = null;
	private Level level = null;
	
	public GameState(int stateID, StateBasedGame sbg) {
		super(stateID, "GameState", sbg);
	}

	@Override
	public void init(GameContainer gc) {
		world = new World(new Vector2(0, -10), false);
		level = new Level("dat", world);
		
		Settings.setWorldScale(0.05f);
		Settings.setPhysic(60, Settings.getP_velocityIterations(), 
				Settings.getP_positionIterations());
		gc.gameCam.zoom = Settings.getWorldScale();
		gc.gameCam.update();
		gc.gameCam.position.set(0, 10, 0);
		
		for(Controller controller : Controllers.getControllers()) {
			Gdx.app.log(this.getName(), controller.getName());
		}
	}

	@Override
	public void update(GameContainer gc) {
		level.update(gc);
	}

	@Override
	public void render(GameContainer gc) {
		gc.gameCam.update();
		gc.b.setProjectionMatrix(gc.gameCam.combined);
		gc.sR.setProjectionMatrix(gc.gameCam.combined);
		level.drawDebug(gc);
		gc.b.begin();
		
		gc.b.end();
		
	}

	@Override
	public void resize(int width, int height, GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

}
