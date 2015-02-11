package meteoroids.Meteoroids.gameobjects;

import java.util.ArrayList;
import java.util.List;

import meteoroids.Meteoroids.utilities.RandomGenerator;

/**
 * Star field.
 * 
 * @author vpyyhtia
 *
 */
public class StarField extends DrawableGameObject {

    List<Star> stars;
    
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
