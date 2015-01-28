package meteoroids.Meteoroids;

/**
 * Main application - Meteoroids
 *
 * @author vpyyhtia
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
