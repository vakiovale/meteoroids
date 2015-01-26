package meteoroids.Meteoroids.gameobjects.physicsobjects;

import org.lwjgl.opengl.GL11;

/**
 * Player's ship class.
 * 
 * @author vpyyhtia
 *
 */
public class Ship extends PhysicsObject {

	public Ship() {
		super(400.0f, 300.0f, 100.0f);
	}
	
	@Override
	public void draw() {
	    GL11.glBegin(GL11.GL_TRIANGLES);
	        GL11.glVertex2f(this.position.x, this.position.y);
	        GL11.glVertex2f(this.position.x+20,this.position.y);
	        GL11.glVertex2f(this.position.x+10,this.position.y+20);
	    GL11.glEnd();
	}
	
	public String toString() {
	    return "Ship";
	}
	
}
