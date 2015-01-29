package meteoroids.Meteoroids.gameobjects;

/**
 * Interface for objects that are drawn to the screen
 * 
 * @author vpyyhtia
 *
 */
public interface Drawable {

    /**
     * Draw object to the screen
     * 
     */
    public void draw();
    
    /**
     * Object ID.
     * 
     * @return id
     */
    public int getID();
}
