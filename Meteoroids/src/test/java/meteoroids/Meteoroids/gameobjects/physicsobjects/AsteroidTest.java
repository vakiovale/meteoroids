package meteoroids.Meteoroids.gameobjects.physicsobjects;

import static org.junit.Assert.*;

import javax.vecmath.Vector2d;

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
        double mass = asteroid.getMass();
        assertEquals("Asteroid mass should be 50.0, but it was " + mass + ".", 50.0, mass, 0.01);
    }

}
