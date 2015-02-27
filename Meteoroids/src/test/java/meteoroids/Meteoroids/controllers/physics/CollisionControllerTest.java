package meteoroids.Meteoroids.controllers.physics;

import static org.junit.Assert.*;

import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;

import org.junit.Before;
import org.junit.Test;

public class CollisionControllerTest {

    private CollisionController controller;
    
    @Before
    public void setUp() {
        controller = new CollisionController();
    }
    
    @Test
    public void testUpdate() {
        try {
            controller.update(11.0f);
        } catch(NullPointerException e) {
            fail("Nothing should happen.");
        }
    }
    
    @Test
    public void testNullUpdate() {
        try {
            controller.update(null, 11.0f);
        } catch(NullPointerException e) {
            fail("Nothing should happen.");
        }
    }
    
    @Test
    public void checkSphereCollision() {
        Asteroid a = new Asteroid(10.01f, 10.0f, 10.0f, 10.0f);
        Planet b = new Planet(30.0f, 10.0f, 10.0f, 10.0f);
        assertTrue("Objects should be colliding.", controller.checkSphereCollision(a, b));
    }
    
    @Test
    public void checkSphereCollisionAlmostTouch() {
        Asteroid a = new Asteroid(0.0f, 10.0f, 10.0f, 10.0f);
        Planet b = new Planet(0.0f, 40.0f, 20.0f, 20.0f);
        assertTrue("Objects should be touching.", controller.checkSphereCollision(a, b));
    }
    
    @Test
    public void checkSphereCollisionTouch() {
        Asteroid a = new Asteroid(0.0f, 10.0f, 10.0f, 10.0f);
        Planet b = new Planet(0.0f, 39.999f, 20.0f, 20.0f);
        assertTrue("Objects should be touching.", controller.checkSphereCollision(a, b));
    }

}
