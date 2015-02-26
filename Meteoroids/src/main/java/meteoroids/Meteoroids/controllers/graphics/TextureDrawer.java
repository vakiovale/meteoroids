package meteoroids.Meteoroids.controllers.graphics;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import meteoroids.Meteoroids.gameobjects.Drawable;

/**
 * Draws OpenGL texture
 * 
 * @author vpyyhtia
 *
 */
public class TextureDrawer implements Drawable {

    private Texture texture;
    
    public TextureDrawer(Texture texture) {
        this.texture = texture;
    }
    
    @Override
    public void draw() {
        draw(0.0f, 0.0f);
    }
    
    /**
     * Draw a circle texture.
     * 
     * @param x
     * @param y
     * @param radius
     * @param blood turns the circle more red, value from 0.0f-1.0f
     */
    public void draw(float x, float y, float radius, float blood) {
        GL11.glEnable(GL11.GL_TEXTURE_2D);              
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);         
         
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            
        Color.white.bind();
        texture.bind();
        GL11.glColor4f(1.0f, 1.0f-(1.0f-blood), 1.0f-(1.0f-blood), 1.0f);     
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex2f(x,y);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex2f(x+radius,y);
            GL11.glTexCoord2f(0,0);
            GL11.glVertex2f(x+radius,y+radius);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex2f(x,y+radius);
        GL11.glEnd();
            
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }
    
    /**
     * Draws a texture.
     * 
     * @param x
     * @param y
     */
    public void draw(float x, float y) {
        GL11.glEnable(GL11.GL_TEXTURE_2D);              
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);         
         
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            
        Color.white.bind();
        texture.bind();
             
        GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex2f(x,y);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex2f(x+texture.getTextureWidth(),y);
            GL11.glTexCoord2f(0,0);
            GL11.glVertex2f(x+texture.getTextureWidth(),y+texture.getTextureHeight());
            GL11.glTexCoord2f(1,0);
            GL11.glVertex2f(x,y+texture.getTextureHeight());
        GL11.glEnd();
            
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }
    
    @Override
    public int getID() {
        return -1;
    }
}
