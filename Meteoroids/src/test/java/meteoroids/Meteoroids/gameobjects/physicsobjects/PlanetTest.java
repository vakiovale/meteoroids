package meteoroids.Meteoroids.gameobjects.physicsobjects;

import static org.junit.Assert.*;

import javax.vecmath.Vector2f;

import org.junit.Before;
import org.junit.Test;

public class PlanetTest {

    private Planet planet;
    
    @Before
    public void setUp() {
        this.planet = new Planet();
    }
    
    @Test
    public void testUpdate() {
        float x = planet.getPosition().x;
        float y = planet.getPosition().y;
        planet.addForce(new Vector2f(100.0f, 90.02f));
        planet.update(100.0f);
        assertEquals("Planet should stay still.", "0.00.0", (x + "" + y));
    }
    
    @Test
    public void testRadius() {
        planet = new Planet(15.0f);
        assertEquals("Radius is wrong.", 15.0, planet.getRadius(), 0.01f);
    }
    
    @Test
    public void testGravityForce() {
        Planet testPlanet = new Planet(100.0f, 0.0f, 1.0f);
        planet.addGravityForce(testPlanet);
        Vector2f force = testPlanet.getForces();
        Vector2f gravityVector = planet.getPosition();
        gravityVector.sub(testPlanet.getPosition());
        float lengthSquared = gravityVector.lengthSquared();
        gravityVector.normalize();
        gravityVector.scale(0.000001f * ((planet.mass * testPlanet.mass) / lengthSquared));
        assertEquals("Wrong gravity.", gravityVector, force);
    }

}
