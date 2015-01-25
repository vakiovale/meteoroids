package meteoroids.Meteoroids.gameobjects;

import static org.junit.Assert.*;

import javax.vecmath.Vector2d;

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
	
	@Test
	public void testCorrectPosition() {
		Vector2d position = gameObject.getPosition();
		Vector2d pos = new Vector2d(0, 0);
		assertEquals("Position was incorrect. It was " + 
		 position + " when it should be " + pos, pos.toString(), position.toString());
	}
	
	@Test
	public void testIndirectPositionChange() {
		Vector2d position = gameObject.getPosition();
		position.set(1.0, 1.0);
		Vector2d position2 = gameObject.getPosition();
		Vector2d zero = new Vector2d(0.0, 0.0);
		assertEquals("Position should not be changed indirectly.", zero.toString(), position2.toString());
	}
	
	@Test
	public void moveObject() {
		Vector2d newPosition = new Vector2d(10.0, 28.0);
		gameObject.setPosition(newPosition);
		Vector2d currentPosition = gameObject.getPosition();
		assertEquals("Position should be " + newPosition + ", but it was " + currentPosition,
				newPosition.toString(), currentPosition.toString());
	}
	
	@Test
	public void testCorrectXYPositions() {
		gameObject.setPosition(new Vector2d(-8.0, 1.7));
		Vector2d position = gameObject.getPosition();
		assertEquals("Position should be (-8.0, 1.7), but it was (" + position.x + 
				", " + position.y + ".", new Vector2d(-8.0, 1.7).toString(), position.toString());
	}

}
