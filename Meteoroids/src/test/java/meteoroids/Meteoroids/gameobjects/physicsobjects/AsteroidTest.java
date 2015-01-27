package meteoroids.Meteoroids.gameobjects.physicsobjects;

import static org.junit.Assert.*;

import javax.vecmath.Vector2f;

import org.junit.Before;
import org.junit.Test;

public class AsteroidTest {

    Asteroid asteroid;
    
    @Before
    public void setUp() {
        asteroid = new Asteroid();
    }

    @Test
    public void testAsteroidMass() {
        float mass = asteroid.getMass();
        assertEquals("Asteroid mass should be 500.0, but it was " + mass + ".", 500.0, mass, 0.01);
    }

}
