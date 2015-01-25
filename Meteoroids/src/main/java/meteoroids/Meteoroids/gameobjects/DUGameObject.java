package meteoroids.Meteoroids.gameobjects;

/**
 * Game object that is drawn and updated. 
 * This is the most common GameObject to use in a game.
 * 
 * @author vpyyhtia
 *
 */
public class DUGameObject extends GameObject implements Drawable, Updateable {

	@Override
	public void update(double deltaTime) {
		System.out.println(this + " update"); 
	}

	@Override
	public void draw() {
		System.out.println(this + " draw");	
	}

}
