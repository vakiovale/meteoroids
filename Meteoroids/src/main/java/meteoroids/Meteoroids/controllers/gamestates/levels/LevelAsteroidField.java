package meteoroids.Meteoroids.controllers.gamestates.levels;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gamestates.GameStateController;
import meteoroids.Meteoroids.controllers.gamestates.GameStatePlay;
import meteoroids.Meteoroids.controllers.utilities.PointsController;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PlanetType;
import meteoroids.Meteoroids.utilities.RandomGenerator;

/**
 * Level - Asteroid field
 * 
 * @author vpyyhtia
 *
 */
public class LevelAsteroidField extends Level {
    
    private long targetPoints;
    
    public LevelAsteroidField(GameStateController controller, GameStatePlay play) {
        super(controller, play); 
        targetPoints = PointsController.getPoints(PointsController.mainPlayer) + 500;
        play.fixedScreen(false);
    }
    
    @Override
    protected void initTexts() {
        addLevelText("LEVEL 4", "Asteroid field");
        addLevelInfoText("protect Jupiter and Mars from nasty asteroids!");
        addLevelInfoText("get 500 points");
    }

    @Override
    protected void initPlanets() {
        this.planets = new Planet[2];
        this.planets[0] = objectController.getPlanet(Game.WIDTH*-0.4f, Game.HEIGHT/3, 200.0f, 1000000.0f, PlanetType.MARS);
        this.planets[1] = objectController.getPlanet(Game.WIDTH*2.2f, Game.HEIGHT-Game.HEIGHT/3, 600.0f, 25000000.0f, PlanetType.JUPITER);        
    }

    @Override
    protected void initAsteroids() {
        float x = Game.WIDTH/2-200.0f;
        float y = -Game.HEIGHT;
        for(int i = 0; i < 5; i++) {
            objectController.createAsteroid(x, y, 1000.0f, 30.0f);
            for(int j = 0; j < 5; j++) {
                objectController.createAsteroid(RandomGenerator.randomPlusMinus()*200.0f+x, RandomGenerator.randomPlusMinus()*1000.0f+y, 500.0f, 15.0f);
                for(int k = 0; k < 5; k++) {
                    objectController.createAsteroid(RandomGenerator.randomPlusMinus()*200.0f+x, RandomGenerator.randomPlusMinus()*1000.0f+y, 200.0f, 6.0f);
                }
            }
            x += 50.0f;
            y += 1000.0f;
        }
    }

    @Override
    protected boolean checkLevelFinished(float deltaTime) {
        if(PointsController.getPoints(PointsController.mainPlayer) >= targetPoints) {
            if(!levelFinished) {
                levelDone();
            }
            return true;
        }        
        return false;
    }

}
