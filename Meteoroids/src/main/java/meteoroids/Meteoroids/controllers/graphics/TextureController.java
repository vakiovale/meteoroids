package meteoroids.Meteoroids.controllers.graphics;

import meteoroids.Meteoroids.utilities.RandomGenerator;

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
    
    /** Planet textures */
    private Texture planetEarth;
    private Texture planetMercury;
    private Texture planetVenus;
    private Texture planetMars;
    private Texture planetJupiter;
    private Texture planetNeptune;
    private Texture planetPluto;

    public TextureController() {
        shipTexture = load("resources/ship.png");
        shipThrustTexture = load("resources/ship_thrust.png");
        shipBreakTexture = load("resources/ship_break.png");
        shipFireTexture = load("resources/ship_fire.png");
        planetEarth = load("resources/earth.png");
        planetMercury = load("resources/mercury.png");
        planetVenus = load("resources/venus.png");
        planetMars = load("resources/mars.png");
        planetJupiter = load("resources/jupiter.png");
        planetNeptune = load("resources/neptune.png");
        planetPluto = load("resources/pluto.png");
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
    
    public TextureDrawer getRandomPlanet() {
        int planet = (int)(RandomGenerator.random()*7);
        switch (planet) {
            default:
            case 0:
                return loadDrawer(planetEarth);
            case 1:
                return loadDrawer(planetMercury);
            case 2:
                return loadDrawer(planetVenus);
            case 3:
                return loadDrawer(planetMars);
            case 4:
                return loadDrawer(planetJupiter);
            case 5:
                return loadDrawer(planetNeptune);
            case 6:
                return loadDrawer(planetPluto);
        }
    }
    
    public TextureDrawer getEarthTexture() {
        return loadDrawer(planetEarth);
    }
    
    public TextureDrawer getMercuryTexture() {
        return loadDrawer(planetMercury);
    }
    
    public TextureDrawer getVenusTexture() {
        return loadDrawer(planetVenus);
    }
    
    public TextureDrawer getMarsTexture() {
        return loadDrawer(planetMars);
    }
    
    public TextureDrawer getJupiterTexture() {
        return loadDrawer(planetJupiter);
    }
    
    public TextureDrawer getNeptuneTexture() {
        return loadDrawer(planetNeptune);
    }
    
    public TextureDrawer getPlutoTexture() {
        return loadDrawer(planetPluto);
    }
    
}
