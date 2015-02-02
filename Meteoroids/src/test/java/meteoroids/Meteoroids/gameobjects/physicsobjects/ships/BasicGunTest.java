package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

import static org.junit.Assert.*;

import javax.vecmath.Vector2f;

import org.junit.Before;
import org.junit.Test;

public class BasicGunTest {

    private BasicGun basicGun;
    
    @Before
    public void setUp() {
        basicGun = new BasicGun();
    }
    
    @Test
    public void testProjectileBinding1() {
        BasicProjectile projectile = new BasicProjectile(20.0f, 20.0f);
        basicGun.bindProjectile(projectile);
        Projectile newProjectile = basicGun.getProjectile();
        assertTrue("Wrong projectile in weapon.", newProjectile!=null && 
                newProjectile instanceof BasicProjectile);
    }
    
    @Test
    public void testProjectileBinding2() {
        PlasmaProjectile projectile = new PlasmaProjectile(20.0f, 20.0f);
        basicGun.bindProjectile(projectile);
        Projectile newProjectile = basicGun.getProjectile();
        assertTrue("Wrong projectile in weapon.", newProjectile!=null && 
                newProjectile instanceof PlasmaProjectile);
    }
    
    @Test
    public void testBindNullProjectile() {
        Projectile nullProjectile = null;
        basicGun.bindProjectile(nullProjectile);
        assertTrue("Should be null.", basicGun.getProjectile()==null);
    }
    
    @Test
    public void testChangeProjectile() {
        Projectile projectile = new PlasmaProjectile(20.0f, 20.0f);
        basicGun.bindProjectile(projectile);
        basicGun.fire(new Vector2f(-1.0f, 1.0f), new Vector2f(-1.0f, 1.0f), new Vector2f(-1.0f, 1.0f));
        Projectile projectile2 = new BasicProjectile(1.0f, 1.0f);
        basicGun.bindProjectile(projectile2);
        basicGun.fire(new Vector2f(-1.0f, 1.0f), new Vector2f(-1.0f, 1.0f), new Vector2f(-1.0f, 1.0f));
        assertEquals("Wrong projectile binded.", projectile2.getPosition(), basicGun.getProjectile().getPosition());
    }

}
