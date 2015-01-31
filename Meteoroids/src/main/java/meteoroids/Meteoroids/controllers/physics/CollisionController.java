package meteoroids.Meteoroids.controllers.physics;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector2f;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.BoundingSphere;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;

/**
 * Handles collision detection.
 * 
 * @author vpyyhtia
 *
 */
public class CollisionController implements Controller {

    private List<GameObject> killed;
    private CollisionRules collisionRules;

    public CollisionController() {
        this.killed = new ArrayList<>();
        collisionRules = new CollisionRules(killed);
    }

    /**
     * Checks collisions. Works only with objects that implements
     * BoundingSphere.
     * 
     * TODO: Implement collision detection for objects with other geometry if
     * needed. TODO: Improve collision response!
     * 
     * @param objects
     * @param deltaTime
     */
    public void update(List<PhysicsObject> objects, float deltaTime) {
        killed.clear();
        for(int i = 0; i < objects.size(); i++) {
            for(int j = i + 1; j < objects.size(); j++) {
                if(objects.get(i) instanceof BoundingSphere
                        && objects.get(j) instanceof BoundingSphere) {
                    if(checkSphereCollision((BoundingSphere)objects.get(i),
                            (BoundingSphere)objects.get(j))) {
                        collide((BoundingSphere)objects.get(i),
                                (BoundingSphere)objects.get(j));
                    }
                }
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        this.update(null, deltaTime);
    }

    private boolean checkSphereCollision(BoundingSphere bsA, BoundingSphere bsB) {
        Vector2f center = bsB.getPosition();
        center.sub(bsA.getPosition());
        float distanceSquared = center.dot(center);
        if(distanceSquared < (bsA.getRadius() + bsB.getRadius())
                * (bsA.getRadius() + bsB.getRadius())) {
            return true;
        }
        return false;
    }

    /**
     * TODO:
     * 
     * Better collision response. Now objects just reverse their velocity and
     * they get easily stuck. This is just a demo collision !!!
     * 
     */
    private void collide(BoundingSphere bsA, BoundingSphere bsB) {
        // Use a special rule for collision
        if(collisionRules.checkHits(bsA, bsB)) {
            return;
        }            

        // Default collision response
        Vector2f center = bsB.getPosition();
        center.sub(bsA.getPosition());
        center.scale(bsA.getRadius() / (bsA.getRadius() + bsB.getRadius()));
        center.add(bsA.getPosition());

        Vector2f velA = bsA.getVelocity();
        velA.negate();
        Vector2f velB = bsB.getVelocity();
        velB.negate();
        bsA.setVelocity(velA);
        bsB.setVelocity(velB);
    }

    /**
     * Killed GameObjects.
     * 
     * @return list of killed GameObjects
     */
    public List<GameObject> getKilled() {
        return killed;
    }
}
