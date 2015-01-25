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
    
}