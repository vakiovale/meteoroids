package meteoroids.Meteoroids.gameobjects;

import javax.vecmath.Vector2d;

/**
 * Basic GameObject
 * 
 * @author vpyyhtia
 *
 */
public class GameObject {
	
	private Vector2d position;
	
	public GameObject() {
		position = new Vector2d(0, 0);
	}

	/**
	 * Get 2D position of the object.
	 * 
	 * @return Vector2d
	 */
	public Vector2d getPosition() {
		return (Vector2d)position.clone();
	}
	
	/**
	 * Set position.
	 * 
	 * @param position Vector2d
	 */
	public void setPosition(Vector2d position) {
		this.position = position;
	}
	
	/**
	 * Set position.
	 * 
	 * @param x new position coordinate
	 * @param y new position coordinate
	 */
	public void setPosition(double x, double y) {
		this.position.set(x, y);
	}
	
	/**
	 * Returns x position.
	 * 
	 * @return x
	 */
	public double getX() {
		return position.x;
	}
	
	/**
	 * Returns y position.
	 * 	
	 * @return y
	 */
	public double getY() {
		return position.y;
	}
	
	public String toString() {
		return "GameObject";
	}
}
