package meteoroids.Meteoroids.gameobjects.physicsobjects;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.gameobjects.Movable;
import meteoroids.Meteoroids.gameobjects.ThrustFlame;

import org.lwjgl.opengl.GL11;

/**
 * Asteroid class.
 * 
 * @author vpyyhtia
 *
 */
public class Asteroid extends PhysicsObject implements Movable, BoundingSphere {

    private float radius;
    private float rotation;
    ThrustFlame flame;

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
        this.rotation = 0.0f;
        this.flame = new ThrustFlame(posX, posY);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        rotate(1.0f * this.velocity.length(), deltaTime);

        // MAX speed for asteroid
        if(this.velocity.length() > 0.2f) {
            this.velocity.normalize();
            this.velocity.scale(0.2f);
        }

        // Keep asteroid inside the window
        if(this.position.x > Game.WIDTH) {
            this.position.x = 0.0f;
        } else if(this.position.x < 0.0f) {
            this.position.x = Game.WIDTH;
        }
        if(this.position.y > Game.HEIGHT) {
            this.position.y = 0.0f;
        } else if(this.position.y < 0.0f) {
            this.position.y = Game.HEIGHT;
        }

        this.flame.addFlame(this.position.x, this.position.y);
    }

    @Override
    public void draw() {
        this.flame.draw();

        GL11.glPushMatrix();

        GL11.glTranslatef(this.position.x, this.position.y, 0);
        GL11.glRotatef(rotation, 0f, 0f, 1f);
        GL11.glTranslatef(-this.position.x, -this.position.y, 0);

        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glColor3f(0.4f, 0.4f, 0.4f);
        GL11.glVertex2f(this.position.x - radius * 0.5f, this.position.y);
        GL11.glVertex2f(this.position.x - radius * 0.75f, this.position.y - radius * 0.5f);
        GL11.glColor3f(0.6f, 0.6f, 0.6f);
        GL11.glVertex2f(this.position.x - radius * 0.4f, this.position.y - radius * 0.8f);
        GL11.glVertex2f(this.position.x + radius * 0.45f, this.position.y - radius * 0.5f);
        GL11.glColor3f(0.5f, 0.5f, 0.5f);
        GL11.glVertex2f(this.position.x + radius * 0.65f, this.position.y - radius * 0.65f);
        GL11.glVertex2f(this.position.x + radius, this.position.y - radius * 0.5f);
        GL11.glColor3f(0.3f, 0.3f, 0.3f);
        GL11.glVertex2f(this.position.x + radius * 0.9f, this.position.y);
        GL11.glVertex2f(this.position.x + radius * 0.5f, this.position.y + radius * 0.5f);
        GL11.glColor3f(0.4f, 0.4f, 0.4f);
        GL11.glVertex2f(this.position.x + radius * 0.75f, this.position.y + radius * 0.8f);
        GL11.glVertex2f(this.position.x + radius * 0.45f, this.position.y + radius * 0.75f);
        GL11.glColor3f(0.6f, 0.6f, 0.6f);
        GL11.glVertex2f(this.position.x, this.position.y + radius);
        GL11.glVertex2f(this.position.x - radius * 0.5f, this.position.y + radius * 0.5f);
        GL11.glVertex2f(this.position.x - radius * 0.8f, this.position.y + radius * 0.6f);
        GL11.glEnd();

        GL11.glPopMatrix();
    }

    public String toString() {
        return "Asteroid " + this.id;
    }

    @Override
    public void accelerate(float amount, float deltaTime) {
        // Don't accelerate
    }

    @Override
    public void rotate(float angle, float deltaTime) {
        this.rotation += angle * deltaTime;
    }
    
    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public float getRadius() {
        return radius;
    }
}
