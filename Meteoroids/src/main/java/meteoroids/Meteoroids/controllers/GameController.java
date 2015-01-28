package meteoroids.Meteoroids.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector2f;

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
    
	public GameController() {
		
		physicsObjects = new ArrayList<>();
		drawableObjects = new ArrayList<>();
		updateableObjects = new ArrayList<>();
		gravityObjects = new ArrayList<>();
		
		Asteroid[] asteroids = new Asteroid[10];
		Planet planet = new Planet(400.0f, 300.0f, 10.0f, 500000.0f);
		Ship ship = new Ship(300.0f, 250.0f, 100.0f);
		
		gravityObjects.add(planet);
		drawableObjects.add(planet);
		physicsObjects.add(planet);
		
		drawableObjects.add(ship);
		physicsObjects.add(ship);
		updateableObjects.add(ship);
		
		ship.addForce(0.0f, 0.02f);
		
		for(int i = 0; i < asteroids.length; i++) {
		    asteroids[i] = new Asteroid(500.0f, 400.0f, 100.0f, 4.0f);
		    physicsObjects.add(asteroids[i]);
		    drawableObjects.add(asteroids[i]);
		    updateableObjects.add(asteroids[i]);
		    
		    asteroids[i].addForce(((float)Math.random()-0.5f)*0.05f, 
		                          ((float)Math.random()-0.5f)*0.05f);
		}
		
	}

	@Override
	public void update(float deltaTime) {
		
	    deltaTime = deltaTime * Game.getTimeFactor();
		
	    for(Updateable u : updateableObjects) {
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
