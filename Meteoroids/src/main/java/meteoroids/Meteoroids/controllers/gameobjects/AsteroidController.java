package meteoroids.Meteoroids.controllers.gameobjects;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector2f;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;

/**
 * Asteroid controller for managing asteroids.
 * 
 * @author vpyyhtia
 *
 */
public class AsteroidController {
    
    private final int ASTEROIDS_BIRTH_TIME = 20000;
    private int newAsteroidsCounter;
    
    public AsteroidController() {
        newAsteroidsCounter = ASTEROIDS_BIRTH_TIME;
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
            Asteroid asteroidA = new Asteroid(position.x-size, position.y-size, mass, size);
            Asteroid asteroidB = new Asteroid(position.x+size, position.y+size, mass, size);
            
            asteroidA.setVelocity(velocity);
            asteroidA.addForce(((float)Math.random() - 0.5f) * 1.5f,
                    ((float)Math.random() - 0.5f) * 1.5f);

            asteroidB.setVelocity(velocity);
            asteroidB.addForce(((float)Math.random() - 0.5f) * 1.5f,
                    ((float)Math.random() - 0.5f) * 1.5f);
            
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
                x = Game.WIDTH * (float)Math.random();
                y = Game.HEIGHT * (float)Math.random();
            } while(spawningFails(x, y));                       
            asteroids[i] = new Asteroid(x, y, mass, size);
            asteroids[i].addForce(((float)Math.random() - 0.5f) * 0.05f,
                    ((float)Math.random() - 0.5f) * 0.05f);
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
        if(x < Game.WIDTH*0.1f || x > Game.WIDTH*0.9f) {
            return false;
        }
        else if(y < Game.HEIGHT*0.1f || y > Game.HEIGHT*0.9f) {
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
            return getAsteroids(2, 1000.0f, 30.0f);
        }
        return null;
    }
}
