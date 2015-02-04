package meteoroids.Meteoroids.gameobjects.physicsobjects;

import meteoroids.Meteoroids.gameobjects.utilities.Energy;
import meteoroids.Meteoroids.gameobjects.utilities.IEnergy;

import org.lwjgl.opengl.GL11;

/**
 * Planet object.
 * 
 * @author vpyyhtia
 */
public class Planet extends GravityObject implements BoundingSphere, IEnergy {

    private float radius;
    private Energy energy;
    private float red;
    private float green;
    private float blue;

    public Planet() {
        this(0.0f, 0.0f, 100.0f, 500.0f);
    }

    public Planet(float radius) {
        this(0.0f, 0.0f, radius, 500.0f);
    }

    public Planet(float radius, float mass) {
        this(0.0f, 0.0f, radius, mass);
    }

    public Planet(float posX, float posY, float radius) {
        this(posX, posY, radius, 1000.0f);
    }

    public Planet(float posX, float posY, float radius, float mass) {
        super(posX, posY, mass);
        this.radius = radius;
        this.energy = new Energy(100);
        this.red = (float)Math.random();
        this.green = (float)Math.random();
        this.blue = (float)Math.random();
    }

    @Override
    public void draw() {
        GL11.glPushMatrix();
        GL11.glColor3f(red, green, blue);
        GL11.glTranslatef(this.position.x, this.position.y, 0);
        GL11.glScalef(radius, radius, 1);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex2f(0, 0);
        for(int i = 0; i <= 64; i++) {
            double angle = Math.PI * 2 * i / 64;
            GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    @Override
    public float getRadius() {
        return radius;
    }
    
    /**
     * Update planet's status
     * 
     */
    @Override
    public void update(float deltaTime) {
        this.dead = energy.dead();
    }
    
    @Override
    public String toString() {
        return "Planet " + this.id;
    }

    @Override
    public void decreaseEnergy() {
        energy.decrease();        
    }

    @Override
    public void decreseEnergy(int amount) {
        energy.decrease(amount);
    }

    @Override
    public int getEnergyValue() {
        return energy.getEnergy();
    }
    
    @Override
    public Energy getEnergy() {
        return energy;
    }

}
