/*  ---
 * 	Welcome to the 'Rings of Elements' code!
 *  ---	
 * 
 *	GameContainer
 * 
 *	---
 * @author: Oczadly Simon <staxx6>
 * @date: 27.09.2012
 * 
 * @lastChange: 27.09.2012
 * @Info:
 */

package de.datpixelstudio.statebasedgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameContainer
{
	public SpriteBatch b = null;
	public ShapeRenderer sR = null;
	public OrthographicCamera gameCam = null;
	public OrthographicCamera uiCam = null;
	
	public Color glClearColor = new Color(0, 0, 0.2f, 1);
	
	private String fps; // statebased   gc.setFps(int)  String getFps
	
	public GameContainer(){}
	
	public void setSpriteBatch(final SpriteBatch b)
	{
		if(b != null)
		{
			this.b = b;
		} else
		{
			throw new IllegalArgumentException("GameContainer: SpriteBatch is null");
		}
	}
	
	public void setShapeRenderer(final ShapeRenderer sR)
	{
		if(sR != null)
		{
			this.sR = sR;
		} else
		{
			throw new IllegalArgumentException("GameContainer: ShapeRenderer is null");
		}
	}
	
	public void setOrthographicCameraGame(final OrthographicCamera gameCam)
	{
		if(gameCam != null)
		{
			this.gameCam = gameCam;
		} else
		{
			throw new IllegalArgumentException("GameContainer: OrthographicCamera_Game is null");
		}
	}
	
	public void setOrthographicCameraUI(final OrthographicCamera uiCam)
	{
		if(uiCam != null)
		{
			this.uiCam = uiCam;
		} else
		{
			throw new IllegalArgumentException("GameContainer: OrthographicCamera_Ui is null");
		}
	}
	
	public void setFps(final String fps)
	{
		this.fps = fps;
	}
	
	public String fps()
	{
		return fps;
	}
}









