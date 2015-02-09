package meteoroids.Meteoroids.utilities;

import java.util.Random;

/**
 * Handles randomizing stuff.
 * 
 * @author vpyyhtia
 *
 */
public class RandomGenerator {

    
    /** Generates random numbers */
    public static Random random = new Random();
    
    /**
     * Random float between -0.5f and 0.5f
     * 
     * @return random float number
     */
    public static float randomPlusMinus() {
        return ((float)random.nextDouble() - 0.5f);
    }
    
    /**
     * Random float between 0.0f and 1.0f
     * 
     * @return random float number
     */
    public static float random() {
        return (float)random.nextDouble();
    }
    
}
