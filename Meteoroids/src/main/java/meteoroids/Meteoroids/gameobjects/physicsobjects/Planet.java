package meteoroids.Meteoroids.gameobjects.physicsobjects;

import org.lwjgl.opengl.GL11;

/**
 * Planet object.
 * 
 * @author vpyyhtia
 */
public class Planet extends GravityObject {
    
    private float size;
    
    public Planet() {
        this(0.0f, 0.0f, 100.0f, 1000.0f);
    }
    
    public Planet(float size) {
        this(0.0f, 0.0f, size, 1000.0f);
    }
    
    public Planet(float size, float mass) {
        this(0.0f, 0.0f, size, mass);
        
    }
    
    public Planet(float posX, float posY, float size) {
        this(posX, posY, size, 1000.0f);        
    }
    
    public Planet(float posX, float posY, float size, float mass) {
        super(posX, posY, mass);
        this.size = size;
    }
    
    @Override
    public void draw() {
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2f(this.position.x, this.position.y);
            GL11.glVertex2f(this.position.x+size,this.position.y);
            GL11.glVertex2f(this.position.x+size,this.position.y+size);
            GL11.glVertex2f(this.position.x, this.position.y+size);
        GL11.glEnd();
    }

}
