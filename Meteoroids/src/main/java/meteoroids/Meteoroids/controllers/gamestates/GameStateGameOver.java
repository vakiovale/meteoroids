package meteoroids.Meteoroids.controllers.gamestates;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.utilities.PointsController;
import meteoroids.Meteoroids.controllers.utilities.TextHandler;
import meteoroids.Meteoroids.utilities.Text;
import meteoroids.Meteoroids.utilities.highscores.HighScores;

/**
 * Game Over state. Will be shown to the player after there will be game over.
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
        
    /**
     * Constructor for GameStateGameOver
     * 
     * @param controller game's main GameStateController
     * @param gameObjectController objectController for showing the object's behind the GameOver view
     * @param gameStatePlay current GameStatePlay
     */
    public GameStateGameOver(GameStateController controller, GameObjectController gameObjectController, GameStatePlay gameStatePlay) {
        this(controller, gameObjectController, gameStatePlay, false);
    }
    
    /**
     * Constructor for GameStateGameOver
     * 
     * @param controller game's main GameStateController
     * @param gameObjectController objectController for showing the object's behind the GameOver view
     * @param gameStatePlay current GameStatePlay
     * @param win true if GameOver came because the game was actually finished
     */
    public GameStateGameOver(GameStateController controller, GameObjectController gameObjectController, GameStatePlay gameStatePlay, boolean win) {
        super(controller);
        
        this.gameStatePlay = gameStatePlay;
        this.gameObjectController = gameObjectController;        
        this.textHandler = new TextHandler();
        
        gameState = GameState.GAME_OVER;
        gameOverCounter = 10000;
        
        Text textGameOver;
        if(!win) {
            textGameOver = new Text("Game Over!", Game.WIDTH/2-(Game.WIDTH/14), Game.HEIGHT/2-(Game.HEIGHT/10));
        }
        else {
            textGameOver = new Text("THE END! YOU WON!", Game.WIDTH/2-(Game.WIDTH/9), Game.HEIGHT/2-(Game.HEIGHT/10));
        }
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
            skip();
        }
                
        controller.getGraphicsController().draw(gameObjectController.getDrawables());
        gameObjectController.getHUDController().draw();
        textHandler.draw();
        textHandler.removeText(textCounter);
    }
    
    /**
     * Skip from Game Over state. Goes to the High scores if player has enough points.
     * Also exits from Game Over state.
     * 
     */
    public void skip() {
        exit();
        this.gameStatePlay.exit();
        textHandler.removeText(textGameOver);
        textHandler.removeText(askToContinueText);
        
        if(highScores.topTenCheck(POINTS)) {
            controller.addGameState(new GameStateGotHighScore(controller, gameObjectController));
        }
    }

    /**
     * Retry current level.
     * 
     */
    public void retry() {
        gameStatePlay.retry();
    }
}
