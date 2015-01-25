package meteoroids.Meteoroids.gameobjects.physicobjects;

import javax.vecmath.Vector2d;

import meteoroids.Meteoroids.gameobjects.DUGameObject;

/**
 * Abstract game object that is affected by the law of physics.
 * Extends DUGameObject so the PhysicsObject is always drawable
 * and updateable.
 * 
 * @author vpyyhtia
 *
 */
public abstract class PhysicsObject extends DUGameObject {
		
	protected Vector2d sumOfForces;
	
	protected Vector2d acceleration;
	protected Vector2d velocity;
	
	protected double mass;
	
	public PhysicsObject() {
		sumOfForces = new Vector2d(0.0, 0.0);
		
		position = new Vector2d(0.0, 0.0);
		acceleration = new Vector2d(0.0, 0.0);
		velocity = new Vector2d(0.0, 0.0);
		
		mass = 0.0;		
	}
	
	public PhysicsObject(double posX, double posY) {
		this();
		position.set(posX, posY);
	}
	
	public PhysicsObject(double mass) {
		this();
		this.mass = mass;
	}
	
	public PhysicsObject(double posX, double posY, double mass) {
		this();
		position.set(posX, posY);
		this.mass = mass;
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
}
