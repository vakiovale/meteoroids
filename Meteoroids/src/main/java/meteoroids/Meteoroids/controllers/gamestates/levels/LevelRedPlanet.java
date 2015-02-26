package meteoroids.Meteoroids.controllers.gamestates.levels;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gamestates.GameStateController;
import meteoroids.Meteoroids.controllers.gamestates.GameStatePlay;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PlanetType;

public class LevelRedPlanet extends Level {
    
    public LevelRedPlanet(GameStateController controller, GameStatePlay play) {
        super(controller, play);
        this.nextLevelCountDown = 5000;
        this.levelFinishedTimer = 100000;
        initTimer();
        play.fixedScreen(true);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
    
    @Override
    protected void initTexts() {
        addLevelText("LEVEL 5", "The 'Dead' Red Planet");
        addLevelInfoText("You saved Mars, but... it's just too quiet... ");
    }

    @Override
    protected void initPlanets() {
        this.planets = new Planet[1];
        planets[0] = objectController.getPlanet(Game.WIDTH/2, Game.HEIGHT/2, 80.0f, 4000000, 40000, PlanetType.MARS);
    }

    @Override
    protected void initAsteroids() {
        for(int i = 0; i <= 360; i += 20) {
            for(int j = 1; j < 10; j++) {
                objectController.createAsteroid(500.0f+(float)Math.cos(Math.toRadians(i))*2200.0f*(1.0f+0.1f*j), 
                        500.0f+(float)Math.sin(Math.toRadians(i))*2200.0f*(1.0f+0.1f*j), 2000.0f, 30.0f);
            }
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
