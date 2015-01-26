package meteoroids.Meteoroids.gameobjects.physicsobjects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {

    Ship ship;
    
    @Before
    public void setUp() {
        ship = new Ship();
    }
    
    @Test
    public void testShipMass() {
        double mass = ship.getMass();
        assertEquals("Ship mass should be 10.0, but it was " + mass + ".", 10.0, mass, 0.01);
    }

}
