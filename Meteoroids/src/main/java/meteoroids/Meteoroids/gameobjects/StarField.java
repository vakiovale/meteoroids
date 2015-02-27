package meteoroids.Meteoroids.gameobjects;

import java.util.ArrayList;
import java.util.List;

import meteoroids.Meteoroids.utilities.RandomGenerator;

/**
 * Star field which creates multiple stars in it.
 * 
 * @author vpyyhtia
 *
 */
public class StarField extends DrawableGameObject {

    List<Star> stars;
    
    /**
     * Constructor for StarField. StarField is built from multiple stars.
     * 
     * @param width of the star field
     * @param height of the star field
     * @param numberOfStars in the star field
     */
    public StarField(float width, float height, int numberOfStars) {
        stars = new ArrayList<>();
        
        for(int i = 0; i < numberOfStars; i++) {
            stars.add(new Star(RandomGenerator.random(),
                    RandomGenerator.randomPlusMinus()*2*width,
                    RandomGenerator.randomPlusMinus()*2*height));
        }
    }
    
    @Override
    public void draw() {
        for(Star star : stars) {
            star.draw();
        }
    }
}
