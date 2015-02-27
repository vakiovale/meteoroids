package meteoroids.Meteoroids.controllers.gamestates.levels;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gamestates.GameStateController;
import meteoroids.Meteoroids.controllers.gamestates.GameStatePlay;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PlanetType;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Ship;

/**
 * Level - Home Sweet Home
 * 
 * @author vpyyhtia
 *
 */
public class LevelHomeSweetHome extends Level {

    public LevelHomeSweetHome(GameStateController controller, GameStatePlay play) {
        super(controller, play);
        this.nextLevelCountDown = 5000;
        this.levelFinishedTimer = 300000;
        initTimer();
        play.fixedScreen(false);
        Ship ship = play.getShip();
        ship.setPosition(Game.WIDTH/2, Game.HEIGHT/2-1800);
    }
    
    @Override
    public void update(float deltaTime) {
        objectController.spawnAsteroids(deltaTime);
        super.update(deltaTime);
    }

    @Override
    protected void initTexts() {
        addLevelText("LEVEL 8", "Home Sweet Home");
    }

    @Override
    protected void initPlanets() {
        this.planets = new Planet[1];
        planets[0] = objectController.getPlanet(Game.WIDTH/2, Game.HEIGHT/2, 125.0f, 4000000, 50000, PlanetType.EARTH);
    }

    @Override
    protected void initAsteroids() {
        objectController.createAsteroid(Game.WIDTH/2, 3000.0f, 40000.f, 300.0f);
        objectController.createAsteroid(Game.WIDTH/2-800, 3500.0f, 40000.f, 300.0f);
        objectController.createAsteroid(Game.WIDTH/2+800, 3700.0f, 40000.f, 300.0f);
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
