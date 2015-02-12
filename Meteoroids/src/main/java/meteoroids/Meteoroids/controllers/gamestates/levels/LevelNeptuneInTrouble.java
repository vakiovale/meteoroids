package meteoroids.Meteoroids.controllers.gamestates.levels;

import meteoroids.Meteoroids.controllers.gamestates.GameStateController;
import meteoroids.Meteoroids.controllers.gamestates.GameStatePlay;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PlanetType;

public class LevelNeptuneInTrouble extends Level {

    public LevelNeptuneInTrouble(GameStateController controller,
            GameStatePlay play) {
        super(controller, play);
        this.nextLevelCountDown = 5000;
        this.levelFinishedTimer = 120000;
    }
        
    @Override
    public void update(float deltaTime) {
        objectController.spawnAsteroids(deltaTime);
        super.update(deltaTime);
    }

    @Override
    protected void initTexts() {
        addLevelText("LEVEL 3", "Neptune in trouble!");
        addLevelInfoText("Oh no, asteroids are closing in!");
        addLevelInfoText("Try to destroy as many asteroids as possible");
    }

    @Override
    protected void initPlanets() {
        this.planets = new Planet[1];
        planets[0] = objectController.getPlanet(500.0f, 700.0f, 200.0f, 5000000, PlanetType.NEPTUNE);
    }

    @Override
    protected void initAsteroids() {
        objectController.createAsteroid(500.0f*4, 500.0f, 2000.f, 60.0f);
        objectController.createAsteroid(500.0f*-4, 500.0f, 2000.f, 60.0f);        
        objectController.createAsteroid(500.0f, 500.0f*4, 2000.f, 60.0f);        
        objectController.createAsteroid(500.0f, 500.0f*-4, 2000.f, 60.0f);
    }

    @Override
    protected boolean checkLevelFinished(float deltaTime) {
        if(objectController.numberOfAsteroidsAlive() <= 0 || levelFinishedTimer <= 0) {
            if(!levelFinished) {
                levelDone();
            }
            killAsteroids(deltaTime);
            return true;
        }        
        return false;
    }

}
