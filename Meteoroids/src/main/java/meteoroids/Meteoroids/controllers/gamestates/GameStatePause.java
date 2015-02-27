package meteoroids.Meteoroids.controllers.gamestates;

import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.utilities.TextHandler;

/**
 * Game state pause. Pause screen.
 * 
 * @author vpyyhtia
 *
 */
public class GameStatePause extends GameStateMachine {

    private GameObjectController gameObjectController;
    private TextHandler textHandler;
        
    public GameStatePause(GameStateController controller, GameObjectController gameObjectController) {
        super(controller);
        this.gameState = GameState.PAUSE;
        this.gameObjectController = gameObjectController;
        this.textHandler = new TextHandler();
        textHandler.addText("PAUSE");
    }

    @Override
    public void update(float deltaTime) {
        controller.getGraphicsController().draw(gameObjectController.getDrawables());
        textHandler.draw();
    }

}
