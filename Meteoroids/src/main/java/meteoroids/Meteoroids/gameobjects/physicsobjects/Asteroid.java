package meteoroids.Meteoroids.gameobjects.physicsobjects;

/**
 * Asteroid class.
 * 
 * @author vpyyhtia
 *
 */
public class Asteroid extends PhysicsObject {

	public Asteroid() {
		super(5.0, 5.0, 50.0);
	}
	
    @Override
    public void draw() {
        System.out.println(this + " " + this.position);
    }
	
	public String toString() {
	    return "Asteroid";
	}
}
