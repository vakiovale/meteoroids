package meteoroids.Meteoroids.gameobjects.physicsobjects;

import javax.vecmath.Vector2f;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.gameobjects.Movable;
import meteoroids.Meteoroids.gameobjects.ThrustFlame;

import org.lwjgl.opengl.GL11;

/**
 * Player's ship class.
 * 
 * @author vpyyhtia
 *
 */
public class Ship extends PhysicsObject implements Movable {

    private ThrustFlame thrustFlame;
    private float rotation;

    public Ship() {
        this(0.0f, 0.0f, 100.0f);
    }

    public Ship(float posX, float posY) {
        this(posX, posY, 100.0f);
    }

    public Ship(float posX, float posY, float mass) {
        super(posX, posY, mass);
        thrustFlame = new ThrustFlame(posX, posY);
        rotation = 0.0f;
    }

    @Override
    public void accelerate(float amount, double deltaTime) {
        Vector2f force = new Vector2f((float) Math.cos(Math
                .toRadians(rotation + 90.0f)), (float) Math.sin(Math
                .toRadians(rotation + 90.0f)));
        force.normalize();
        force.scale((float) (amount * deltaTime));
        this.addForce(force);
    }

    @Override
    public void rotate(float angle, double deltaTime) {
        rotation += angle * deltaTime;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        
        // MAX speed for space ship
        if(this.velocity.length() > 0.3f) {
            this.velocity.normalize();
            this.velocity.scale(0.3f);
        }

        // Keep ship inside the window
        if (this.position.x > Game.WIDTH) {
            this.position.x = 0.0f;
        } else if (this.position.x < 0.0f) {
            this.position.x = Game.WIDTH;
        }
        if (this.position.y > Game.HEIGHT) {
            this.position.y = 0.0f;
        } else if (this.position.y < 0.0f) {
            this.position.y = Game.HEIGHT;
        }

        thrustFlame.addFlame(this.position.x, this.position.y - 4.0f);
    }

    @Override
    public void draw() {
        thrustFlame.draw();
        GL11.glPushMatrix();

        GL11.glTranslatef(this.position.x, this.position.y, 0);
        GL11.glRotatef(rotation, 0f, 0f, 1f);
        GL11.glTranslatef(-this.position.x, -this.position.y, 0);

        GL11.glColor3f(1.0f, 0.2f, 0.2f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(this.position.x - 10, this.position.y - 10);
        GL11.glVertex2f(this.position.x, this.position.y - 3);
        GL11.glVertex2f(this.position.x + 10, this.position.y - 10);
        GL11.glVertex2f(this.position.x, this.position.y + 10);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    public String toString() {
        return "Ship";
    }

}
