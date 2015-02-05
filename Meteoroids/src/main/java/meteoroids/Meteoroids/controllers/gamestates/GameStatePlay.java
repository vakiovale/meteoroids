package meteoroids.Meteoroids.controllers.gamestates;

import java.util.List;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.graphics.GraphicsController;
import meteoroids.Meteoroids.controllers.input.InputController;
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
    private InputController inputController;
    
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
        this.planet = objectController.getPlanet();
        
        // Initialize input controller
        this.inputController = new InputController(objectController, ship);
    }

    @Override
    public void update(float deltaTime) {
        // Refresh screen
        graphicsController.update(deltaTime);

        // Calculate gravity fields
        physicsController.update(objectController.getGravityObjects(),
                    objectController.getPhysicsObjects(), deltaTime);

        // Kill dead objects
        killGameObjects(physicsController.getKilled());

        // Update game world
        objectController.update(deltaTime);
            
        // Get input
        inputController.update(deltaTime);

        // Draw
        graphicsController.draw(objectController.getDrawables());
        graphicsController.draw(objectController.getHUDController());

        // Check if game is over
        gameOver();    
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
    public void gameOver() {
        if(getNumberOfAsteroidsAlive() <= 0 || (planet != null && planet.isDead())) {               
            objectController.killGameObject(planet);
            GameStateGameOver gameOverGameState = new GameStateGameOver(controller, objectController);
            controller.addGameState(gameOverGameState);
            planet = objectController.getPlanet((float)Math.random()*Game.WIDTH, (float)Math.random()*Game.HEIGHT);
        }
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
