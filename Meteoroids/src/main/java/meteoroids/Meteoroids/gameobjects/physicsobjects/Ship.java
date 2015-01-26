package meteoroids.Meteoroids.gameobjects.physicsobjects;

/**
 * Player's ship class.
 * 
 * @author vpyyhtia
 *
 */
public class Ship extends PhysicsObject {

	public Ship() {
		super(100.0);
	}
	
	@Override
	public void draw() {
	    System.out.println(this + " " + this.position);
	}
	
	public String toString() {
	    return "Ship";
	}
	
}
