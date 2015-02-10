package meteoroids.Meteoroids.controllers.graphics;

import java.io.IOException;

import meteoroids.Meteoroids.controllers.utilities.ErrorController;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Loads OpenGL texture for objects.
 * 
 * @author vpyyhtia
 *
 */
public class MeteoroidsTextureLoader {

    private Texture texture;
    
    public MeteoroidsTextureLoader(final String PATH_TO_TEXTURE) {
        try {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(PATH_TO_TEXTURE));
        } catch (IOException e) {
            ErrorController.textureLoadError(PATH_TO_TEXTURE);
            System.exit(0);
        } catch (RuntimeException e) {
            ErrorController.textureLoadError(PATH_TO_TEXTURE);
            System.exit(0);
        }
        
    }
    
    public Texture texture() {
        return texture;
    }
    
}
