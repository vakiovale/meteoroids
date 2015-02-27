package meteoroids.Meteoroids.controllers.gameobjects;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.gameobjects.StarField;

/**
 * Controller for handling and drawing a star field to the background.
 * 
 * @author vpyyhtia
 *
 */
public class StarController implements Controller {
    
    @Override
    public void update(float deltaTime) {
        // Do nothing
    }

    /**
     * Generates a new StarField.
     * 
     * @return starField
     */
    public StarField generateStarField(float width, float height, int numberOfStars) {
        StarField starField = new StarField(width, height, numberOfStars);
        return starField;
    }

}
