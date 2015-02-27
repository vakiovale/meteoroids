package meteoroids.Meteoroids.controllers.gamestates;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.controllers.graphics.TextureController;

/**
 * Abstract class for different game states. GameStateMachines are used for
 * different states in the game. Main menu, different levels, high scores screen etc.
 * all have their own state.
 * 
 * @author vpyyhtia
 *
 */
public abstract class GameStateMachine implements Controller {

    protected boolean exitState;
    protected GameStateController controller;
    protected GameState gameState;
    protected TextureController textureController;
    
    /**
     * Constructor for GameStateMachine.
     * 
     * @param controller the main GameStateController
     */
    public GameStateMachine(GameStateController controller) {
        this.controller = controller;
        this.textureController = Game.textureController;
        exitState = false;
    }
    
    /**
     * Exit from current state.
     * 
     */
    public void exit() {
        exitState = true;
    }
    
    /**
     * Check if GameStateMachine has come to its end.
     * 
     * @return true if GameStateMachine has exited
     */
    public boolean isFinished() {
        return exitState;
    }
    
    public GameState getGameState() {
        return gameState;
    }
    
}
