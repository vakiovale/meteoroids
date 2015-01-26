package meteoroids.Meteoroids.gameobjects.physicsobjects;

import static org.junit.Assert.*;

import javax.vecmath.Vector2d;

import org.junit.Before;
import org.junit.Test;

public class PhysicsObjectTest {

    PhysicsObject po;
    
    class A extends PhysicsObject {
        public A(double mass) {
            super(mass);
        }
        
        public A() {
            super();
        }
    }
    
    @Before
    public void setUp() throws Exception {
        po = null;
    }

    @Test
    public void testNegativeMass() {
        po = new A(-1.0);
        assertEquals("Mass can't be smaller than 1.0.", 1.0, po.mass, 0.01);
    }
    
    @Test
    public void testZeroMass() {
        po = new A(0);
        assertEquals("Mass can't be zero.", 1.0, po.mass, 0.01);
    }
    
    @Test
    public void testSmallMass() {
        po = new A(0.52);
        assertEquals("Mass can't be smaller than 1.0.", 1.0, po.mass, 0.01);
    }
    
    @Test
    public void testClearForces() {
        po = new A();
        po.addForce(new Vector2d(-28.01, 155568.3331));
        po.clearForces();
        Vector2d force = po.getForces();
        Vector2d zero = new Vector2d(0.0, 0.0);
        assertEquals("Forces should be cleared.", zero.toString(), force.toString());
    }
    
    @Test
    public void testPositionAfterForce() {
        po = new A();
        po.addForce(new Vector2d(1.0, -1.0));
                
        po.update(2.0);
        po.clearForces();
        Vector2d correct = new Vector2d(4.0, -4.0);
        assertEquals("Position should be " + correct, correct.toString(), po.getPosition().toString());
    }
    
    @Test
    public void testSumOfForcesZero() {
        po = new A();
        po.addForce(new Vector2d(1.0, -1.0));
        po.addForce(new Vector2d(-1.0, 1.0));
        po.update(2.0);
        po.clearForces();
        Vector2d correct = new Vector2d(0.0, 0.0);
        assertEquals("Position should be " + correct, correct.toString(), po.getPosition().toString());
    }
}
