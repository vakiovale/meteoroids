package meteoroids.Meteoroids.controllers.physics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PhysicControllerTest {

    private PhysicsController controller;
    
    @Before
    public void setUp() {
        this.controller = new PhysicsController();
    }
        
    @Test
    public void testNullUpdate() {
        try {
            controller.update(0.0f);
            controller.update(10.0f);
        } catch(NullPointerException e) {
            fail("Nothing should happen.");
        }    
    
    }
    
    @Test
    public void testNullUpdate2() {
        try {
            controller.update(null, null, 100.0f);
        } catch(NullPointerException e) {
            fail("Nothing should happen.");
        }
    }

}
