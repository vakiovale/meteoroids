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
    public void accelerate(float amount, double deltaTime);

    /**
     * Rotate object.
     * 
     * @param angle of rotation
     * @param deltaTime
     */
    public void rotate(float angle, double deltaTime);
}
