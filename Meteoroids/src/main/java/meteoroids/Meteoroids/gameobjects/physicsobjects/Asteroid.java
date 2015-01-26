package meteoroids.Meteoroids.gameobjects.physicsobjects;

/**
 * Asteroid class.
 * 
 * @author vpyyhtia
 *
 */
public class Asteroid extends PhysicsObject {

	public Asteroid() {
		super(Math.random() * 20.0 - 10.0, 
		      Math.random() * 20.0 - 10.0, 
		      500.0);
	}
	
    @Override
    public void draw() {
        System.out.println(this + " " + this.position);
    }
	
	public String toString() {
	    return "Asteroid";
	}
}
