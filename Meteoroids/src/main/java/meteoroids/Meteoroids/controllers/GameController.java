package meteoroids.Meteoroids.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector2f;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.gameobjects.Drawable;
import meteoroids.Meteoroids.gameobjects.Updateable;
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

    List<PhysicsObject> physicsObjects;
    List<Drawable> drawableObjects;
    List<Updateable> updateableObjects;
    
	public GameController() {
		
		physicsObjects = new ArrayList<>();
		drawableObjects = new ArrayList<>();
		updateableObjects = new ArrayList<>();
		
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
	
}
