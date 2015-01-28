package meteoroids.Meteoroids.gameobjects.physicsobjects;

import org.lwjgl.opengl.GL11;

/**
 * Planet object.
 * 
 * @author vpyyhtia
 */
public class Planet extends GravityObject {
    
    private float radius;
    
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
    }
    
    @Override
    public void draw() {
    	GL11.glPushMatrix();
    	GL11.glTranslatef(this.position.x, this.position.y, 0);
    	GL11.glScalef(radius, radius, 1);
    	GL11.glBegin(GL11.GL_TRIANGLE_FAN);
    	GL11.glVertex2f(0, 0);
    	for(int i = 0; i <= 64; i++){ //NUM_PIZZA_SLICES decides how round the circle looks.
    	    double angle = Math.PI * 2 * i / 64;
    	    GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
    	}
    	GL11.glEnd();
    	GL11.glPopMatrix();
    }

}
