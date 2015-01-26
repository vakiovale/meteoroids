package meteoroids.Meteoroids.gameobjects;

import javax.vecmath.Vector2d;

/**
 * Basic interface for objects with a position
 * 
 * @author vpyyhtia
 *
 */
public interface IPosition {
    
    /**
     * Get 2D position of the object.
     * 
     * @return Vector2d
     */
    public Vector2d getPosition();
    
    /**
     * Set position.
     * 
     * @param position Vector2d
     */
    public void setPosition(Vector2d position);
    
    /**
     * Set position.
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public void setPosition(double x, double y);
    
    /**
     * Returns x position.
     * 
     * @return x
     */
    public double getX();
    
    /**
     * Returns y position.
     *  
     * @return y
     */
    public double getY();
}
