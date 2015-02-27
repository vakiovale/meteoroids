package meteoroids.Meteoroids.controllers.utilities;

import java.awt.Font;
import java.io.InputStream;
import java.util.HashSet;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.gameobjects.Drawable;
import meteoroids.Meteoroids.utilities.Text;

/**
 * Handles drawing texts to the screen. Call initFont() method
 * before using this class to load the desired TrueType font.
 * 
 * @author vpyyhtia
 *
 */
public class TextHandler implements Drawable {

    private HashSet<Text> texts;

    private static TrueTypeFont font = null;
    private static TrueTypeFont fontSmall = null;
    private static TrueTypeFont fontBig = null;
    private static TrueTypeFont fontTiny = null;
    
    /**
     * Constructor for TextHandler
     * 
     */
    public TextHandler() {
        texts = new HashSet<>();
    }
    
    /**
     * Initialize font and load resources.
     * 
     * @param fontPath path to the font
     * @return true if font was loaded successfully
     */
    public static boolean initFont(String fontPath) {
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream(fontPath);
            
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);

            awtFont = awtFont.deriveFont(18f);
            fontTiny = new TrueTypeFont(awtFont, false);           
            
            awtFont = awtFont.deriveFont(36f);
            fontSmall = new TrueTypeFont(awtFont, false);
            
            awtFont = awtFont.deriveFont(60f);
            font = new TrueTypeFont(awtFont, false);
            
            awtFont = awtFont.deriveFont(72f);
            fontBig = new TrueTypeFont(awtFont, false);
            return true;     
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Adds a text to the screen
     * 
     * @param text
     */
    public void addText(String text) {
        texts.add(new Text(text));
    }
    
    /**
     * Adds a text to the screen
     * 
     * @param text
     */
    public void addText(Text text) {
        texts.add(text);
    }
    
    /**
     * Removes a specif text from the screen
     * 
     * @param text to be removed
     * @return true if text was successfully removed
     */
    public boolean removeText(Text text) {
        return texts.remove(text);
    }

    @Override
    public void draw() {
        if(!texts.isEmpty() && font != null) {
            Color.white.bind();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0, Game.WIDTH, Game.HEIGHT, 0, 1, -1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            for(Text text : texts) {
                TrueTypeFont f = null;
                switch(text.getSize()) {
                    case -1:
                        f = fontTiny;
                        break;
                    case 0:
                        f = fontSmall;
                        break;
                    case 1:
                        f = font;
                        break;
                    case 2:
                        f = fontBig;
                        break;
                }
                if(f != null) {
                    f.drawString(text.getX(), text.getY(), text.toString(), text.isActive() ? Color.yellow : Color.lightGray);
                }
            }
        }
    }
    
    @Override
    public int getID() {
        return -1;
    }

    public void clear() {
        texts.clear();
    }

}
