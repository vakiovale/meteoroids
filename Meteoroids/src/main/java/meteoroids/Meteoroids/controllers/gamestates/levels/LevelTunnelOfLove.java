package meteoroids.Meteoroids.controllers.gamestates.levels;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gamestates.GameStateController;
import meteoroids.Meteoroids.controllers.gamestates.GameStatePlay;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PlanetType;
import meteoroids.Meteoroids.utilities.RandomGenerator;

public class LevelTunnelOfLove extends Level {

    public LevelTunnelOfLove(GameStateController controller, GameStatePlay play) {
        super(controller, play);
        this.nextLevelCountDown = 5000;
        this.levelFinishedTimer = 120000;
        initTimer();
        play.fixedScreen(false);
        play.getShip().setPosition(-5000f, Game.HEIGHT/2);
    }

    @Override
    protected void initTexts() {
        addLevelText("LEVEL 7", "Rush hour");
        addLevelInfoText("Protect Mercury before asteroids get there!");
    }

    @Override
    protected void initPlanets() {
        this.planets = new Planet[1];
        planets[0] = objectController.getPlanet(Game.WIDTH/2, Game.HEIGHT/2, 300.0f, 28000000, 10000, PlanetType.MERCURY);
    }

    @Override
    protected void initAsteroids() {
        for(int i = 0; i < 30; i++) {
            objectController.createAsteroid(-4000.0f-i*300.0f*RandomGenerator.random(), 
                    Game.HEIGHT/2-200.0f+80.0f*RandomGenerator.randomPlusMinus(), 
                    2000.f, 45.0f).addForce(8.0f, 0.0f);
            objectController.createAsteroid(-4000.0f-i*300.0f*RandomGenerator.random(), 
                    Game.HEIGHT/2+200.0f+80.0f*RandomGenerator.randomPlusMinus(), 
                    2000.f, 45.0f).addForce(8.0f, 0.0f);;
        }
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
