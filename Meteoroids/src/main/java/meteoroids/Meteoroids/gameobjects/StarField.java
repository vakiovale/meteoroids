package meteoroids.Meteoroids.gameobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Star field.
 * 
 * @author vpyyhtia
 *
 */
public class StarField extends DrawableGameObject {

    List<Star> stars;
    
    public StarField(float width, float height) {
        stars = new ArrayList<>();
        
        for(int i = 0; i < 1000; i++) {
            stars.add(new Star((float)Math.random()*1,
                    (float)Math.random()*width,
                    (float)Math.random()*height));
        }
    }
    
    @Override
    public void draw() {
        for(Star star : stars) {
            star.draw();
        }
    }
}
