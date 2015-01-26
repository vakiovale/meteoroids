package meteoroids.Meteoroids.gameobjects.physicsobjects;

import static org.junit.Assert.*;

import javax.vecmath.Vector2f;

import org.junit.Before;
import org.junit.Test;

public class PhysicsObjectTest {

    PhysicsObject po;
    
    class A extends PhysicsObject {
        public A(float mass) {
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
        po = new A(-1.0f);
        assertEquals("Mass can't be smaller than 1.0.", 1.0, po.mass, 0.01);
    }
    
    @Test
    public void testZeroMass() {
        po = new A(0);
        assertEquals("Mass can't be zero.", 1.0, po.mass, 0.01);
    }
    
    @Test
    public void testSmallMass() {
        po = new A(0.52f);
        assertEquals("Mass can't be smaller than 1.0.", 1.0, po.mass, 0.01);
    }
    
    @Test
    public void testClearForces() {
        po = new A();
        po.addForce(new Vector2f(-28.01f, 155568.331f));
        po.clearForces();
        Vector2f force = po.getForces();
        Vector2f zero = new Vector2f(0.0f, 0.0f);
        assertEquals("Forces should be cleared.", zero.toString(), force.toString());
    }
    
    @Test
    public void testPositionAfterForce() {
        po = new A();
        po.addForce(new Vector2f(1.0f, -1.0f));
                
        po.update(2.0f);
        po.clearForces();
        Vector2f correct = new Vector2f(4.0f, -4.0f);
        assertEquals("Position should be " + correct, correct.toString(), po.getPosition().toString());
    }
    
    @Test
    public void testSumOfForcesZero() {
        po = new A();
        po.addForce(new Vector2f(1.0f, -1.0f));
        po.addForce(new Vector2f(-1.0f, 1.0f));
        po.update(2.0f);
        po.clearForces();
        Vector2f correct = new Vector2f(0.0f, 0.0f);
        assertEquals("Position should be " + correct, correct.toString(), po.getPosition().toString());
    }
}
