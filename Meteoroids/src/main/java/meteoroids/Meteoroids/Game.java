package meteoroids.Meteoroids;

import meteoroids.Meteoroids.controllers.GameController;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * Main Game Loop that handles the game
 * 
 * @author vpyyhtia
 *
 */
public class Game {
	
	private static float timeFactor = 1.0f;
	private GameController gameController;
	private GameTimer timer;

	public Game() {
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
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		int deltaTime = timer.getDeltaTime();		
		
		// Game loop
        while (!Display.isCloseRequested()) {
            // Clear the screen and depth buffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
                     
            // set the color of the quad (R,G,B,A)
            GL11.glColor3f(0.5f,0.5f,1.0f);
            
            
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
	
	/**
	 * Time factor tells how fast game should be run.
	 * It's possible to speed up / slow down the game by 
	 * multiplying delta time with time factor.
	 * 
	 * @return time factor
	 */
	public static float getTimeFactor() {
		return timeFactor;
	}
	
}
