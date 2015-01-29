package meteoroids.Meteoroids.controllers;

import java.util.List;

import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.GravityObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;

public class PhysicsController implements Controller {

    private GravityController gravityController;
    private CollisionController collisionController;
    
    public PhysicsController() {
        this.gravityController = new GravityController();
        this.collisionController = new CollisionController();
    }
        
    public void update(List<GravityObject> gravityObjects,
            List<PhysicsObject> objects, float deltaTime) {
        gravityController.update(gravityObjects, objects, deltaTime);
        collisionController.update(objects, deltaTime);
    }
    
    public List<GameObject> getKilled() {
        return collisionController.getKilled();
    }
    
    @Override
    public void update(float deltaTime) {
        update(null, null, deltaTime);
    }

}