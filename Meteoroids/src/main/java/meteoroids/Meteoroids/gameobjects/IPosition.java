package meteoroids.Meteoroids.gameobjects;

import javax.vecmath.Vector2f;

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
     * @return Vector2f
     */
    public Vector2f getPosition();

    /**
     * Set position.
     * 
     * @param position Vector2f
     */
    public void setPosition(Vector2f position);

    /**
     * Set position.
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public void setPosition(float x, float y);

    /**
     * Returns x position.
     * 
     * @return x
     */
    public float getX();

    /**
     * Returns y position.
     * 
     * @return y
     */
    public float getY();
}
