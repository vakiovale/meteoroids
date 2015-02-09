package meteoroids.Meteoroids.controllers.input;

import org.lwjgl.input.Keyboard;

/**
 * Text writer. Uses LWJGL poll system to write text.
 * 
 * @author vpyyhtia
 *
 */
public class PollWriter {

    /**
     * Writes text to StringBuilder object. Works with letters A-Z.
     * 
     * @param text to be changed
     * @return text
     */
    public static StringBuilder poll(StringBuilder text) {
        if(text == null) {
            text = new StringBuilder();
        }
        
        while(Keyboard.next()) {
            int key = Keyboard.getEventKey();
            if(key == 30) {
                if(!Keyboard.getEventKeyState()) {
                    text.append(Keyboard.getKeyName(key));
                    return text;
                }
            }
            if(key == Keyboard.KEY_BACK) {
                if(!Keyboard.getEventKeyState()) {      
                    text.substring(0, text.length()-1);
                    return text;
                }
            }
        }
        return text;
    }
}
