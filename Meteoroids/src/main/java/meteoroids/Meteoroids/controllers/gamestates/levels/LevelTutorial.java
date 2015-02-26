package meteoroids.Meteoroids.controllers.gamestates.levels;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gamestates.GameStateController;
import meteoroids.Meteoroids.controllers.gamestates.GameStatePlay;


public class LevelTutorial extends Level {

    public LevelTutorial(GameStateController controller, GameStatePlay play) {
        super(controller, play);
        this.nextLevelCountDown = 5000;
        play.fixedScreen(false);
    }

    @Override
    protected void initTexts() {
        addLevelText("LEVEL 1", "Lost asteroids");
        addLevelInfoText("destroy the lost asteroid!");
        addHelpText("Use ARROWS to control your ship");
        addHelpText("Press SPACE to shoot");
    }

    @Override
    protected void initPlanets() {
        this.planets = null;
    }

    @Override
    protected void initAsteroids() {
        float x = Game.WIDTH/2-1000.0f;
        float y = Game.HEIGHT/2;
        objectController.createAsteroid(x, y, 1500.f, 30.0f);
    }

    @Override
    protected boolean checkLevelFinished(float deltaTime) {
        if(objectController.numberOfAsteroidsAlive() <= 0) {
            if(!levelFinished) {
                levelDone();
            }
            return true;
        }        
        return false;
    }

    @Override
    protected void checkGameOver() {
        // Can't fail the tutorial level
    }


}
