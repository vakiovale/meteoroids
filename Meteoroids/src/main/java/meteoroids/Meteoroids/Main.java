package meteoroids.Meteoroids;

import meteoroids.Meteoroids.controllers.utilities.ErrorController;

/**
 * Main application - Meteoroids
 *
 * @author vpyyhtia
 * 
 */
public class Main {

    public static ErrorController error;
    
    public static void main(String[] args) {
        System.out.println("Starting Meteoroids!");
        error = new ErrorController();

        Game game = new Game();
        game.start();

        System.out.println("Exiting Meteoroids...");
    }

}
