package meteoroids.Meteoroids.gameobjects;

import meteoroids.Meteoroids.utilities.RandomGenerator;

import org.lwjgl.opengl.GL11;

/**
 * A star.
 * 
 * @author vpyyhtia
 *
 */
public class Star extends DrawableGameObject {

    private float radius;
    private float red;
    private float green;
    private float blue;
    
    public Star(float radius, float x, float y) {
        this.radius = radius;
        this.position.set(x, y);
        red = radius/4+0.3f*RandomGenerator.random();
        green = radius/4+0.2f*RandomGenerator.random();
        blue = radius/4+0.4f*RandomGenerator.random();
    }
    
    @Override
    public void draw() {
        GL11.glPushMatrix();
        GL11.glColor3f(red, green, blue);
        GL11.glTranslatef(this.position.x, this.position.y, 0);
        GL11.glScalef(radius, radius, 1);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex2f(0, 0);
        for(int i = 0; i <= 24; i++) {
            double angle = Math.PI * 2 * i / 24;
            GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }
}
