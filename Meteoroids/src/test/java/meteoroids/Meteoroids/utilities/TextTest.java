package meteoroids.Meteoroids.utilities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TextTest {

    Text text1;
    Text text2;
    Text text3;
    
    @Before
    public void setUp() {
        text1 = new Text("Hello World!", 0, 0);
        text2 = new Text("Hello World!", 0, 0);
        text3 = new Text("Hello Magic World!", 10, 10);
    }
    
    @Test
    public void testDifferentObjects() {
        assertTrue("Objects should not be same", !text1.equals(text2));
    }
    
    @Test
    public void testSameObjects() {
        assertTrue("Objects should be same", text1.equals(text1));
        assertTrue("Objects should be same", text3.equals(text3));
    }
    
    @Test
    public void testActive() {
        assertTrue("Object should not be active", !text2.isActive());
    }
    
    @Test
    public void testToggleActive() {
        text2.toggleActive();
        assertTrue("Object should be active", text2.isActive());
    }
    
    @Test
    public void testToggleMultipleActiveOn() {
        for(int i = 0; i < 11; i++) text2.toggleActive();
        assertTrue("Object should be active", text2.isActive());
    }
    
    public void testToggleMultipleActiveOff() {
        for(int i = 0; i < 138; i++) text3.toggleActive();
        assertTrue("Object should not be active", !text3.isActive());
    }

}
