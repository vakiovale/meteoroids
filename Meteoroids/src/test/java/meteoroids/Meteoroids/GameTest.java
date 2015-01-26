package meteoroids.Meteoroids;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	Game game;
	
	@Before
	public void setUp() {
		game = new Game();
	}
	
    @Test
    public void testGameApplication() {
    	boolean b = game.init();
    	game.destroy();
    	assertTrue("Initializing LWJGL failed", b);
    }
    
    @Test
    public void testTimeFactor() {
        double timeFactor = game.getTimeFactor();
        assertEquals("Time factor should be 1.0 as default, but it was "
                + timeFactor + ".", 1.0, timeFactor, 0.01);
    }
    
}