package meteoroids.Meteoroids.controllers.utilities;

/**
 * Handles the error messages.
 * 
 * @author vpyyhtia
 *
 */
public class ErrorController {
    
    private String errorMessage;
    private String exitMessage;
    
    public ErrorController() {
        errorMessage = "ERROR:";
        exitMessage =  "Exiting now...";
    }
    
    public void error() {
        System.out.println(errorMessage);
    }
    
    public void exit() {
        System.out.println(exitMessage);
    }
    
    private void print(String message) {
        error();
        System.out.println(message);
        exit();
    }
    
    /**
     * Print error message when failing to initalize display, LWJGL or OpenGL.
     */
    public void displayError() {
        print("Meteors have crashed your village and broke the display! "
            + "Playing is not possible right now :(\n"
            + "Please check your graphics drivers for OpenGL support.");
    }

    /**
     * Print error message when failing to load fonts.
     */
    public void fontResourceError(String pathToFonts) {
        print("Oops! Meteors have crashed your village and destroyed your resources!\n"
            + "We could not find the fonts (" + pathToFonts +") needed to "
            + "display text on the screen. :(\nPlease reinstall the game and "
            + "don't forget the fonts!");
    }
    
}
