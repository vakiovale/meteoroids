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

	private float radius;
	
	public Asteroid() {
		this(0.0f, 0.0f, 500.0f, 2.5f);
	}
	
	public Asteroid(float posX, float posY) {
		this(posX, posY, 500.0f, 2.5f);
	}
	
	public Asteroid(float posX, float posY, float mass) {
		this(posX, posY, mass, 2.5f);
	}
	
	public Asteroid(float posX, float posY, float mass, float radius) {
		super(posX, posY, mass);
		this.radius = radius;
	}	
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
	
        // MAX speed for asteroid
        if(this.velocity.length() > 0.2f) {
            this.velocity.normalize();
            this.velocity.scale(0.2f);
        }
		
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
    	GL11.glColor3f(0.5f, 0.5f, 0.5f);
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(this.position.x-radius, this.position.y-radius);
            GL11.glVertex2f(this.position.x+radius,this.position.y-radius);
            GL11.glVertex2f(this.position.x+radius,this.position.y+radius);
            GL11.glVertex2f(this.position.x-radius,this.position.y+radius);
        GL11.glEnd();
    }
	
	public String toString() {
	    return "Asteroid";
	}
}
