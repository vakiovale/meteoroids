package meteoroids.Meteoroids.controllers;

/**
 * Interface for controllers
 * 
 * @author vpyyhtia
 *
 */
public interface Controller {

	/**
	 * Update controller with deltaTime
	 * 
	 * @param deltaTime time since last frame
	 */
	public void update(float deltaTime);
	
}
