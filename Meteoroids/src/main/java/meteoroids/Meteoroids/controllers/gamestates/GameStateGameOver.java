package meteoroids.Meteoroids.controllers.gamestates;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.utilities.TextHandler;
import meteoroids.Meteoroids.utilities.Text;

/**
 * Game Over state
 * 
 * @author vpyyhtia
 *
 */
public class GameStateGameOver extends GameStateMachine {

    private int gameOverCounter;
    private GameObjectController gameObjectController;
    private TextHandler textHandler;
    private Text textGameOver;
    private Text askToContinueText;
    private Text textCounter;
        
    public GameStateGameOver(GameStateController controller, GameObjectController gameObjectController) {
        super(controller);
        
        this.gameObjectController = gameObjectController;        
        this.textHandler = new TextHandler();
        
        gameState = GameState.GAME_OVER;
        gameOverCounter = 5000;
        
        Text textGameOver = new Text("Game Over!", Game.WIDTH/2-(Game.WIDTH/14), Game.HEIGHT/2-(Game.HEIGHT/10));
        Text askToContinueText = new Text("New game? (Press N)", Game.WIDTH/2-(Game.WIDTH/8), Game.HEIGHT/2);
        
        textHandler.addText(textGameOver);
        textHandler.addText(askToContinueText);
        
    }

    @Override
    public void update(float deltaTime) {
        controller.getGraphicsController().update(deltaTime);

        gameOverCounter -= deltaTime;
        
        textCounter = new Text(Integer.toString(gameOverCounter/1000) + "...", Game.WIDTH/2, Game.HEIGHT/2+(Game.HEIGHT/10));
        textHandler.addText(textCounter);
        
        if(gameOverCounter <= 0) {
            exit();
            textHandler.removeText(textGameOver);
            textHandler.removeText(askToContinueText);
        }
                
        controller.getGraphicsController().draw(gameObjectController.getDrawables());
        textHandler.draw();
        textHandler.removeText(textCounter);
    }

}
