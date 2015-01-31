package meteoroids.Meteoroids.gameobjects;

/**
 * Interface for movable objects.
 * 
 * @author vpyyhtia
 *
 */
public interface Movable {

    /**
     * Accelerate object.
     * 
     * @param amount
     * @param deltaTime
     */
    public void accelerate(float amount, float deltaTime);

    /**
     * Rotate object.
     * 
     * @param angle of rotation
     * @param deltaTime
     */
    public void rotate(float angle, float deltaTime);
    
    public float getRotation();
}
