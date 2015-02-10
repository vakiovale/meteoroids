package meteoroids.Meteoroids.controllers.gamestates;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.controllers.graphics.TextureController;

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
    protected TextureController textureController;
    
    public GameStateMachine(GameStateController controller) {
        this.controller = controller;
        this.textureController = Game.textureController;
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
