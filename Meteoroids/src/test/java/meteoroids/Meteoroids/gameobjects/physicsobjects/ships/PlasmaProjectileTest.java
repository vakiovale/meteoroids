package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlasmaProjectileTest {

    PlasmaProjectile projectile;
    
    @Before
    public void setUp() {
        projectile = new PlasmaProjectile(20.0f, 20.0f);
    }
    
    @Test
    public void testPlasmaProjectileCoolOffTime() {
        assertEquals("Wrong cool off time.", 500, projectile.coolOffTime);
    }

}
