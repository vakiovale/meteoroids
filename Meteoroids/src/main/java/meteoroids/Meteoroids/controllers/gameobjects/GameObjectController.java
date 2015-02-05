package meteoroids.Meteoroids.controllers.gameobjects;

import java.util.ArrayList;
import java.util.List;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.controllers.graphics.HUDController;
import meteoroids.Meteoroids.gameobjects.DUGameObject;
import meteoroids.Meteoroids.gameobjects.Drawable;
import meteoroids.Meteoroids.gameobjects.DrawableGameObject;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.StarField;
import meteoroids.Meteoroids.gameobjects.Updateable;
import meteoroids.Meteoroids.gameobjects.hud.EnergyBar;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.gameobjects.physicsobjects.GravityObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Projectile;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Projectile.ProjectileType;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Ship;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.ShootingShip;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Weapon;

/**
 * Controller for game objects.
 * 
 * @author vpyyhtia
 *
 */
public class GameObjectController implements Controller {

    private List<GameObject> killed;

    private int idCounter;
    private int asteroidCounter;

    private FiringController firingController;
    private ShipController shipController;
    private AsteroidController asteroidController;
    private PlanetController planetController;
    private StarController starController;
    private HUDController hudController;
    
    private List<PhysicsObject> physicsObjects;
    private List<GravityObject> gravityObjects;
    private List<Drawable> drawableObjects;
    private List<Updateable> updateableObjects; 

    public GameObjectController() {
        idCounter = 0;
        asteroidCounter = 0;
        
        killed = new ArrayList<>();
        physicsObjects = new ArrayList<>();
        drawableObjects = new ArrayList<>();
        updateableObjects = new ArrayList<>();
        gravityObjects = new ArrayList<>();
        
        firingController = new FiringController(this);
        shipController = new ShipController();
        asteroidController = new AsteroidController();
        planetController = new PlanetController();
        starController = new StarController();
        hudController = new HUDController();
    }

    /**
     * Shoot with ship.
     * 
     * @param ship
     */
    public void fire(ShootingShip ship) {
        Projectile projectile = firingController.fire(ship);
        if(projectile != null) {
            addGameObject(projectile);
        }
    }

    /**
     * Creates new ship.
     * 
     * @return Ship
     */
    public Ship getShip() {
        Ship ship = shipController.getShip();
        addGameObject(ship);
        return ship;
    }

    /**
     * Bind projectile to the ship's main weapon.
     * 
     * @param ship
     * @param projectileType
     */
    public void bindProjectile(Ship ship, ProjectileType projectileType) {
        shipController.bindProjectile(ship, projectileType);
    }

    /**
     * Bind projectile to the specific weapon.
     * 
     * @param weapon
     * @param projectileType
     */
    public void bindProjectile(Weapon weapon, ProjectileType projectileType) {
        shipController.bindProjectile(weapon, projectileType);
    }
    
    /**
     * Change to the next weapon from specific ship.
     * 
     * @param ship
     */
    public void changeWeapon(Ship ship) {
        shipController.changeWeapon(ship);
    }

    /**
     * Creates Asteroids.
     * 
     * @return array of Asteroids
     */
    public Asteroid[] getAsteroids(int numberOfAsteroids, float mass, float size) {
        Asteroid[] asteroids = asteroidController.getAsteroids(numberOfAsteroids, mass, size);
        addGameObject(asteroids);
        return asteroids;
    }

    /**
     * Create a Planet.
     * 
     * @return Planet
     */
    public Planet getPlanet() {
        return getPlanet(Game.WIDTH/2, Game.HEIGHT/2);
    }
    
    /**
     * Create a Planet at certain position.
     * 
     * @return Planet
     */
    public Planet getPlanet(float x, float y) {
        return getPlanet(x, y, 20.0f, 500000.0f);
    }
    
    /**
     * Create a Planet at certain position with certain radius and mass.
     * 
     * @return Planet
     */
    public Planet getPlanet(float x, float y, float radius, float mass) {
        Planet planet = new Planet(x, y, radius, mass);
        addGameObject(planet);
        hudController.addHUDElement(new EnergyBar(planet.getEnergy(), x-(radius*1.5f), y+(radius+10), radius*3));
        return planet;
    }
        
    /**
     * Get HUDController.
     * 
     * @return hudController
     */
    public HUDController getHUDController() {
        return hudController;
    }

    @Override
    public void update(float deltaTime) {
        for(Updateable u : updateableObjects) {
            u.update(deltaTime);
        }
        for(GameObject object : killed) {
            killGameObject(object);
            System.out.println("kill " + object);
        }
        firingController.update(deltaTime);
        generateNewAsteroids(deltaTime);
    }
    
    /**
     * Generates a star field to the game.
     *  
     * @param width of the star field
     * @param height of the star field
     * @return
     */
    public StarField getStarField(float width, float height) {
        StarField starField = starController.generateStarField(width, height);
        addGameObject(starField);
        return starField;
    }

    /**
     * Generates new asteroids to the game.
     * 
     * @param deltaTime
     */
    private void generateNewAsteroids(float deltaTime) {
        addGameObject(asteroidController.generateNewAsteroids(deltaTime));      
    }

    /**
     * Destroys an asteroid and creates two smaller one from it.
     * 
     * @param a Asteroid to be destroyed
     */
    private void destroyAsteroid(Asteroid asteroid) {
        for(Asteroid a : asteroidController.destroyAsteroid(asteroid)) {
            addGameObject(a);
        }
    }

    /**
     * Adds DUGameObject to the game. Works for PhysicsObject also.
     * 
     * @param object
     */
    public void addGameObject(DUGameObject object) {
        if(object == null) {
            System.out.println("adding object was null");
            return;
        }
        if(object instanceof PhysicsObject) {
            PhysicsObject pobject = (PhysicsObject)object;
            physicsObjects.add(pobject);
            if(object instanceof Asteroid) {
                asteroidCounter++;
            }
        }
        if(object instanceof GravityObject) {
            GravityObject gobject = (GravityObject)object;
            gravityObjects.add(gobject);
        }
        drawableObjects.add(object);
        updateableObjects.add(object);
        object.setID(++idCounter);        
    }
    
    /**
     * Kills game object and removes from the game.
     * 
     * @param object to be removed
     */
    public void killGameObject(GameObject object) {
        if(object == null) {
            System.out.println("object was null");
            return;
        }
        removeFromList(physicsObjects, object);
        removeFromList(gravityObjects, object);
        removeFromList(drawableObjects, object);
        removeFromList(updateableObjects, object);
        if(object instanceof Asteroid) {
            Asteroid a = (Asteroid)object;
            destroyAsteroid(a);
            asteroidCounter--;
        }
    }
    
    /**
     * Add a DrawableGameObject to the game.
     * 
     * @param object
     */
    public void addGameObject(DrawableGameObject object) {
        drawableObjects.add(object);
        object.setID(++idCounter);
    }
    
    /**
     * Adds DUGameObjects to the game. Works for PhysicsObject also.
     * 
     * @param objects array of DUGameObjects
     */
    public void addGameObject(DUGameObject[] objects) {
        if(objects == null) return;
        for(DUGameObject o : objects) {
            addGameObject(o);
        }
    }
    
    /**
     * Number of asteroids alive in the game.
     * 
     * @return number of asteroids
     */
    public int numberOfAsteroidsAlive() {
        return asteroidCounter;
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

    public List<GameObject> getKilled() {
        return killed;
    }
    
    private void removeFromList(List<?> list, GameObject object) {
        int id = object.getID();
        for(int i = list.size() - 1; i >= 0; i--) {
            if(!(list.get(i) instanceof GameObject))
                break;
            if(((GameObject)list.get(i)).getID() == id) {
                list.remove(i);
                break;
            }
        }
    }
}
