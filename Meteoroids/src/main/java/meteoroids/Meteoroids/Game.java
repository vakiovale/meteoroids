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
class Game {
	
	private static double timeFactor = 1.0;
	private GameController gameController;
	private GameTimer timer;

	Game() {
		gameController = new GameController();
		timer = new GameTimer();
	}
	
	/**
	 * Starts the main game loop. 
	 * Uses GameController to update the game world.
	 * 
	 */
	void start() {
              
		if(!init()) {
			// TODO: Error
			System.exit(0);
		}
		
		int deltaTime = timer.getDeltaTime();		
		
		// Game loop
        while (!Display.isCloseRequested()) {
        	deltaTime = timer.getDeltaTime();
                        	        	
        	gameController.update(deltaTime);
        	Display.update();
        	Display.sync(60);
        }            
        
        destroy();
    }
	
	/**
	 * Display destroy
	 * 
	 * @return true
	 *  
	 */
	boolean destroy() {
        Display.destroy();
        return true;
	}
	
	/**
	 * Initialize LWJGL
	 * 
	 * @return true if initializing LWJGL successfully
	 * 
	 */
	boolean init() {
		try {
            Display.setDisplayMode(new DisplayMode(800,600));
            Display.create();
        } catch (LWJGLException e) {
        	return false;
        }
		return true;
	}
	
	double getTimeFactor() {
		return timeFactor;
	}
	
}
