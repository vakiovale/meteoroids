package meteoroids.Meteoroids;

import meteoroids.Meteoroids.controllers.GameController;
import meteoroids.Meteoroids.controllers.graphics.GraphicsController;
import meteoroids.Meteoroids.controllers.utilities.TextHandler;
import meteoroids.Meteoroids.utilities.GameTimer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

/**
 * Main Game Loop that handles the game
 * 
 * @author vpyyhtia
 *
 */
public class Game {
    
    /** Width of the screen */
    public static final int WIDTH = 1920;
    
    /** Height of the screen */
    public static final int HEIGHT = 1080;
    
    /** Frames per second - ALTERING THIS WILL ALSO AFFECT THE GAME PHYSICS*/
    private final int FPS = 60;
    
    /** GameController for conrolling all the game objects and game states */
    private GameController gameController;
    
    /** GraphicsControlelr for controlling initializing graphics and drawing OpenGL object */
    private GraphicsController graphicsController;
    
    /** gamOver variable will exit the game when set to true */
    private static boolean gameOver = false;
    
    /** Path to font */
    private final String FONT_PATH = "/resources/fonts/Munro.ttf";
    
    /** Handles calculating delta time and game time */
    private GameTimer timer;
    
    /**
     * Start Meteoroids!
     * 
     */
    public Game() {
        graphicsController = new GraphicsController(Game.WIDTH, Game.HEIGHT);
        gameController = null;
        timer = new GameTimer();
    }

    /**
     * Starts the main game loop. Uses GameController to update the game world.
     * 
     */
    void start() {

        // Initialize LWJGL and OpenGL
        if(!init() || !graphicsController.init()) {
            Main.error.displayError();
            System.exit(0);
        }       
        
        // Initialize font resources
        if(!TextHandler.initFont(FONT_PATH)) {
            Main.error.fontResourceError(FONT_PATH);
            System.exit(0);
        }
        
        gameController = new GameController(graphicsController);
        int deltaTime = 1 / FPS * 1000;
                
        // Game loop
        while(!Display.isCloseRequested() && !gameOver) {
            deltaTime = timer.getDeltaTime();
                      
            // Refresh screen
            graphicsController.update(deltaTime);
            
            // Update game world
            gameController.update(deltaTime);
            
            Display.update();
            Display.sync(FPS);
        }
        
        destroy();
    }
    
    /**
     * Quit game.
     * 
     */
    public static void gameOver() {
        gameOver = true;
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
            //Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setFullscreen(true);
            Display.setResizable(true);
            Display.create();
        } catch (LWJGLException e) {
            return false;
        }
        return true;
    }

}
