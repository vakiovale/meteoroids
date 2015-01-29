package meteoroids.Meteoroids.controllers;

import java.util.ArrayList;
import java.util.List;

import meteoroids.Meteoroids.gameobjects.Drawable;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.Updateable;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.gameobjects.physicsobjects.GravityObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Ship;

/**
 * Controller for game objects.
 * 
 * @author vpyyhtia
 *
 */
public class GameObjectController implements Controller {

    private int idCounter;
    
    private List<PhysicsObject> physicsObjects;
    private List<GravityObject> gravityObjects;
    private List<Drawable> drawableObjects;
    private List<Updateable> updateableObjects;
    
    public GameObjectController() {
        idCounter = 0;
        physicsObjects = new ArrayList<>();
        drawableObjects = new ArrayList<>();
        updateableObjects = new ArrayList<>();
        gravityObjects = new ArrayList<>();
    }
    
    /**
     * Creates new ship.
     * 
     * @return Ship
     */
    public Ship getShip() {
        Ship ship = new Ship(100.0f, 300.0f, 100.0f);
        drawableObjects.add(ship);
        physicsObjects.add(ship);
        updateableObjects.add(ship);
        ship.setID(++idCounter);
        return ship;
    }
    
    /**
     * Creates Asteroids.
     * 
     * @return array of Asteroids
     */
    public Asteroid[] getAsteroids() {
        Asteroid[] asteroids = new Asteroid[25];
        for (int i = 0; i < asteroids.length; i++) {
            asteroids[i] = new Asteroid(750.0f*(float)Math.random(), 550.0f*(float)Math.random(), 100.0f, 8.0f);
            physicsObjects.add(asteroids[i]);
            drawableObjects.add(asteroids[i]);
            updateableObjects.add(asteroids[i]);
            asteroids[i].addForce(((float) Math.random() - 0.5f) * 0.05f,
                    ((float) Math.random() - 0.5f) * 0.05f);
            asteroids[i].setID(++idCounter);
        }
        return asteroids;
    }
    
    /**
     * Create a Planet.
     * 
     * @return Planet
     */
    public Planet getPlanet() {
        Planet planet = new Planet(400.0f, 300.0f, 20.0f, 500000.0f);
        gravityObjects.add(planet);
        drawableObjects.add(planet);
        physicsObjects.add(planet);
        planet.setID(++idCounter);
        return planet;
    }
    
    @Override
    public void update(float deltaTime) {
        for (Updateable u : updateableObjects) {
            u.update(deltaTime);
        }
    }
    
    /**
     * Kills game object and removes from the game.
     * 
     * @param object to be removed
     */
    public void killGameObject(GameObject object) {
        removeFromList(physicsObjects, object);
        removeFromList(gravityObjects, object);
        removeFromList(drawableObjects, object);
        removeFromList(updateableObjects, object);
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
    
    public void clearIdCounter() {
        idCounter = 0;
    }
    
    private void removeFromList(List<?> list, GameObject object) {
        int id = object.getID();
        for(int i = list.size()-1; i >= 0; i--) {
            if(!(list.get(i) instanceof GameObject)) break;
            if(((GameObject)list.get(i)).getID()==id) {
                list.remove(i);
                break;
            }
        }
    }

}
