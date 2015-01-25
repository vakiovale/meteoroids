package meteoroids.Meteoroids.gameobjects;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class GameObjectTest {

	GameObject gameObject;

	@Before
	public void setUp() {
		gameObject = new GameObject();
	}

	@Test
	public void testGameObject() {
		String str = gameObject.toString();
		assertEquals("toString() should return 'GameObject', but it returned '" 
				+ str + "'", "GameObject", str);
	}

}
