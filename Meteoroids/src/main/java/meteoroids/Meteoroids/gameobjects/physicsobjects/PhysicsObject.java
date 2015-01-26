package meteoroids.Meteoroids.gameobjects.physicsobjects;

import javax.vecmath.Vector2d;

import meteoroids.Meteoroids.gameobjects.DUGameObject;
import meteoroids.Meteoroids.gameobjects.IPosition;

/**
 * Abstract game object that is affected by the law of physics.
 * Extends DUGameObject so the PhysicsObject is always drawable
 * and updateable.
 * 
 * @author vpyyhtia
 *
 */
public abstract class PhysicsObject extends DUGameObject implements IPosition {
		
	protected Vector2d sumOfForces;
	
	protected Vector2d acceleration;
	protected Vector2d velocity;
	
	protected double mass;
	
	public PhysicsObject() {
		this(new Vector2d(0.0, 0.0), new Vector2d(0.0, 0.0), new Vector2d(0.0, 0.0), new Vector2d(0.0, 0.0), 1.0);
	}
	
	public PhysicsObject(Vector2d sumOfForces, Vector2d position, Vector2d acceleration, Vector2d velocity, double mass) {
	    this.sumOfForces = sumOfForces;
	    this.position = position;
	    this.acceleration = acceleration;
	    this.velocity = velocity;
	    
	    this.mass = mass <= 1.0 ? 1.0 : mass;
	}
	
	public PhysicsObject(double posX, double posY) {
        this(new Vector2d(0.0, 0.0), new Vector2d(posX, posY), new Vector2d(0.0, 0.0), new Vector2d(0.0, 0.0), 1.0);
	}
	
	public PhysicsObject(double mass) {
        this(new Vector2d(0.0, 0.0), new Vector2d(0.0, 0.0), new Vector2d(0.0, 0.0), new Vector2d(0.0, 0.0), mass);
	}
	
	public PhysicsObject(double posX, double posY, double mass) {
        this(new Vector2d(0.0, 0.0), new Vector2d(posX, posY), new Vector2d(0.0, 0.0), new Vector2d(0.0, 0.0), mass);
	}
	
	@Override
	public void update(double deltaTime) {
	    //acceleration = sumOfForces / mass;
	}
	
	/**
	 * Add a force vector to the sum of forces.
	 * 
	 * @param force 2d vector
	 */
	public void addForce(Vector2d force) {
		sumOfForces.add(force);
	}
	
	/**
	 * Returns the force vector that is affected to the object.
	 * 
	 * @return Vector2d sum of forces
	 */
	public Vector2d getForces() {
		return (Vector2d)sumOfForces.clone();
	}
	
	/**
	 * Clear sum of forces.
	 * 
	 */
	public void clearForces() {
		sumOfForces.set(0.0, 0.0);
	}
	
	/**
	 * Mass of the object
	 * 
	 * @return mass
	 */
	public double getMass() {
	    return mass;
	}
}
