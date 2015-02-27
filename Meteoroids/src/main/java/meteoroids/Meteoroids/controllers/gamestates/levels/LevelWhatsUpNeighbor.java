package meteoroids.Meteoroids.controllers.gamestates.levels;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gamestates.GameStateController;
import meteoroids.Meteoroids.controllers.gamestates.GameStatePlay;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PlanetType;

public class LevelWhatsUpNeighbor extends Level {

    public LevelWhatsUpNeighbor(GameStateController controller,
            GameStatePlay play) {
        super(controller, play);
        this.nextLevelCountDown = 5000;
        this.levelFinishedTimer = 120000;
        initTimer();
        play.fixedScreen(true);
    }
        
    @Override
    public void update(float deltaTime) {
        objectController.spawnAsteroids(deltaTime);
        super.update(deltaTime);
    }

    @Override
    protected void initTexts() {
        addLevelText("LEVEL 6", "What's up neighbor?");
        addLevelInfoText("Venus is feeling weak! Protect her at all costs!");
        addLevelInfoText("Try to destroy as many asteroids as possible");
    }

    @Override
    protected void initPlanets() {
        this.planets = new Planet[1];
        planets[0] = objectController.getPlanet(Game.WIDTH/2, Game.HEIGHT/2, 50.0f, 2000000, 4500, PlanetType.VENUS);
    }

    @Override
    protected void initAsteroids() {
        objectController.createAsteroid(Game.WIDTH/2-600*2, Game.HEIGHT/2+600*2, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2-800*2, Game.HEIGHT/2-300*2, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2-300*2.5f, Game.HEIGHT/2+200*2.5f, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2-200*2, Game.HEIGHT/2-800*2, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2+100*2.5f, Game.HEIGHT/2+500*2.5f, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2+200*2, Game.HEIGHT/2-600*2, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2+400*2, Game.HEIGHT/2+200*2, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2+600*4, Game.HEIGHT/2-600*2, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2-600*3, Game.HEIGHT/2+600*3, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2-800*3, Game.HEIGHT/2-300*3, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2-300*3, Game.HEIGHT/2+200*3, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2-200*3, Game.HEIGHT/2-800*3, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2+100*3, Game.HEIGHT/2+500*3, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2+200*3, Game.HEIGHT/2-600*3, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2+400*3, Game.HEIGHT/2+200*3, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2+600*3, Game.HEIGHT/2-600*3, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2-600*4, Game.HEIGHT/2+600*4, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2-800*4, Game.HEIGHT/2-300*4, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2-300*4, Game.HEIGHT/2+200*4, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2-200*4, Game.HEIGHT/2-800*4, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2+100*4, Game.HEIGHT/2+500*4, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2+200*4, Game.HEIGHT/2-600*4, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2+400*4, Game.HEIGHT/2+200*4, 2000.f, 60.0f);
        objectController.createAsteroid(Game.WIDTH/2+600*4, Game.HEIGHT/2-600*4, 2000.f, 60.0f);
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

