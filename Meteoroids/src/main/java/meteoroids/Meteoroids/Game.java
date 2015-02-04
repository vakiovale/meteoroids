package meteoroids.Meteoroids;

import meteoroids.Meteoroids.controllers.GameController;
import meteoroids.Meteoroids.controllers.graphics.GraphicsController;
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
    
    public static final int WIDTH = 1900;
    public static final int HEIGHT = 1000;

    private final int FPS = 60;
    
    private GameController gameController;
    private GraphicsController graphicsController;
    
    private GameTimer timer;
    
    public Game() {
        graphicsController = new GraphicsController(Game.WIDTH, Game.HEIGHT);
        gameController = new GameController(graphicsController);
        timer = new GameTimer();
    }

    /**
     * Starts the main game loop. Uses GameController to update the game world.
     * 
     */
    void start() {

        if(!init() || !graphicsController.init()) {
            // TODO: Error
            System.exit(0);
        }

        int deltaTime = 1 / FPS * 1000;

        // Game loop
        while(!Display.isCloseRequested()) {
            deltaTime = timer.getDeltaTime();          
            gameController.update(deltaTime);
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

}
