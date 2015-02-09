package meteoroids.Meteoroids.utilities;

import org.lwjgl.Sys;

/**
 * Handles calculating delta time and current time
 * 
 * @author vpyyhtia
 *
 */
public class GameTimer {

    private long lastTime;

    public GameTimer() {
        lastTime = Sys.getTime();
    }

    /**
     * Calculates milliseconds from last frame.
     * 
     * @return delta time (milliseconds from last frame)
     */
    public int getDeltaTime() {
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
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

}
