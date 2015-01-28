package meteoroids.Meteoroids.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector2f;

import org.lwjgl.input.Keyboard;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.gameobjects.Drawable;
import meteoroids.Meteoroids.gameobjects.Updateable;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.gameobjects.physicsobjects.GravityObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Ship;

/**
 * Handles all the controllers in the game
 * 
 * @author vpyyhtia
 *
 */
public class GameController implements Controller {

    List<PhysicsObject> physicsObjects;
    List<GravityObject> gravityObjects;
    List<Drawable> drawableObjects;
    List<Updateable> updateableObjects;
    Ship ship;

    public GameController() {

        physicsObjects = new ArrayList<>();
        drawableObjects = new ArrayList<>();
        updateableObjects = new ArrayList<>();
        gravityObjects = new ArrayList<>();
        
        ship = new Ship(100.0f, 300.0f, 100.0f);
        drawableObjects.add(ship);
        physicsObjects.add(ship);
        updateableObjects.add(ship);

        Asteroid[] asteroids = new Asteroid[100];
        for (int i = 0; i < asteroids.length; i++) {
            asteroids[i] = new Asteroid(500.0f, 400.0f, 100.0f, 2.0f);
            physicsObjects.add(asteroids[i]);
            drawableObjects.add(asteroids[i]);
            updateableObjects.add(asteroids[i]);

            asteroids[i].addForce(((float) Math.random() - 0.5f) * 0.05f,
                    ((float) Math.random() - 0.5f) * 0.05f);
        }
        
        Planet planet = new Planet(400.0f, 300.0f, 50.0f, 500000.0f);
        gravityObjects.add(planet);
        drawableObjects.add(planet);
        physicsObjects.add(planet);
        
        Planet planet2 = new Planet(200.0f, 100.0f, 50.0f, 500000.0f);
        //gravityObjects.add(planet2);
        //drawableObjects.add(planet2);
        //physicsObjects.add(planet2);
        
        Planet planet3 = new Planet(700.0f, 500.0f, 50.0f, 500000.0f);
        //gravityObjects.add(planet3);
        //drawableObjects.add(planet3);
        //physicsObjects.add(planet3);

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
        /* TESTING ENDS */

        for (Updateable u : updateableObjects) {
            u.update(deltaTime);
        }

    }

    public List<Updateable> getUpdateables() {
        return updateableObjects;
    }

    public List<Drawable> getDrawables() {
        return drawableObjects;
    }

    public List<PhysicsObject> getPhysicsObjects() {
        return physicsObjects;
    }

    public List<GravityObject> getGravityObjects() {
        return gravityObjects;
    }

}
