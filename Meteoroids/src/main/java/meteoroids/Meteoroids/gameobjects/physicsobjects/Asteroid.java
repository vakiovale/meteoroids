package meteoroids.Meteoroids.gameobjects.physicsobjects;

import meteoroids.Meteoroids.Game;

import org.lwjgl.opengl.GL11;

/**
 * Asteroid class.
 * 
 * @author vpyyhtia
 *
 */
public class Asteroid extends PhysicsObject {

	public Asteroid() {
		super(500.0f);
	}
	
	public Asteroid(float posX, float posY) {
		super(posX, posY, 500.0f);
	}
	
	public Asteroid(float posX, float posY, float mass) {
		super(posX, posY, mass);
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		// Keep asteroid inside the window
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
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(this.position.x, this.position.y);
            GL11.glVertex2f(this.position.x+2,this.position.y);
            GL11.glVertex2f(this.position.x+2,this.position.y+2);
            GL11.glVertex2f(this.position.x,this.position.y+2);
        GL11.glEnd();
    }
	
	public String toString() {
	    return "Asteroid";
	}
}
