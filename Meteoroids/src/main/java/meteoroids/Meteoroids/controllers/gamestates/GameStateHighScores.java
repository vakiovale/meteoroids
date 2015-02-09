package meteoroids.Meteoroids.controllers.gamestates;

import java.util.ArrayList;
import java.util.List;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.utilities.TextHandler;
import meteoroids.Meteoroids.utilities.Text;
import meteoroids.Meteoroids.utilities.highscores.HighScores;
import meteoroids.Meteoroids.utilities.highscores.Score;

/**
 * Game state for High scores screen
 * 
 * @author vpyyhtia
 *
 */
public class GameStateHighScores extends GameStateMachine {

    private final String HIGHSCORES_PATH = "highscores.dat"; 
    private HighScores highScores;
    private GameObjectController objectController;
    private TextHandler textHandler;
    private final int MAX_SCORES = 10; 
    private boolean staticBackground;
    
    public GameStateHighScores(GameStateController controller, GameObjectController objectController, boolean isStaticBackground) {
        super(controller);
        staticBackground = isStaticBackground;
        
        this.objectController = objectController;
        this.gameState = GameState.HIGH_SCORES;
        
        textHandler = new TextHandler();        
        initHighScores();
    }
    
    public GameStateHighScores(GameStateController controller) {
        this(controller, new GameObjectController(), false);
        objectController.getStarField(Game.WIDTH, Game.HEIGHT);
    }

    private void initHighScores() {
        highScores = new HighScores(HIGHSCORES_PATH);
        textHandler.addText(new Text("High Scores", Game.WIDTH/2-220.0f, Game.HEIGHT/10));
        
        List<Score> scores = highScores.getScores();
        float height = Game.HEIGHT/3;
        for(int i = 0; i < MAX_SCORES && i < scores.size(); i++) {
            Score score = scores.get(i);
            textHandler.addText(new Text(score.getName(), 
                            Game.WIDTH/2-(2*Game.WIDTH/10), 
                            height));
            textHandler.addText(new Text(Long.toString(score.getPoints()), 
                            Game.WIDTH/2+Game.WIDTH/10, 
                            height));
            height += 50.0f;
        }
    }

    @Override
    public void update(float deltaTime) {
        if(!staticBackground) {
            objectController.update(deltaTime);
        }
        controller.getGraphicsController().draw(objectController.getDrawables());
        textHandler.draw();
    }

}
