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
		
		Asteroid[] asteroids = new Asteroid[10000];
		Planet planet = new Planet(400.0f, 300.0f, 20.0f, 5000.0f);
		gravityObjects.add(planet);
		drawableObjects.add(planet);
		
		for(int i = 0; i < asteroids.length; i++) {
		    asteroids[i] = new Asteroid(500.0f, 400.0f);
		    physicsObjects.add(asteroids[i]);
		    drawableObjects.add(asteroids[i]);
		    updateableObjects.add(asteroids[i]);
		    
		    asteroids[i].addForce(new Vector2f(((float)Math.random()-0.5f)*0.1f, 
		                                       ((float)Math.random()-0.5f)*0.1f));
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
