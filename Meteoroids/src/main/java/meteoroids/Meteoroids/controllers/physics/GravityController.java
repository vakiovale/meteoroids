package meteoroids.Meteoroids.controllers.physics;

import java.util.List;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.gameobjects.physicsobjects.GravityObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;

/**
 * Applies gravity to the PhysicsObjects.
 * 
 * @author vpyyhtia
 *
 */
public class GravityController implements Controller {

    public void update(List<GravityObject> gravityObjects,
            List<PhysicsObject> objects, float deltaTime) {
        
        for (int i = 0; i < gravityObjects.size(); i++) {
            for (int j = 0; j < objects.size(); j++) {
                gravityObjects.get(i).addGravityForce(objects.get(j));
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        update(null, null, deltaTime);
    }

}
