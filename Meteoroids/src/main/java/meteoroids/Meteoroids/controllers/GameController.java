package meteoroids.Meteoroids.controllers;

import java.util.List;

import org.lwjgl.input.Keyboard;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.graphics.HUDController;
import meteoroids.Meteoroids.gameobjects.Drawable;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.StarField;
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
    private StarField starField;

    public GameController() {

        objectController = new GameObjectController();

        starField = objectController.getStarField(Game.WIDTH, Game.HEIGHT);
        ship = objectController.getShip();
        asteroids = objectController.getAsteroids(2, 1000.0f, 30.0f);
        planet = objectController.getPlanet();
        
        printHelp();

    }
    
    /**
     * Check if game is over.
     * 
     * @return true if game is over
     */
    public boolean gameOver() {
        return getNumberOfAsteroidsAlive() <= 0 || planet.isDead();
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

    @Override
    public void update(float deltaTime) {

        deltaTime = deltaTime * Game.getTimeFactor();

        /*
         * TESTING INPUT !!
         * 
         * TODO: Add these to the InputController class
         */
        pollInputs(deltaTime);
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
    
    public HUDController getHUDController() {
        return objectController.getHUDController();
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
    
    /**
     * Number of asteroids alive in the game.
     * 
     * @return number of asteroids
     */
    public int getNumberOfAsteroidsAlive() {
        return objectController.numberOfAsteroidsAlive();
    }

    /**
     * TODO:
     * 
     * !! TESTING INPUTS !!
     * 
     * Implement InputController
     * 
     * @param deltaTime
     */
    private void pollInputs(float deltaTime) {
        while(Keyboard.next()) {
            if(Keyboard.getEventKey() == Keyboard.KEY_LCONTROL) {
                if(!Keyboard.getEventKeyState()) {
                    objectController.changeWeapon(ship);
                }
            }
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            ship.rotate(0.3f, deltaTime);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            ship.rotate(-0.3f, deltaTime);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            ship.accelerate(0.002f, deltaTime);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            ship.accelerate(-0.002f, deltaTime);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            objectController.fire(ship);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            Game.exit();
        }
    }

}
