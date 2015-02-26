package meteoroids.Meteoroids.controllers.gameobjects;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector2f;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.utilities.RandomGenerator;

/**
 * Asteroid controller for managing asteroids.
 * 
 * @author vpyyhtia
 *
 */
public class AsteroidController {
    
    private final int ASTEROIDS_BIRTH_TIME = 15000;
    private final float BIG_ASTEROID_RADIUS_START = 30.0f;
    private final float BIG_ASTEROID_MASS_START = 1000.0f;
    private float bigAsteroidRadius;
    private float bigAsteroidMass;
    private int newAsteroidsCounter;
    
    public AsteroidController() {
        newAsteroidsCounter = ASTEROIDS_BIRTH_TIME;
        bigAsteroidRadius = BIG_ASTEROID_RADIUS_START;
        bigAsteroidMass = BIG_ASTEROID_MASS_START;
    }
    
    /**
     * Destroys an asteroid and creates two smaller one from it.
     * 
     * @param a Asteroid to be destroyed
     * @return list of asteroids
     */
    public List<Asteroid> destroyAsteroid(Asteroid asteroid) {
        float size = asteroid.getRadius()/2;
        float mass = asteroid.getMass()/2;
        Vector2f position = asteroid.getPosition();
        Vector2f velocity = asteroid.getVelocity();
        List<Asteroid> newAsteroids = new ArrayList<>();        
        if(size >= 3.0f) {
            Asteroid asteroidA = new Asteroid(position.x, position.y, mass, size);
            Asteroid asteroidB = new Asteroid(position.x, position.y, mass, size);
            
            asteroidA.addForce(velocity);
            asteroidA.addForce(RandomGenerator.randomPlusMinus() * 1.5f,
                    RandomGenerator.randomPlusMinus() * 1.5f);

            asteroidB.addForce(velocity);
            asteroidB.addForce(RandomGenerator.randomPlusMinus() * 1.5f,
                    RandomGenerator.randomPlusMinus() * 1.5f);
            
            newAsteroids.add(asteroidA);
            newAsteroids.add(asteroidB);
        }
        return newAsteroids;
    }
    
    /**
     * Creates Asteroids.
     * 
     * @return array of Asteroids
     */
    public Asteroid[] getAsteroids(int numberOfAsteroids, float mass, float size) {
        Asteroid[] asteroids = new Asteroid[numberOfAsteroids];
        float x;
        float y;
        for(int i = 0; i < asteroids.length; i++) {
            do {
                x = Game.WIDTH*3 * RandomGenerator.randomPlusMinus();
                y = Game.HEIGHT*3 * RandomGenerator.randomPlusMinus();
            } while(spawningFails(x, y));                       
            asteroids[i] = new Asteroid(x, y, mass, size);
            asteroids[i].addForce(RandomGenerator.randomPlusMinus() * 0.05f,
                    RandomGenerator.randomPlusMinus() * 0.05f);
        }
        return asteroids;
    }
    
    /**
     * Check if position (x, y) is allowed for spawning. This returns false
     * if position is near the borders of the window.
     * 
     * @param x
     * @param y
     * @return true if spawning fails
     */
    private boolean spawningFails(float x, float y) {
        if(x < -Game.WIDTH || x > Game.WIDTH*2) {
            return false;
        }
        else if(y < -Game.HEIGHT || y > Game.HEIGHT*2) {
            return false;
        }
        return true;
    }
    
    /**
     * Generates new asteroids.
     * 
     * @param deltaTime
     * @return array of asteroids
     */
    public Asteroid[] generateNewAsteroids(float deltaTime) {
        newAsteroidsCounter -= deltaTime;
        if(newAsteroidsCounter <= 0) {
            newAsteroidsCounter = ASTEROIDS_BIRTH_TIME;
            Asteroid[] asteroids = getAsteroids(2, bigAsteroidMass, bigAsteroidRadius);

            // Next spawn will be even bigger asteroids!
            bigAsteroidRadius = bigAsteroidRadius + bigAsteroidRadius/3;
            bigAsteroidMass = bigAsteroidMass + bigAsteroidMass/3;
            
            return asteroids;
        }
        return null;
    }

    /**
     * Creates new asteroid.
     * 
     * @param posX
     * @param posY
     * @param mass
     * @param size
     * @return Asteroid
     */
    public Asteroid createAsteroid(float posX, float posY, float mass,
            float size) {
        return new Asteroid(posX, posY, mass, size);
    }
}
