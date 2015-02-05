package meteoroids.Meteoroids.controllers.gamestates;

import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.resources.Text;
import meteoroids.Meteoroids.controllers.resources.TextHandler;

/**
 * Game Over state
 * 
 * @author vpyyhtia
 *
 */
public class GameStateGameOver extends GameStateMachine {

    private int gameOverCounter;
    private GameObjectController gameObjectController;
    private Text text;
        
    public GameStateGameOver(GameStateController controller, GameObjectController gameObjectController) {
        super(controller);
        
        this.gameObjectController = gameObjectController;        
        
        gameState = GameState.GAME_OVER;
        gameOverCounter = 5000;
        Text text = new Text("Game Over!");
        controller.getTextHandler().addText(text);
        
    }

    @Override
    public void update(float deltaTime) {
        controller.getGraphicsController().update(deltaTime);

        gameOverCounter -= deltaTime;
        if(gameOverCounter <= 0) {
            controller.removeGameState();
            controller.getTextHandler().removeText(text);
        }
        
        controller.getGraphicsController().draw(gameObjectController.getDrawables());
        controller.getTextHandler().draw();
    }

}
