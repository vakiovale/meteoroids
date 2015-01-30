package meteoroids.Meteoroids.gameobjects.physicsobjects;

import javax.vecmath.Vector2f;

/**
 * Physics objects with a geometry of BoundingSphere
 * 
 * @author vpyyhtia
 *
 */
public interface BoundingSphere {

    /**
     * Add force to the object.
     * 
     * @param x
     * @param y
     */
    public void addForce(float x, float y);

    /**
     * Radius of the object.
     * 
     * @return radius
     */
    public float getRadius();

    /**
     * Position of the object.
     * 
     * @return Vector2f position
     */
    public Vector2f getPosition();

    /**
     * Velocity of the object.
     * 
     * @return Vector2f velocity
     */
    public Vector2f getVelocity();

    /**
     * Set velocity for the object.
     * 
     * @param velocity
     */
    public void setVelocity(Vector2f velocity);
}
