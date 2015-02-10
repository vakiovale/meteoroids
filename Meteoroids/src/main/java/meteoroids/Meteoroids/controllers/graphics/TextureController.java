package meteoroids.Meteoroids.controllers.graphics;

import org.newdawn.slick.opengl.Texture;

/**
 * Loads textures and handles all the textures.
 * 
 * @author vpyyhtia
 *
 */
public class TextureController {

    private MeteoroidsTextureLoader loader;
    
    /** Ship textures */
    private Texture shipTexture;
    private Texture shipThrustTexture;
    private Texture shipBreakTexture;
    private Texture shipFireTexture;

    public TextureController() {
        shipTexture = load("resources/ship.png");
        shipThrustTexture = load("resources/ship_thrust.png");
        shipBreakTexture = load("resources/ship_break.png");
        shipFireTexture = load("resources/ship_fire.png");
    }
    
    private Texture load(String path) {
        loader = new MeteoroidsTextureLoader(path);
        return loader.texture();
    }
    
    private TextureDrawer loadDrawer(Texture texture) {
        return new TextureDrawer(texture);
    }

    public TextureDrawer getShipTexture() {
        return loadDrawer(shipTexture);
    }    
    
    public TextureDrawer getShipThrustTexture() {
        return loadDrawer(shipThrustTexture);
    }
    
    public TextureDrawer getShipBreakTexture() {
        return loadDrawer(shipBreakTexture);
    }

    public TextureDrawer getShipFireTexture() {
        return loadDrawer(shipFireTexture);
    }
    
}
