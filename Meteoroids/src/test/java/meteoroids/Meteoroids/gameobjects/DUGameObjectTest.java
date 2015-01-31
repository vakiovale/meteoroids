package meteoroids.Meteoroids.gameobjects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DUGameObjectTest {

    private DUGameObject object;
    
    @Before
    public void setUp() {
        object = new DUGameObject();
    }
    
    @Test
    public void testDying() {
        assertTrue("Object should die.", object.die());
    }
    
    @Test
    public void testDyingOnce() {
        object.die();
        assertTrue("Object should die only once.", !object.die());
    }
    
    @Test
    public void testDyingChecking() {
        object.die();
        assertTrue("Object should be dead.", object.isDead());
    }
    
    @Test
    public void testDyingChecking2() {
        assertTrue("Object should be alive.", !object.isDead());
    }

}
