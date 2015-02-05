package meteoroids.Meteoroids.controllers.resources;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.HashSet;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.gameobjects.Drawable;

/**
 * Handles drawing texts to the screen.
 * 
 * @author vpyyhtia
 *
 */
public class TextHandler implements Drawable {

    private HashSet<Text> texts;

    private TrueTypeFont font;
    
    public TextHandler() {
        texts = new HashSet<>();
        initFont();
    }
    
    private void initFont() {
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("Munro.ttf");
            
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(30f*2);
            font = new TrueTypeFont(awtFont, false);
     
        } catch (Exception e) {
            // TODO: Error message
            e.printStackTrace();
            System.exit(0);
        }        
    }
    
    public void addText(String text) {
        texts.add(new Text(text));
    }
    
    public void addText(Text text) {
        texts.add(text);
    }
    
    public boolean removeText(Text text) {
        return texts.remove(text);
    }

    @Override
    public void draw() {
        if(!texts.isEmpty()) {
            Color.white.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0, Game.WIDTH, Game.HEIGHT, 0, 1, -1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            for(Text text : texts) {
                font.drawString(text.getX(), text.getY(), text.toString(), Color.lightGray);
            }
        }
        
    }

    @Override
    public int getID() {
        return -1;
    }

}
