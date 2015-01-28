package meteoroids.Meteoroids.gameobjects.physicsobjects;

import meteoroids.Meteoroids.Game;

import org.lwjgl.opengl.GL11;

/**
 * Player's ship class.
 * 
 * @author vpyyhtia
 *
 */
public class Ship extends PhysicsObject {

	public Ship() {
		super(100.0f);
	}
	
	public Ship(float posX, float posY) {
		super(posX, posY, 100.0f);
	}
	
	public Ship(float posX, float posY, float mass) {
		super(posX, posY, mass);
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		// Keep ship inside the window
		if(this.position.x > Game.WIDTH) {
			this.position.x = 0.0f;
		}
		else if(this.position.x < 0.0f) {
			this.position.x = Game.WIDTH;
		}
		if(this.position.y > Game.HEIGHT) {
			this.position.y = 0.0f;
		}
		else if(this.position.y < 0.0f) {
			this.position.y = Game.HEIGHT;
		}
	}
	
	@Override
	public void draw() {
		GL11.glColor3f(1.0f, 0.2f, 0.2f);
	    GL11.glBegin(GL11.GL_QUADS);
	        GL11.glVertex2f(this.position.x-10, this.position.y-10);
	        GL11.glVertex2f(this.position.x, this.position.y-3);
	        GL11.glVertex2f(this.position.x+10,this.position.y-10);
	        GL11.glVertex2f(this.position.x,this.position.y+10);
	    GL11.glEnd();
	}
	
	public String toString() {
	    return "Ship";
	}
	
}
