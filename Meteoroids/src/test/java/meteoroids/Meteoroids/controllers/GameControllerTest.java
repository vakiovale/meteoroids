package meteoroids.Meteoroids.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameControllerTest {

	GameController controller;
	
	@Before
	public void setUp() {
		controller = new GameController();
	}

	@Test
	public void testGameController() {
		assert(true);
	}

	@Test
	public void testUpdate() {
		try {
			controller.update(0.2f);
			controller.update(0.8f);
		} catch(Exception e) {
			assertTrue("GameController's update function doesn't work", false);
		}
		assert(true);
	}
	
	@Test
	public void testInterface() {
		try {
			Controller c = controller;
			c.update(100.0f);
		} catch(Exception e) {
			assertTrue("GameController problem with Controller interface", false);
		}
		assert(true);
	}

}
