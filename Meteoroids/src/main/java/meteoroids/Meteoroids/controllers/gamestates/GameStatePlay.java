package meteoroids.Meteoroids.controllers.gamestates;

import java.util.List;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.graphics.GraphicsController;
import meteoroids.Meteoroids.controllers.physics.PhysicsController;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.StarField;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Ship;

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
    private Planet planet;
    private StarField starField;
            
    public GameStatePlay(GameStateController controller) {
        super(controller);
        gameState = GameState.PLAY;
        printHelp();
        
        // Initialize controllers
        objectController = new GameObjectController();
        physicsController = new PhysicsController();
        graphicsController = this.controller.getGraphicsController();
        
        // Initialize game objects
        this.starField = objectController.getStarField(Game.WIDTH, Game.HEIGHT);
        this.ship = objectController.getShip();
        this.asteroids = objectController.getAsteroids(30, 1000.0f, 30.0f);
        this.planet = initPlanet();
        
    }
    
    private Planet initPlanet() {
        float x;
        float y;
        do {
            x = (float)Math.random()*Game.WIDTH;
            y = (float)Math.random()*Game.HEIGHT;
        } while(spawningFails(x, y));
        
        float radius = (float)Math.random()*100.0f + 10.0f;
        float mass = (float)(Math.random()*500000.0f+25000.0f)*(radius/10.0f);
        
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
        System.out.println("DOWN:\t\tReverse");
        System.out.println("LEFT:\t\tSteer left");
        System.out.println("RIGHT:\t\tSteer right");
        System.out.println("SPACE:\t\tFire");
        System.out.println("LEFT CTRL\tChange weapon");
        System.out.println("ESC:\t\tExit game");
        System.out.println("******************************");
    }
    
    /**
     * Check if game is over.
     * 
     * @return true if game is over
     */
    public void checkGameOver() {
        if(planet != null && planet.isDead()) {               
            exit();
            objectController.killGameObject(planet);
            GameStateGameOver gameOverGameState = new GameStateGameOver(controller, objectController);
            controller.addGameState(gameOverGameState);                    
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
        if(x > Game.WIDTH*0.15f && x < Game.WIDTH*0.85f) {
            if(y > Game.HEIGHT*0.15f && y < Game.HEIGHT*0.85f) {
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
