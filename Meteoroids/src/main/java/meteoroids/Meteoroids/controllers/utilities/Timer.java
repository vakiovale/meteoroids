package meteoroids.Meteoroids.controllers.utilities;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.gameobjects.Updateable;
import meteoroids.Meteoroids.utilities.Text;

/**
 * Draws timer to the screen.
 * 
 * @author vpyyhtia
 *
 */
public class Timer implements Updateable {

    private TextHandler textHandler;
    private Text textCounter;
    private int timer;
    
    public Timer(TextHandler textHandler, int timeInSeconds) {
        this.textHandler = textHandler;
        this.timer = timeInSeconds * 1000;
        textCounter = null;
        initTextCounter();
    }
    
    private void initTextCounter() {
        if(textCounter != null) {
            textHandler.removeText(textCounter);
        }    
        if(timer > 0) {
            this.textCounter = new Text("Time to survive: " + Float.toString((float)(timer/1000.0f)), Game.WIDTH/2, Game.HEIGHT/15);
            this.textCounter.setSize(0);
            textHandler.addText(textCounter);
        }
    }

    @Override
    public void update(float deltaTime) {
        timer -= deltaTime;
        initTextCounter();
    }

    @Override
    public int getID() {
        return -1;
    }

}
