package meteoroids.Meteoroids.controllers.gamestates;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;

/**
 * Game Over state
 * 
 * @author vpyyhtia
 *
 */
public class GameStateGameOver extends GameStateMachine {

    private int gameOverCounter;
    private GameObjectController gameObjectController;
    
    public GameStateGameOver(GameStateController controller, GameObjectController gameObjectController) {
        super(controller);
        
        this.gameObjectController = gameObjectController;        
        
        gameState = GameState.GAME_OVER;
        gameOverCounter = 5000;
    }

    @Override
    public void update(float deltaTime) {
        controller.getGraphicsController().update(deltaTime);

        gameOverCounter -= deltaTime;
        if(gameOverCounter <= 0) {
            controller.removeGameState();
        }
        
        controller.getGraphicsController().draw(gameObjectController.getDrawables());
    }

}
