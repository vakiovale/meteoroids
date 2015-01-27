package meteoroids.Meteoroids.gameobjects;

/**
 * Updateable game object.
 * 
 * @author vpyyhtia
 *
 */
public class UpdateableGameObject extends GameObject implements Updateable {

	@Override
	public void update(float deltaTime) {
		System.out.println(this + " update"); 
	}

}
