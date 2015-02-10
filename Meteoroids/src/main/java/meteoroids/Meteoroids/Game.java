package meteoroids.Meteoroids;

import java.io.IOException;

import meteoroids.Meteoroids.controllers.GameController;
import meteoroids.Meteoroids.controllers.graphics.GraphicsController;
import meteoroids.Meteoroids.controllers.graphics.TextureController;
import meteoroids.Meteoroids.controllers.utilities.ErrorController;
import meteoroids.Meteoroids.controllers.utilities.TextHandler;
import meteoroids.Meteoroids.utilities.GameTimer;
import meteoroids.Meteoroids.utilities.RandomGenerator;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

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
    
    /** Random generator */
    public static RandomGenerator random;
    
    /** Frames per second - ALTERING THIS WILL ALSO AFFECT THE GAME PHYSICS*/
    private final int FPS = 60;
    
    /** GameController for conrolling all the game objects and game states */
    private GameController gameController;
    
    /** GraphicsControlelr for controlling initializing graphics and drawing OpenGL object */
    private GraphicsController graphicsController;
    
    /** TextureController */
    public static TextureController textureController; 
    
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
        textureController = null;
        RandomGenerator.randomPlusMinus(); // init random generator
    }

    /**
     * Starts the main game loop. Uses GameController to update the game world.
     * 
     */
    void start() {

        // Initialize LWJGL and OpenGL
        if(!init() || !graphicsController.init()) {
            ErrorController.displayError();
            System.exit(0);
        }       
        
        // Initialize font resources
        if(!TextHandler.initFont(FONT_PATH)) {
            ErrorController.fontResourceError(FONT_PATH);
            System.exit(0);
        }
        
        // Load textures
        textureController = new TextureController();
        
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
