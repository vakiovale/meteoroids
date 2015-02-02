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
     * needed.
     * 
     * @param objects
     * @param deltaTime
     */
    public void update(List<PhysicsObject> objects, float deltaTime) {
        killed.clear();
        if(objects == null) return;
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
        // Do nothing
    }

    /**
     * Check if two BoundingSphere objects collide.
     * 
     * @param bsA
     * @param bsB
     * @return
     */
    public boolean checkSphereCollision(BoundingSphere bsA, BoundingSphere bsB) {
        Vector2f center = bsB.getPosition();
        center.sub(bsA.getPosition());
        float distanceSquared = center.dot(center);
        float touchingDistance = (bsA.getRadius() + bsB.getRadius())
                * (bsA.getRadius() + bsB.getRadius());
        if(distanceSquared <= touchingDistance) {
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

        // TODO: testing one version of collision response
        // this has to be improved !
        Vector2f difference = bsA.getPosition();
        difference.sub(bsB.getPosition());
        float length = difference.length();
        Vector2f midway = (Vector2f)difference.clone();
        midway.scale(((bsA.getRadius() + bsB.getRadius())-length)/length);
        
        PhysicsObject phA = (PhysicsObject)bsA;
        PhysicsObject phB = (PhysicsObject)bsB;
        
        Vector2f positionA = phA.getPosition();
        Vector2f positionB = phB.getPosition();
        
        Vector2f midwaytmp = (Vector2f)midway.clone();
        midwaytmp.scale(phA.getInverseMass() / (phA.getInverseMass()+phB.getInverseMass()));
        positionA.add(midwaytmp);
        midwaytmp = (Vector2f)midway.clone();
        midwaytmp.scale(phB.getInverseMass() / (phA.getInverseMass() + phB.getInverseMass()));
        positionB.add(midwaytmp);
        
        phA.setPosition(positionA);
        phB.setPosition(positionB);
        
        Vector2f velocity = bsA.getVelocity();
        velocity.sub(bsB.getVelocity());
        midwaytmp = (Vector2f)midway.clone();
        float vn = velocity.dot(midwaytmp);
        
        if(vn > 0.0f) return;
        
        float impulse = (-(1.0f) * vn) / (phA.getInverseMass() + phB.getInverseMass());
        Vector2f impulseVector = (Vector2f)midway.clone();
        impulseVector.scale(impulse);
        
        Vector2f velocitytmp = (Vector2f)phA.getVelocity();
        Vector2f impulsetmp = (Vector2f)impulseVector.clone();
        impulsetmp.scale(phA.getInverseMass());
        velocitytmp.add(impulsetmp);
        phA.setVelocity(velocitytmp);
        
        velocitytmp = (Vector2f)phB.getVelocity();
        impulsetmp = (Vector2f)impulseVector.clone();
        impulsetmp.scale(phB.getInverseMass());
        velocitytmp.sub(impulsetmp);
        phB.setVelocity(velocitytmp);
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
