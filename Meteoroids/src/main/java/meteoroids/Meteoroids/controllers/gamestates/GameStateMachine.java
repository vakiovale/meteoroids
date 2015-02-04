package meteoroids.Meteoroids.controllers.gamestates;

import meteoroids.Meteoroids.controllers.Controller;

/**
 * Abstract class for different game states.
 * 
 * @author vpyyhtia
 *
 */
public abstract class GameStateMachine implements Controller {

    protected boolean exitState;
    protected GameStateController controller;
    protected GameState gameState;
    
    public GameStateMachine(GameStateController controller) {
        this.controller = controller;
        exitState = false;
    }
    
    public void exit() {
        exitState = true;
    }
    
    public boolean isFinished() {
        return exitState;
    }
    
    public GameState getGameState() {
        return gameState;
    }
    
}
