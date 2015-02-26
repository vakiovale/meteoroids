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
    private GameStatePlay gameStatePlay;
    private GameObjectController gameObjectController;
    private TextHandler textHandler;
    private Text textGameOver;
    private Text askToContinueText;
    private Text textCounter;
    private final long POINTS;
    private final String HIGHSCORES_PATH = "highscores.dat";
    private HighScores highScores;
        
    public GameStateGameOver(GameStateController controller, GameObjectController gameObjectController, GameStatePlay gameStatePlay) {
        super(controller);
        
        this.gameStatePlay = gameStatePlay;
        this.gameObjectController = gameObjectController;        
        this.textHandler = new TextHandler();
        
        gameState = GameState.GAME_OVER;
        gameOverCounter = 10000;
        
        Text textGameOver = new Text("Game Over!", Game.WIDTH/2-(Game.WIDTH/14), Game.HEIGHT/2-(Game.HEIGHT/10));
        Text askToContinueText = new Text("Try again? (Press Y)", Game.WIDTH/2-(Game.WIDTH/8), Game.HEIGHT/2);
        Text newHighScore = new Text("NEW HIGHSCORE: " + PointsController.getPoints(PointsController.mainPlayer), Game.WIDTH/2-(Game.WIDTH/8), Game.HEIGHT/2-(Game.HEIGHT/4));
        newHighScore.setSize(1);
        
        textHandler.addText(textGameOver);
        textHandler.addText(askToContinueText);  
        
        POINTS = PointsController.getPoints(PointsController.mainPlayer);
        highScores = new HighScores(HIGHSCORES_PATH);
        
        if(highScores.topTenCheck(POINTS)) {
            textHandler.addText(newHighScore);
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
            this.gameStatePlay.exit();
            textHandler.removeText(textGameOver);
            textHandler.removeText(askToContinueText);
            
            if(highScores.topTenCheck(POINTS)) {
                controller.addGameState(new GameStateGotHighScore(controller, gameObjectController));
            }
        }
                
        controller.getGraphicsController().draw(gameObjectController.getDrawables());
        gameObjectController.getHUDController().draw();
        textHandler.draw();
        textHandler.removeText(textCounter);
    }

    /**
     * Retry level.
     * 
     */
    public void retry() {
        gameStatePlay.retry();
    }
}
