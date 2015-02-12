package meteoroids.Meteoroids.controllers.gamestates;

import java.util.ArrayDeque;
import java.util.List;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.gamestates.levels.Level;
import meteoroids.Meteoroids.controllers.gamestates.levels.LevelAsteroidField;
import meteoroids.Meteoroids.controllers.gamestates.levels.LevelType;
import meteoroids.Meteoroids.controllers.graphics.GraphicsController;
import meteoroids.Meteoroids.controllers.physics.PhysicsController;
import meteoroids.Meteoroids.controllers.utilities.PointsController;
import meteoroids.Meteoroids.controllers.utilities.TextHandler;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.StarField;
import meteoroids.Meteoroids.gameobjects.hud.EnergyBar;
import meteoroids.Meteoroids.gameobjects.hud.PointsBox;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PlanetType;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Ship;
import meteoroids.Meteoroids.utilities.RandomGenerator;
import meteoroids.Meteoroids.utilities.Text;

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

    private ArrayDeque<LevelType> levels;
    private Level level;
                
    public GameStatePlay(GameStateController controller) {
        super(controller);
        gameState = GameState.PLAY;
        printHelp();
        initLevels();
        init(levels.poll());        
    }
    
    private void initLevels() {
        levels = new ArrayDeque<>();
        levels.add(LevelType.ASTEROID_FIELD);
    }

    /**
     * Initialize GameStatePlay
     * @param asteroidField 
     * 
     */
    public void init(LevelType levelType) {
        // Initialize controllers
        objectController = new GameObjectController();
        physicsController = new PhysicsController();
        graphicsController = this.controller.getGraphicsController();
        objectController.bindTextureController(textureController);
        
        // Initialize game objects
        objectController.getStarField(Game.WIDTH*3, Game.HEIGHT*3, 1000);
        this.ship = objectController.getShip();
        
        // Initialize level
        initLevel(levelType);
        
        // Initialize radar
        objectController.addRadar(ship);
        
        // Add player's ship to the PointsController
        PointsController.addPlayer(ship);
        PointsController.bindMainPlayer(ship);
        objectController.getHUDController().addHUDElement(
                new PointsBox(PointsController.getPointsObject(ship)));
        
        // Initialize follow up camera
        graphicsController.setFollowPlayerCamera(true, ship);
    }
    
    private void initLevel(LevelType levelType) {
        switch (levelType) {
            case ASTEROID_FIELD:
                this.level = new LevelAsteroidField(controller, this);
                break;
            default:
                this.level = new LevelAsteroidField(controller, this);
                break;        
        }
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
        
        // Level update
        level.update(deltaTime);
        
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
     * Number of asteroids alive in the game.
     * 
     * @return number of asteroids
     */
    public int getNumberOfAsteroidsAlive() {
        return objectController.numberOfAsteroidsAlive();
    }

    /**
     * Go to the next level!
     * 
     */
    public void nextLevel() {
        LevelType nextLevel = levels.poll();
        if(nextLevel != null) {
            init(nextLevel);
        }
        else {
            exit();
        }
    }

}
