package meteoroids.Meteoroids.controllers.gamestates;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.utilities.PointsController;
import meteoroids.Meteoroids.controllers.utilities.TextHandler;
import meteoroids.Meteoroids.utilities.Text;
import meteoroids.Meteoroids.utilities.highscores.HighScores;
import meteoroids.Meteoroids.utilities.highscores.Score;

public class GameStateGotHighScore extends GameStateMachine {

    private GameObjectController objectController;
    private HighScores scores;
    private final String HIGHSCORES_PATH = "highscores.dat";
    public static StringBuilder name;
    private TextHandler textHandler;
    
    public GameStateGotHighScore(GameStateController controller, GameObjectController objectController) {
        super(controller);
        this.objectController = objectController;
        this.gameState = GameState.GOT_HIGH_SCORE;
        name = new StringBuilder();
        textHandler = new TextHandler();
        scores = new HighScores(HIGHSCORES_PATH);
    }

    @Override
    public void update(float deltaTime) {
        textHandler.clear();
        //PollWriter.poll(name);
        textHandler.addText(new Text("NEW HIGH SCORE!", Game.WIDTH/2-220.0f, Game.HEIGHT/10));
        textHandler.addText(new Text("Enter name: ", Game.WIDTH/2-350.0f, Game.HEIGHT/2));
        textHandler.addText(new Text(name.toString(), Game.WIDTH/2, Game.HEIGHT/2));
        controller.getGraphicsController().draw(objectController.getDrawables());
        textHandler.draw();
    }

    public void save() {
        scores.saveScore(new Score(name.toString(), PointsController.getPoints(PointsController.mainPlayer)));
        controller.addGameState(new GameStateHighScores(controller, objectController, true));
    }
}
