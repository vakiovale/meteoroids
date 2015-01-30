package meteoroids.Meteoroids.controllers.gameobjects;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector2f;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.gameobjects.DUGameObject;
import meteoroids.Meteoroids.gameobjects.Drawable;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.Updateable;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.gameobjects.physicsobjects.GravityObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.BasicGun;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.BasicProjectile;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.PlasmaProjectile;
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

    private FiringController firingController;

    private List<PhysicsObject> physicsObjects;
    private List<GravityObject> gravityObjects;
    private List<Drawable> drawableObjects;
    private List<Updateable> updateableObjects;

    public GameObjectController() {
        idCounter = 0;
        killed = new ArrayList<>();
        physicsObjects = new ArrayList<>();
        drawableObjects = new ArrayList<>();
        updateableObjects = new ArrayList<>();
        gravityObjects = new ArrayList<>();
        firingController = new FiringController(this);
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
        Ship ship = new Ship(100.0f, 300.0f, 100.0f);

        addGameObject(ship);
        
        BasicGun gun = new BasicGun();
        bindProjectile(gun, ProjectileType.BASIC_PROJECTILE);

        ship.bindWeapon(gun);

        return ship;
    }

    /**
     * Bind projectile to the ship's main weapon.
     * 
     * @param ship
     * @param projectileType
     */
    public void bindProjectile(Ship ship, ProjectileType projectileType) {
        Weapon weapon = ship.getWeapon();
        if(weapon != null) {
            bindProjectile(weapon, projectileType);
        }
    }

    /**
     * Bind projectile to the specific weapon.
     * 
     * @param weapon
     * @param projectileType
     */
    public void bindProjectile(Weapon weapon, ProjectileType projectileType) {
        Projectile projectile = null;
        switch (projectileType) {
        case BASIC_PROJECTILE:
            projectile = new BasicProjectile(0.0f, 0.0f);
            break;
        case PLASMA_PROJECTILE:
            projectile = new PlasmaProjectile(0.0f, 0.0f);
            break;
        default:
            break;
        }
        weapon.bindProjectile(projectile);
    }

    /**
     * Creates Asteroids.
     * 
     * @return array of Asteroids
     */
    public Asteroid[] getAsteroids(int numberOfAsteroids, float mass, float size) {
        Asteroid[] asteroids = new Asteroid[numberOfAsteroids];
        for(int i = 0; i < asteroids.length; i++) {
            float x = 800.0f * (float)Math.random();
            float y = 600.0f * (float)Math.random();
            while(x > 200.0f && x < 600.0f)
                x = 800.0f * (float)Math.random();
            while(y > 200.0f && y < 400.0f)
                y = 600.0f * (float)Math.random();
                        
            asteroids[i] = new Asteroid(x, y, mass, size);
            addGameObject(asteroids[i]);
            asteroids[i].addForce(((float)Math.random() - 0.5f) * 0.05f,
                    ((float)Math.random() - 0.5f) * 0.05f);
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
        for(Updateable u : updateableObjects) {
            u.update(deltaTime);
        }
        for(GameObject object : killed) {
            killGameObject(object);
            System.out.println("kill " + object);
        }
        firingController.update(deltaTime);
    }

    /**
     * Kills game object and removes from the game.
     * 
     * @param object to be removed
     */
    public void killGameObject(GameObject object) {
        if(object == null) return;
        removeFromList(physicsObjects, object);
        removeFromList(gravityObjects, object);
        removeFromList(drawableObjects, object);
        removeFromList(updateableObjects, object);
        if(object instanceof Asteroid) {
            Asteroid a = (Asteroid)object;
            destroyAsteroid(a);
        }
    }

    /**
     * Destroys an asteroid and creates two smaller one from it.
     * 
     * @param a Asteroid to be destroyed
     */
    private void destroyAsteroid(Asteroid a) {
        float size = a.getRadius()/2;
        float mass = a.getMass()/2;
        Vector2f position = a.getPosition();
        Vector2f velocity = a.getVelocity();
        
        if(size >= 3.0f) {
            Asteroid asteroidA = new Asteroid(position.x-size, position.y-size, mass, size);
            Asteroid asteroidB = new Asteroid(position.x+size, position.y+size, mass, size);
            
            asteroidA.setVelocity(velocity);
            asteroidA.addForce(((float)Math.random() - 0.5f) * 1.5f,
                    ((float)Math.random() - 0.5f) * 1.5f);

            asteroidB.setVelocity(velocity);
            asteroidB.addForce(((float)Math.random() - 0.5f) * 1.5f,
                    ((float)Math.random() - 0.5f) * 1.5f);
            
            addGameObject(asteroidA);
            addGameObject(asteroidB);
        }
    }

    /**
     * Adds DUGameObject to the game. Works for PhysicsObject also.
     * 
     * @param object
     */
    public void addGameObject(DUGameObject object) {
        if(object == null) return;
        if(object instanceof PhysicsObject) {
            PhysicsObject pobject = (PhysicsObject)object;
            physicsObjects.add(pobject);
        }
        drawableObjects.add(object);
        updateableObjects.add(object);
        object.setID(++idCounter);        
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

    /**
     * Change to the next weapon from specific ship.
     * 
     * @param ship
     */
    public void changeWeapon(Ship ship) {
        ProjectileType[] types = ProjectileType.values();
        int projectileNumber = ship.getWeapon().getProjectile().getType()
                .ordinal();
        projectileNumber++;
        if(projectileNumber >= types.length)
            projectileNumber = 0;
        bindProjectile(ship, types[projectileNumber]);
    }

}
