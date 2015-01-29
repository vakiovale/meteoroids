package meteoroids.Meteoroids.controllers;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.gameobjects.Drawable;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.Updateable;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.gameobjects.physicsobjects.GravityObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Ship;

/**
 * Handles all the controllers in the game
 * 
 * @author vpyyhtia
 *
 */
public class GameController implements Controller {
   
    private GameObjectController objectController;
    
    private Ship ship;
    private Asteroid[] asteroids;
    private Planet planet;

    public GameController() {
       
        objectController = new GameObjectController();
        
        ship = objectController.getShip();
        asteroids = objectController.getAsteroids();
        planet = objectController.getPlanet();
        
        printHelp();
        
    }

    private void printHelp() {
         System.out.println("***********************");
         System.out.println(" H O W   T O   P L A Y");
         System.out.println("***********************");
         System.out.println("UP:\tAccelerate");
         System.out.println("DOWN:\tReverse");
         System.out.println("LEFT:\tSteer left");
         System.out.println("RIGHT:\tSteer right");
         System.out.println("SPACE:\tFire");
         System.out.println("ESC:\tExit game");
         System.out.println("***********************");
    }

    @Override
    public void update(float deltaTime) {

        deltaTime = deltaTime * Game.getTimeFactor();

        /*
         * TESTING INPUT !!
         * 
         * TODO: Add these to the InputController class
         */
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            ship.rotate(0.3f, deltaTime);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            ship.rotate(-0.3f, deltaTime);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            ship.accelerate(0.002f, deltaTime);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            ship.accelerate(-0.002f, deltaTime);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            objectController.fire(ship);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            Game.exit();
        }
        /* TESTING ENDS */

        objectController.update(deltaTime);
    }

    public List<Updateable> getUpdateables() {
        return objectController.getUpdateables();
    }

    public List<Drawable> getDrawables() {
        return objectController.getDrawables();
    }

    public List<PhysicsObject> getPhysicsObjects() {
        return objectController.getPhysicsObjects();
    }

    public List<GravityObject> getGravityObjects() {
        return objectController.getGravityObjects();
    }
    
    /**
     * Kills GameObject and removes it from the game.
     * 
     * @param object
     */
    public void killGameObjects(List<GameObject> objects) {
        for(GameObject object : objects) 
            objectController.killGameObject(object);
    }

}
