package meteoroids.Meteoroids.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector2f;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Ship;

/**
 * Handles all the controllers in the game
 * 
 * @author vpyyhtia
 *
 */
public class GameController implements Controller {

    Ship ship;
    Asteroid asteroid1;
    Asteroid asteroid2;
    Asteroid asteroid3;
    List<PhysicsObject> physicsObjects;
    
	public GameController() {
		ship = new Ship();
		asteroid1 = new Asteroid();
		asteroid2 = new Asteroid();
		asteroid3 = new Asteroid();
		
		physicsObjects = new ArrayList<>();
		physicsObjects.add(ship);
		physicsObjects.add(asteroid1);
		physicsObjects.add(asteroid2);
		physicsObjects.add(asteroid3);
	}

	@Override
	public void update(float deltaTime) {
		
	    /* TODO:
	     * 
	     * !!! All this is just testing for now !!!
	     * 
	     */
	    
	    deltaTime = deltaTime * Game.getTimeFactor();
	    
	    // Add forces
	    for(PhysicsObject p : physicsObjects) {
		    p.addForce(new Vector2f((float)Math.random()*0.1f-0.5f*0.1f, 
		                            (float)Math.random()*0.1f-0.5f*0.1f));
		}
		
	    // Update objects and clear forces and draw object
	    for(PhysicsObject p : physicsObjects) {
	        p.update(deltaTime);
	        p.clearForces();
	        p.draw();
	    }
	}
	
}
