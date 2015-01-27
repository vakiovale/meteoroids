package meteoroids.Meteoroids;

import meteoroids.Meteoroids.controllers.GameController;
import meteoroids.Meteoroids.controllers.GraphicsController;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Main Game Loop that handles the game
 * 
 * @author vpyyhtia
 *
 */
public class Game {
	
	private static float timeFactor = 1.0f;
	public static final int WIDTH = 1300;
	public static final int HEIGHT = 400;
	private GameController gameController;
	private GraphicsController graphicsController;
	private GameTimer timer;
	
	private final int FPS = 60;

	public Game() {
		gameController = new GameController();
		graphicsController = new GraphicsController(WIDTH, HEIGHT);
		timer = new GameTimer();
	}
	
	/**
	 * Starts the main game loop. 
	 * Uses GameController to update the game world.
	 * 
	 */
	void start() {
              
		if(!init() || !graphicsController.init()) {
			// TODO: Error
			System.exit(0);
		}
		
		int deltaTime = 1/FPS * 1000;		
		
		// Game loop
        while (!Display.isCloseRequested()) {            
            deltaTime = timer.getDeltaTime();
            
            // Refresh screen
            graphicsController.update(deltaTime);
            
            // Update game world
        	gameController.update(deltaTime);
        	
        	// Draw
        	graphicsController.draw(gameController.getDrawables());
        	
        	Display.update();
        	Display.sync(FPS);
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
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
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
