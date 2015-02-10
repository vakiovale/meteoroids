package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

import javax.vecmath.Vector2f;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.graphics.TextureDrawer;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.Movable;
import meteoroids.Meteoroids.gameobjects.physicsobjects.BoundingSphere;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;
import meteoroids.Meteoroids.gameobjects.utilities.ThrustFlame;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

/**
 * Player's ship class.
 * 
 * @author vpyyhtia
 *
 */
public class Ship extends PhysicsObject implements Movable, BoundingSphere, ShootingShip {

    protected ThrustFlame thrustFlame;
    protected float rotation;
    protected float radius;
    protected Weapon weapon;
    protected final float MAX_SPEED = 0.25f;
    
    public Ship() {
        this(0.0f, 0.0f, 100.0f);
    }

    public Ship(float posX, float posY) {
        this(posX, posY, 100.0f);
    }

    public Ship(float posX, float posY, float mass) {
        super(posX, posY, mass);
        this.thrustFlame = new ThrustFlame(posX, posY);
        this.rotation = 0.0f;
        this.radius = 10.0f;
        this.weapon = null;
        this.maxSpeed = MAX_SPEED;
    }
    
    @Override
    public void accelerate(float amount, float deltaTime) {
        Vector2f force = PhysicsObject.getRotationVector(rotation);
        force.normalize();
        force.scale((float)(((amount/((velocity.length()/2)+1.0f)) * deltaTime)));
        this.addForce(force);
    }

    @Override
    public void rotate(float angle, float deltaTime) {
        rotation = PhysicsObject.getRotation(rotation, angle, deltaTime);
    }
    
    @Override
    public void slowDown(float amount, float deltaTime) {
        float slowDownScale = 1.0f - (amount * deltaTime);
        if(slowDownScale > 0.0f) { 
            this.velocity.scale(slowDownScale);
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        GameObject.keepObjectInsideGameWindow(this.position);
        
        if(weapon != null) {
            weapon.update(deltaTime);
        }
        
        thrustFlame.addFlame(this.position.x, this.position.y - 4.0f);
    }
    
    /**
     * Ship rotation.
     * 
     * @return rotation (float number)
     */
    @Override
    public float getRotation() {
        return rotation;
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
        GL11.glVertex2f(this.position.x - radius, this.position.y - radius);
        GL11.glVertex2f(this.position.x, this.position.y - (radius / 3));
        GL11.glVertex2f(this.position.x + radius, this.position.y - radius);
        GL11.glVertex2f(this.position.x, this.position.y + radius);
        GL11.glEnd();
               
        GL11.glPopMatrix();
    }

    public String toString() {
        return "Ship " + id;
    }

    @Override
    public float getRadius() {
        return radius;
    }

    @Override
    public void bindWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public Projectile fire() {
        if(this.weapon != null) {
            Vector2f position = getPosition();
            Vector2f rotationVec = PhysicsObject.getRotationVector(rotation);
            Vector2f rotationTmp = (Vector2f)rotationVec.clone();
            rotationTmp.scale(10.5f);
            position.add(rotationTmp);
            
            return weapon.fire(position, getVelocity(), rotationVec);
        }
        return null;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }


}