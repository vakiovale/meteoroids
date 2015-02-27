package meteoroids.Meteoroids.controllers.physics;

import java.util.List;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.GravityObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;

/**
 * PhysicsController updates all the physics. Moves and controls 
 * the PhysicsObjects and GravityObjects and update them.
 * 
 * @author vpyyhtia
 *
 */
public class PhysicsController implements Controller {

    private GravityController gravityController;
    private CollisionController collisionController;

    public PhysicsController() {
        this.gravityController = new GravityController();
        this.collisionController = new CollisionController();
    }

    /**
     * Update the movement of PhysicsObjects
     * 
     * @param gravityObjects all GravityObjects
     * @param objects all PhysicsObjects
     * @param deltaTime
     */
    public void update(List<GravityObject> gravityObjects,
            List<PhysicsObject> objects, float deltaTime) {
        if(gravityObjects == null || objects == null) return;
        gravityController.update(gravityObjects, objects, deltaTime);
        collisionController.update(objects, deltaTime);
    }

    public List<GameObject> getKilled() {
        return collisionController.getKilled();
    }

    @Override
    public void update(float deltaTime) {
        // Do nothing
    }

}
