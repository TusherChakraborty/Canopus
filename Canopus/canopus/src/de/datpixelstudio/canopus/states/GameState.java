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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import de.datpixelstudio.canopus.Canopus;
import de.datpixelstudio.canopus.Level;
import de.datpixelstudio.canopus.LevelObject.Dimension;
import de.datpixelstudio.canopus.Player;
import de.datpixelstudio.canopus.inputHandler.GameInputHandler;
import de.datpixelstudio.statebasedgame.GameContainer;
import de.datpixelstudio.statebasedgame.Settings;
import de.datpixelstudio.statebasedgame.State;
import de.datpixelstudio.statebasedgame.StateBasedGame;

public class GameState extends State {

	private World world = null;
	private Level level = null;
	
	private Player player = null;
	
	private GameInputHandler gameInputHandler = null;
	
	private String activeDimensions = "";
	
	public GameState(int stateID, StateBasedGame sbg) {
		super(stateID, "GameState", sbg);
	}

	@Override
	public void init(GameContainer gc) {
		world = new World(new Vector2(0, -20), false);
		level = new Level("dat", world);
		
		player = new Player(world);
		player.setPositionBody(new Vector2(2, 5), 0);
		
		Settings.setWorldScale(0.025f);
		Settings.setPhysic(60, Settings.getP_velocityIterations(), 
				Settings.getP_positionIterations());
		gc.gameCam.zoom = Settings.getWorldScale();
		gc.gameCam.update();
		gc.gameCam.position.set(0, 10, 0);
		
		gameInputHandler = new GameInputHandler(this);
		gameInputHandler.setPlayer(player);
		gameInputHandler.setLevel(level);
		addInput(gameInputHandler);
		setInput();
	}

	@Override
	public void update(GameContainer gc) {
		gameInputHandler.update();
		player.update();
		level.update(gc);
	}

	@Override
	public void render(GameContainer gc) {
		gc.gameCam.update();
		gc.gameCam.position.set(player.getBody().getPosition().x, 
				player.getBody().getPosition().y, 0);
		gc.b.setProjectionMatrix(gc.gameCam.combined);
		gc.sR.setProjectionMatrix(gc.gameCam.combined);
		
		/* DebugDraw Shapes */
		level.drawDebug(gc);
		/* End DebugDraw Shapes */
		
		gc.b.begin();
		
		/* GameCam */
		
		/* End GameCam */
		
		gc.b.setProjectionMatrix(gc.uiCam.combined);
		
		/* UiCam */
		
		activeDimensions = " ";
		for(Dimension dimension : level.getActiveDimensions()) {
			activeDimensions = activeDimensions + dimension.getValue() + ", ";
		}
		
		Canopus.getFont().draw(gc.b, "Active dimensions: " + activeDimensions, 20, 120);
		Canopus.getFont().draw(gc.b, "Player dir: " + player.getDirection().getValue(), 20, 100);
		Canopus.getFont().draw(gc.b, "Player isMove: " + player.isMove(), 20, 80);
		Canopus.getFont().draw(gc.b, "Player friction: " + player.getFriction(), 20, 60);
		Canopus.getFont().draw(gc.b, "Player isGround: " + player.isGround(), 20, 40);
		Canopus.getFont().draw(gc.b, "FPS: " + Gdx.graphics.getFramesPerSecond(), 20, 20);
		
		/* End UiCam*/
		
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
