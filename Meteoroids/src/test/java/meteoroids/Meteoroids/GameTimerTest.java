package meteoroids.Meteoroids;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTimerTest {

	GameTimer timer;
	
	@Before
	public void setUp() {
		timer = new GameTimer();
	}

	@Test
	public void testTimeIncreases() {
		long time = timer.getTime();	
		long newTime = timer.getTime();
		assertTrue("Current time is wrong. First time was " + time + " "
				+ "and second time was " + newTime, (time <= newTime));
	}
	
	@Test
	public void testDeltaTime() {
		long delta = timer.getDeltaTime();
		long time = timer.getTime();
		assertTrue("Something wrong with delta time. Delta time was " + delta,
				delta < time);
	}

}
