package meteoroids.Meteoroids.controllers.gamestates;

import java.util.List;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.graphics.GraphicsController;
import meteoroids.Meteoroids.controllers.physics.PhysicsController;
import meteoroids.Meteoroids.controllers.utilities.PointsController;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.StarField;
import meteoroids.Meteoroids.gameobjects.hud.EnergyBar;
import meteoroids.Meteoroids.gameobjects.hud.PointsBox;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PlanetType;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Ship;
import meteoroids.Meteoroids.utilities.RandomGenerator;

/**
 * Playing game state.
 * 
 * @author vpyyhtia
 *
 */
public class GameStatePlay extends GameStateMachine {

    private GameObjectController objectController;

    private GraphicsController graphicsController;
    private PhysicsController physicsController;
    private Ship ship;
    private Asteroid[] asteroids;
    private Planet[] planets;
    private StarField starField;
            
    public GameStatePlay(GameStateController controller) {
        super(controller);
        gameState = GameState.PLAY;
        printHelp();
        
        // Initialize controllers
        objectController = new GameObjectController();
        physicsController = new PhysicsController();
        graphicsController = this.controller.getGraphicsController();
        objectController.bindTextureController(textureController);
                
        // Initialize game objects
        this.starField = objectController.getStarField(Game.WIDTH, Game.HEIGHT);
        this.ship = objectController.getShip();
        
        this.asteroids = objectController.getAsteroids(4, 1000.0f, 30.0f);
        //initAsteroids();
        
        this.planets = new Planet[1];
        //this.planets[0] = objectController.getPlanet(200.0f, Game.HEIGHT/3, 20.0f, 10000.0f, PlanetType.MARS);
        //this.planets[1] = objectController.getPlanet(Game.WIDTH-200.0f, Game.HEIGHT-Game.HEIGHT/3, 100.0f, 10000.0f, PlanetType.JUPITER);
        for(int i = 0; i < planets.length; i++) {
            this.planets[i] = initPlanet();
        }
        
        // Add player's ship to the PointsController
        PointsController.addPlayer(ship);
        PointsController.bindMainPlayer(ship);
        objectController.getHUDController().addHUDElement(
                new PointsBox(PointsController.getPointsObject(ship)));
    }
    
    private void initAsteroids() {
        float x = Game.WIDTH/2-200.0f;
        float y = 100.0f;
        for(int i = 0; i < 5; i++) {
            objectController.createAsteroid(x, y, 1000.0f, 30.0f);
            for(int j = 0; j < 5; j++) {
                objectController.createAsteroid(RandomGenerator.randomPlusMinus()*200.0f+x, RandomGenerator.randomPlusMinus()*200.0f+y, 500.0f, 15.0f);
                for(int k = 0; k < 5; k++) {
                    objectController.createAsteroid(RandomGenerator.randomPlusMinus()*200.0f+x, RandomGenerator.randomPlusMinus()*200.0f+y, 200.0f, 6.0f);
                }
            }
            x += 50.0f;
            y += 220.0f;
        }
    }

    private Planet initPlanet() {
        float x;
        float y;
        do {
            x = RandomGenerator.random()*Game.WIDTH;
            y = RandomGenerator.random()*Game.HEIGHT;
        } while(spawningFails(x, y));
        
        float radius = RandomGenerator.random()*100.0f + 10.0f;
        float mass = (RandomGenerator.random()*500000.0f+25000.0f)*(radius/10.0f);
        
        return objectController.getPlanet(x, y, radius, mass);
    }

    /**
     * Player's current ship
     * 
     * @return ship
     */
    public Ship getShip() {
        return ship;
    }
    
    /**
     * Current GameObjectController
     * 
     * @return objectController
     */
    public GameObjectController getObjectController() {
        return objectController;
    }

    @Override
    public void update(float deltaTime) {
        // Calculate gravity fields
        physicsController.update(objectController.getGravityObjects(),
                    objectController.getPhysicsObjects(), deltaTime);

        // Kill dead objects
        killGameObjects(physicsController.getKilled());

        // Update game world
        objectController.update(deltaTime);
            
        // Draw
        graphicsController.draw(objectController.getDrawables());
        graphicsController.draw(objectController.getHUDController());

        // Check if game is over
        checkGameOver();    
    }
    
    /**
     * Kills GameObject and removes it from the game.
     * 
     * @param object(float)
     */
    public void killGameObjects(List<GameObject> objects) {
        for(GameObject object : objects)
            objectController.killGameObject(object);
    }
    
    private void printHelp() {
        System.out.println("******************************");
        System.out.println("    H O W   T O   P L A Y");
        System.out.println("******************************");
        System.out.println("UP:\t\tAccelerate");
        System.out.println("DOWN:\t\tSlow down");
        System.out.println("LEFT:\t\tSteer left");
        System.out.println("RIGHT:\t\tSteer right");
        System.out.println("SPACE:\t\tFire");
        System.out.println("LEFT CTRL\tChange weapon");
        System.out.println("P\t\tPause game");
        System.out.println("ESC:\t\tExit game");
        System.out.println("******************************");
    }
    
    /**
     * Check if game is over.
     * 
     * @return true if game is over
     */
    public void checkGameOver() {
        for(int i = 0; i < planets.length; i++) {
            if(planets[i] != null && planets[i].isDead()) {               
                exit();
                objectController.killGameObject(planets[i]);
                GameStateGameOver gameOverGameState = new GameStateGameOver(controller, objectController);
                controller.addGameState(gameOverGameState);
                break;
            }
        }
    }
    
    /**
     * Check if position (x, y) is allowed for spawning. This returns false
     * if position is not near the borders of the window.
     * 
     * @param x
     * @param y
     * @return true if spawning fails
     */
    private boolean spawningFails(float x, float y) {
        if(x > Game.WIDTH*0.3f && x < Game.WIDTH*0.7f) {
            if(y > Game.HEIGHT*0.3f && y < Game.HEIGHT*0.7f) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Number of asteroids alive in the game.
     * 
     * @return number of asteroids
     */
    public int getNumberOfAsteroidsAlive() {
        return objectController.numberOfAsteroidsAlive();
    }

}
