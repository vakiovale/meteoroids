package meteoroids.Meteoroids.controllers.gameobjects;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.gameobjects.StarField;

/**
 * Controller for handling a star field to the background.
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
     * Generates new StarField.
     * 
     * @return starField
     */
    public StarField generateStarField(float width, float height) {
        StarField starField = new StarField(width, height);
        return starField;
    }

}
