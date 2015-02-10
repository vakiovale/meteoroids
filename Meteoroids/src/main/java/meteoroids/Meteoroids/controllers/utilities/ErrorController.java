package meteoroids.Meteoroids.controllers.utilities;

/**
 * Handles the error messages.
 * 
 * @author vpyyhtia
 *
 */
public class ErrorController {
    
    private static String errorMessage = "ERROR:";
    private static String exitMessage =  "Exiting now...";;
      
    private static void error() {
        System.out.println(errorMessage);
    }
    
    private static void exit() {
        System.out.println(exitMessage);
    }
    
    private static void print(String message) {
        error();
        System.out.println(message);
        exit();
    }
    
    /**
     * Print error message when failing to initalize display, LWJGL or OpenGL.
     */
    public static void displayError() {
        print("Meteors have crashed your village and broke the display! "
            + "Playing is not possible right now :(\n"
            + "Please check your graphics drivers for OpenGL support.");
    }

    /**
     * Print error message when failing to load fonts.
     */
    public static void fontResourceError(String pathToFonts) {
        print("Oops! Meteors have crashed your village and destroyed your resources!\n"
            + "We could not find the fonts (" + pathToFonts +") needed to "
            + "display text on the screen. :(\nPlease reinstall the game and "
            + "don't forget the fonts!");
    }
    
    /**
     * Print error for high scores loading errors.
     */
    public static void highScoresFileError() {
        print("Oh my god! Meteors destroyed your village and all your high scores are lost!\n"
            + "Damn those meteors! Please reinstall the game and check that highscores file exists!");
    }

    /**
     * Print error for high scores saving errors.
     */
    public static void highScoresSaveFileError() {
        print("Oh my god! Meteors destroyed your village and your current high score is lost!\n"
            + "Damn those meteors! For some reason your high score couldn't be saved :(");        
    }

    /**
     * Print error message when failing to load textures.
     */
    public static void textureLoadError(final String PATH_TO_TEXTURE) {
        print("Oops! Meteors have crashed your village and destroyed your nice looking textures!\n"
            + "We could not find the file (" + PATH_TO_TEXTURE +") needed to "
            + "display textures. :(\nPlease reinstall the game and "
            + "don't forget the textures!");        
    }
    
}
