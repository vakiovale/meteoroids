package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

import static org.junit.Assert.*;

import javax.vecmath.Vector2f;

import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Ship;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {

    Ship ship;
    
    @Before
    public void setUp() {
        ship = new Ship();
    }
    
    @Test
    public void testShipPosition() {
        float posX = 0.001f;
        float posY = -1.981f;
        ship = new Ship(posX, posY);
        assertEquals("Ship X position is wrong.", posX, ship.getPosition().x, 0.001f);
        assertEquals("Ship Y position is wrong.", posY, ship.getPosition().y, 0.001f);
    }
    
    @Test
    public void testShipMass() {
        float mass = 102.1f;
        ship = new Ship(100.0f, -129f, mass);
        assertEquals("Ship mass should be " + mass + " but it was " + ship.getMass() + ".", mass, ship.getMass(), 0.01);
    }
    
    @Test
    public void testUpdate() {
        ship = new Ship(1.0f, 1.0f);
        ship.update(10f);
        assertEquals("Ship position should not have changed.", new Vector2f(1.0f, 1.0f), ship.getPosition());
    }
    
    @Test
    public void testShipDefaultMass() {
        float mass = ship.getMass();
        assertEquals("Ship mass should be 100.0, but it was " + mass + ".", 100.0, mass, 0.01);
    }
    
    @Test
    public void testRotate() {
        ship.rotate(10.0f, 2.5f);
        assertEquals("Ship rotation is wrong.", 25.0f, ship.getRotation(), 0.001f);
    }
    
    @Test
    public void testDoubleRotate() {
        ship.rotate(10.0f, 2.5f);
        ship.rotate(-12, 3.0f);
        assertEquals("Ship rotation is wrong.", -11.0f, ship.getRotation(), 0.001f);
    }
    
    @Test
    public void testRotationBack() {
        ship.rotate(-3600.1f, 1.0f);
        assertEquals("Ship rotation is wrong.", -0.1f, ship.getRotation(), 0.001f);
    }
    
    @Test
    public void testRotation() {
        ship.rotate(-3600.1f, 1.0f);
        assertEquals("Ship rotation is wrong.", -0.1f, ship.getRotation(), 0.001f);
    }
    
    @Test
    public void testFireWithNoWeapon() {
        Projectile projectile = ship.fire();
        assertTrue("There was a projectile without weapon.", projectile == null);
    }
    
    @Test
    public void testFireWeapon() {
        BasicGun gun = new BasicGun();
        gun.bindProjectile(new BasicProjectile(ship.getPosition().x, ship.getPosition().y));
        ship.bindWeapon(gun);
        // Update gun so that it's able to fire
        gun.update(10000.0f);
        Projectile projectile = ship.fire();
        assertTrue("There should be a projectile after firing.", projectile != null);
    }
    
    @Test
    public void testWeaponBinding() {
        Weapon weapon = new BasicGun();
        ship.bindWeapon(weapon);
        assertTrue("Ship has a wrong weapon.", weapon==ship.getWeapon());
    }
    
    /** TODO: This test doesn't work anymore like this. Calculating position after certain acceleration
     *  is pretty difficult now and proper testing needs some work to do.
     */
    /*
    @Test
    public void testAccelerate() {
        ship = new Ship(20.0f, 20.0f, 1.0f);
        ship.rotate(-45.0f, 1.0f);
        ship.accelerate(0.01f, 10.0f);
        ship.update(10.0f);

        float posX = 27.07f;
        float posY = 27.07f;
        assertEquals("Ship X position is wrong.", posX, ship.getPosition().x, 0.01f);
        assertEquals("Ship Y position is wrong.", posY, ship.getPosition().y, 0.01f);
    }*/

}
