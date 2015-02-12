package meteoroids.Meteoroids.controllers.gamestates;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.utilities.PointsController;
import meteoroids.Meteoroids.controllers.utilities.TextHandler;
import meteoroids.Meteoroids.utilities.Text;
import meteoroids.Meteoroids.utilities.highscores.HighScores;

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
    private final long POINTS;
    private final String HIGHSCORES_PATH = "highscores.dat";
        
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
        
        POINTS = PointsController.getPoints(PointsController.mainPlayer);
        HighScores highScores = new HighScores(HIGHSCORES_PATH);
        if(highScores.topTenCheck(POINTS)) {
            this.exit();
            controller.addGameState(new GameStateGotHighScore(controller, gameObjectController));
        }
    }

    @Override
    public void update(float deltaTime) {
        controller.getGraphicsController().update(deltaTime);

        gameOverCounter -= deltaTime;
        
        gameObjectController.getHUDController().update(deltaTime);
        
        textCounter = new Text(Integer.toString(gameOverCounter/1000) + "...", Game.WIDTH/2, Game.HEIGHT/2+(Game.HEIGHT/10));
        textHandler.addText(textCounter);
        
        if(gameOverCounter <= 0) {
            exit();
            textHandler.removeText(textGameOver);
            textHandler.removeText(askToContinueText);
        }
                
        controller.getGraphicsController().draw(gameObjectController.getDrawables());
        gameObjectController.getHUDController().draw();
        textHandler.draw();
        textHandler.removeText(textCounter);
    }
}
