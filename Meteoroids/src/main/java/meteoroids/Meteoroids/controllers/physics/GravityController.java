package meteoroids.Meteoroids.controllers.physics;

import java.util.List;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.gameobjects.physicsobjects.GravityObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;

/**
 * Applies gravity to the PhysicsObjects. Goes through 
 * all the GravityObjects and PhysicsObjects and adds a
 * force to the PhysicsObjects.
 * 
 * @author vpyyhtia
 *
 */
public class GravityController implements Controller {

    /**
     * Add a force / forces to the PhysicsObjects
     * 
     * @param gravityObjects that has a gravity
     * @param objects to be effected by gravity
     * @param deltaTime
     */
    public void update(List<GravityObject> gravityObjects,
            List<PhysicsObject> objects, float deltaTime) {

        for(int i = 0; i < gravityObjects.size(); i++) {
            for(int j = 0; j < objects.size(); j++) {
                gravityObjects.get(i).addGravityForce(objects.get(j));
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        update(null, null, deltaTime);
    }

}
