package meteoroids.Meteoroids.gameobjects;

/**
 * Interface for objects that are updated
 * 
 * @author vpyyhtia
 *
 */
public interface Updateable {

	/**
	 * Update object with delta time.
	 * 
	 * @param deltaTime time since last frame
	 */
	public void update(float deltaTime);
}
