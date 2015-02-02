package meteoroids.Meteoroids.controllers.gameobjects;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector2f;

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
        for(int i = 0; i < asteroids.length; i++) {
            float x = 800.0f * (float)Math.random();
            float y = 600.0f * (float)Math.random();
            while(x > 200.0f && x < 600.0f)
                x = 800.0f * (float)Math.random();
            while(y > 200.0f && y < 400.0f)
                y = 600.0f * (float)Math.random();
                        
            asteroids[i] = new Asteroid(x, y, mass, size);
            asteroids[i].addForce(((float)Math.random() - 0.5f) * 0.05f,
                    ((float)Math.random() - 0.5f) * 0.05f);
        }
        return asteroids;
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
