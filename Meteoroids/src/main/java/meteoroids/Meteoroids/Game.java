package meteoroids.Meteoroids;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * 
 * Main Game Loop that handles Meteoroids game
 * 
 * @author vpyyhtia
 *
 */
public class Game {
	
	public Game() {
		// nothing yet
	}
	
	/**
	 * Starts the game
	 * 
	 */
	public void start() {
        
		// TODO: replace LWJGL example test loop with REAL one
		try {
            Display.setDisplayMode(new DisplayMode(1024,768));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
         
        while (!Display.isCloseRequested()) {
            Display.update();
        }    
        Display.destroy();
    }
}
