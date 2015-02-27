package meteoroids.Meteoroids.controllers;

import meteoroids.Meteoroids.controllers.gamestates.GameStateController;
import meteoroids.Meteoroids.controllers.gamestates.GameStateMachine;
import meteoroids.Meteoroids.controllers.gamestates.GameStateMainMenu;
import meteoroids.Meteoroids.controllers.graphics.GraphicsController;

/**
 * Handles the GameStateController and updates it with deltaTime
 * 
 * @author vpyyhtia
 *
 */
public class GameController implements Controller {

    private static float timeFactor = 1.0f;
    
    private GameStateController stateController;
    
    /**
     * Constructor for GameController
     * 
     * @param graphics GraphicsController which will be binded to the GameStateController
     */
    public GameController(GraphicsController graphics) {
        this.stateController = new GameStateController(graphics);
        GameStateMachine startingGameState = new GameStateMainMenu(stateController);
        this.stateController.addGameState(startingGameState);
    }

    @Override
    public void update(float deltaTime) {
        deltaTime = deltaTime * timeFactor;
        stateController.update(deltaTime);
    }
    
    /**
     * Time factor tells how fast game should be run. It's possible to speed up
     * / slow down the game by multiplying delta time with time factor.
     * 
     * @return time factor
     */
    public static float getTimeFactor() {
        return timeFactor;
    }
    
}
