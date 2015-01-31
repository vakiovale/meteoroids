package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

import static org.junit.Assert.*;

import javax.vecmath.Vector2f;

import org.junit.Before;
import org.junit.Test;

public class BasicProjectileTest {

    BasicProjectile projectile;
    
    @Before
    public void setUp() {
        projectile = new BasicProjectile(100.0f, 100.0f);
    }
    
    @Test
    public void testKilled() {
        projectile.update(4999f);
        assertTrue("Projectile should be alive.", !projectile.isDead());
    }
    
    @Test
    public void testKilledLong() {
        projectile.update(5000);
        assertTrue("Projectile should be alive.", !projectile.isDead());
    }
    
    @Test
    public void testKilledLongLong() {
        projectile.update(1001021030);
        assertTrue("Projectile should be dead.", projectile.isDead());
    }
    
    @Test
    public void testProjectileCreation() {
        Vector2f position = projectile.getPosition();
        projectile = (BasicProjectile)projectile.getProjectile(projectile.getPosition(), projectile.getVelocity(), new Vector2f(0.0f, 0.0f));
        assertEquals("New projectile should be in the same place as the old one.", position, projectile.getPosition());      
    }

}
