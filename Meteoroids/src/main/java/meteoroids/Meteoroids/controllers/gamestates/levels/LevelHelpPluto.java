package meteoroids.Meteoroids.controllers.gamestates.levels;

import meteoroids.Meteoroids.controllers.gamestates.GameStateController;
import meteoroids.Meteoroids.controllers.gamestates.GameStatePlay;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PlanetType;

public class LevelHelpPluto extends Level {

    public LevelHelpPluto(GameStateController controller, GameStatePlay play) {
        super(controller, play);
        this.nextLevelCountDown = 5000;
        this.levelFinishedTimer = 50000;
        initTimer();
    }

    @Override
    protected void initTexts() {
        addLevelText("LEVEL 2", "Help Pluto");
        addLevelInfoText("save Pluto from closing asteroids!");
        addLevelInfoText("Try to destroy as many asteroids as possible");
        addHelpText("Keep asteroids away from the planet");
        addHelpText("Don't shoot the Planet!");
    }

    @Override
    protected void initPlanets() {
        this.planets = new Planet[1];
        planets[0] = objectController.getPlanet(500.0f, 500.0f, 50.0f, 2500000, 3500, PlanetType.PLUTO);
    }

    @Override
    protected void initAsteroids() {
        objectController.createAsteroid(500.0f*3, 500.0f, 2000.f, 60.0f);
        objectController.createAsteroid(500.0f*-2, 500.0f, 2000.f, 60.0f);        
        objectController.createAsteroid(500.0f, 500.0f*3, 2000.f, 60.0f);        
        objectController.createAsteroid(500.0f, 500.0f*-2, 2000.f, 60.0f);
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
