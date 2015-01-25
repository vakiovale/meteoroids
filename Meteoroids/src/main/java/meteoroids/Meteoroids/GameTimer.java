package meteoroids.Meteoroids;

import org.lwjgl.Sys;

/**
 * Handles calculating delta time and current time
 * 
 * @author vpyyhtia
 *
 */
class GameTimer {

	private long lastTime;
	
	GameTimer() {
		lastTime = Sys.getTime();
	}
	
	/**
	 * Calculates milliseconds from last frame.
	 * 
	 * @return delta time (milliseconds from last frame)
	 */
	int getDeltaTime() {
		long currentTime = Sys.getTime();
		int delta = (int)(currentTime - lastTime);
		lastTime = currentTime;
		return delta;
	}
	
	/**
	 * Current time
	 * 
	 * @return time in milliseconds
	 */
	long getTime() {
		return (Sys.getTime()*1000) / Sys.getTimerResolution();
	}
	
}
