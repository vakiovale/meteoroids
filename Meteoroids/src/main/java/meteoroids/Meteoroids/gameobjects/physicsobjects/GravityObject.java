package meteoroids.Meteoroids.gameobjects.physicsobjects;

import javax.vecmath.Vector2f;

/**
 * Abstract Gravity class for objects with gravity.
 * 
 * @author vpyyhtia
 *
 */
public abstract class GravityObject extends PhysicsObject {

    public GravityObject(float posX, float posY, float mass) {
        super(posX, posY, mass);
    }
    
    /**
     * Apply gravity to other PhysicObject.
     * 
     * @param other object that is going to be affected by the gravity field
     */
    public void addGravityForce(PhysicsObject other) {
        Vector2f gravityVector = this.getPosition();
        gravityVector.sub(other.getPosition());
        float lengthSquared = gravityVector.lengthSquared();
        gravityVector.normalize();
        gravityVector.scale((this.mass*other.mass)/lengthSquared);
        other.addForce(gravityVector);
    }
}
