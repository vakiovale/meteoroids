package meteoroids.Meteoroids.gameobjects.physicsobjects;

import org.lwjgl.opengl.GL11;

/**
 * Asteroid class.
 * 
 * @author vpyyhtia
 *
 */
public class Asteroid extends PhysicsObject {

	public Asteroid() {
		super(400.0f, 300.0f, 500.0f);
	}
	
    @Override
    public void draw() {
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(this.position.x, this.position.y);
            GL11.glVertex2f(this.position.x+20,this.position.y);
            GL11.glVertex2f(this.position.x+20,this.position.y+20);
            GL11.glVertex2f(this.position.x,this.position.y+20);
        GL11.glEnd();
    }
	
	public String toString() {
	    return "Asteroid";
	}
}
