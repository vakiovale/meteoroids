package meteoroids.Meteoroids;

import meteoroids.Meteoroids.controllers.GameController;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Main Game Loop that handles the game
 * 
 * @author vpyyhtia
 *
 */
public class Game {
	
	private GameController gameController;
	private GameTimer timer;

	public Game() {
		gameController = new GameController();
		timer = new GameTimer();
		Sys.getTime();
	}
	
	/**
	 * Starts the main game loop. 
	 * Uses GameController to update the game world.
	 * 
	 */
	public void start() {
              
		init();
		
		int deltaTime = timer.getDeltaTime();		
		
		// Game loop
        while (!Display.isCloseRequested()) {
        	deltaTime = timer.getDeltaTime();
                        	        	
        	gameController.update(deltaTime);
        	Display.update();
        	Display.sync(60);
        }    
        
        Display.destroy();
    }
	
	/**
	 * Initialize LWJGL
	 * 
	 */
	private void init() {
		try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
	}
	
}
