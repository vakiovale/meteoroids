package meteoroids.Meteoroids.gameobjects;

import static org.junit.Assert.*;

import javax.vecmath.Vector2f;

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
		Vector2f position = gameObject.getPosition();
		Vector2f pos = new Vector2f(0, 0);
		assertEquals("Position was incorrect. It was " + 
		 position + " when it should be " + pos, pos.toString(), position.toString());
	}
	
	@Test
	public void testIndirectPositionChange() {
		Vector2f position = gameObject.getPosition();
		position.set(1.0f, 1.0f);
		Vector2f position2 = gameObject.getPosition();
		Vector2f zero = new Vector2f(0.0f, 0.0f);
		assertEquals("Position should not be changed indirectly.", zero.toString(), position2.toString());
	}
	
	@Test
	public void moveObject() {
		Vector2f newPosition = new Vector2f(10.0f, 28.0f);
		gameObject.setPosition(newPosition);
		Vector2f currentPosition = gameObject.getPosition();
		assertEquals("Position should be " + newPosition + ", but it was " + currentPosition,
				newPosition.toString(), currentPosition.toString());
	}
	
	@Test
	public void testCorrectXYPositions() {
		gameObject.setPosition(new Vector2f(-8.0f, 1.7f));
		Vector2f position = gameObject.getPosition();
		assertEquals("Position should be (-8.0, 1.7), but it was (" + position.x + 
				", " + position.y + ".", new Vector2f(-8.0f, 1.7f).toString(), position.toString());
	}

}
