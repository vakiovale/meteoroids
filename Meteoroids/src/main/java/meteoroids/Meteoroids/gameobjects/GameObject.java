package meteoroids.Meteoroids.gameobjects;

import javax.vecmath.Vector2d;

/**
 * Basic GameObject
 * 
 * @author vpyyhtia
 *
 */
public class GameObject implements IPosition {
	
	protected Vector2d position;
	
	public GameObject() {
		position = new Vector2d(0, 0);
	}

	@Override
	public Vector2d getPosition() {
		return (Vector2d)position.clone();
	}
	
    @Override
	public void setPosition(Vector2d position) {
		this.position = position;
	}
	
    @Override
	public void setPosition(double x, double y) {
		this.position.set(x, y);
	}
	
    @Override
	public double getX() {
		return position.x;
	}
	
    @Override
	public double getY() {
		return position.y;
	}
	
	public String toString() {
		return "GameObject";
	}
}
