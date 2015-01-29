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
		} catch(Exception e) {
			assert(true);
			return;
		}
		assertTrue("GameController's update function should not update on its own.", false);
	}
	
	@Test
	public void testInterface() {
		try {
			Controller c = controller;
		} catch(Exception e) {
			assertTrue("GameController problem with Controller interface", false);
		}
		assert(true);
	}

}
