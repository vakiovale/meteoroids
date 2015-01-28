package meteoroids.Meteoroids.gameobjects.physicsobjects;

import javax.vecmath.Vector2f;

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
		
	protected Vector2f sumOfForces;
	
	protected Vector2f acceleration;
	protected Vector2f velocity;
	
	// TODO: Instead of the actual mass, it could be wiser to use an inverse of mass.
	// The mass itself is rarely used and it's inverse mass is more common.
	// One way could be to add also an inverse mass variable;
	protected float mass;
	private float inverseMass;
	
	public PhysicsObject() {
		this(new Vector2f(0.0f, 0.0f), new Vector2f(0.0f, 0.0f), new Vector2f(0.0f, 0.0f), new Vector2f(0.0f, 0.0f), 1.0f);
	}
	
	public PhysicsObject(Vector2f sumOfForces, Vector2f position, Vector2f acceleration, Vector2f velocity, float mass) {
	    this.sumOfForces = sumOfForces;
	    this.position = position;
	    this.acceleration = acceleration;
	    this.velocity = velocity;
	    
	    this.mass = mass <= 1.0f ? 1.0f : mass; // Don't allow mass lower than 1.0
	    this.inverseMass = 1/mass;
	}
	
	public PhysicsObject(float posX, float posY) {
        this(new Vector2f(0.0f, 0.0f), new Vector2f(posX, posY), new Vector2f(0.0f, 0.0f), new Vector2f(0.0f, 0.0f), 1.0f);
	}
	
	public PhysicsObject(float mass) {
        this(new Vector2f(0.0f, 0.0f), new Vector2f(0.0f, 0.0f), new Vector2f(0.0f, 0.0f), new Vector2f(0.0f, 0.0f), mass);
	}
	
	public PhysicsObject(float posX, float posY, float mass) {
        this(new Vector2f(0.0f, 0.0f), new Vector2f(posX, posY), new Vector2f(0.0f, 0.0f), new Vector2f(0.0f, 0.0f), mass);
	}
	
	/**
	 * Update object's position, velocity and acceleration with current forces.
	 * Clears forces at the end of the method.
	 * 
	 */
	@Override
	public void update(float deltaTime) {  
	    // Calculate acceleration (a = F/m)
		sumOfForces.scale(inverseMass);
	    acceleration = sumOfForces;
	    	    
	    // Calculate velocity with delta time
	    Vector2f velocityAdd = (Vector2f)acceleration.clone();
	    velocityAdd.scale(deltaTime);	    
	    velocity.add(velocityAdd);
	    
	    // Calculate position with delta time
	    Vector2f positionAdd = (Vector2f)velocity.clone();
	    positionAdd.scale(deltaTime);	    
	    position.add(positionAdd);
	    
	    clearForces();
	}
	
	/**
	 * Add a force vector to the sum of forces.
	 * 
	 * @param force 2d vector
	 */
	public void addForce(Vector2f force) {
		sumOfForces.add(force);
	}
	
	/**
	 * Add a force vector to the sum of forces.
	 * 
	 * @param x 
	 * @param y
	 */
	public void addForce(float x, float y) {
		sumOfForces.add(new Vector2f(x, y));
	}
	
	/**
	 * Returns the force vector that is affected to the object.
	 * 
	 * @return Vector2f sum of forces
	 */
	public Vector2f getForces() {
		return (Vector2f)sumOfForces.clone();
	}
	
	/**
	 * Clear sum of forces.
	 * 
	 */
	public void clearForces() {
		sumOfForces.set(0.0f, 0.0f);
	}
	
	/**
	 * Mass of the object.
	 * 
	 * @return mass
	 */
	public float getMass() {
	    return mass;
	}
	
	/**
	 * Velocity of the object
	 * 
	 * @return velocity
	 */
	public Vector2f getVelocity() {
		return (Vector2f)velocity.clone();
	}
}
