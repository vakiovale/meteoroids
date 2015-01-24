package meteoroids.Meteoroids;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Main application - Meteoroids
 *
 */
public class Main {
	
	public static void main(String[] args) {
        System.out.println("Starting Meteoroids!");
        
        Game game = new Game();
        game.start();
        
        System.out.println("Exiting Meteoroids...");
    }
}
