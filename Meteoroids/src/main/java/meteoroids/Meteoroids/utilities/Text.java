package meteoroids.Meteoroids.utilities;

import java.util.UUID;

import meteoroids.Meteoroids.Game;

/**
 * Create text.
 * 
 * @author vpyyhtia
 *
 */
public class Text {

    private String text;
    private float x;
    private float y;
    private boolean active;
    private UUID id;
    private int size;
    
    /**
     * Constructor for Text that's almost centered in the window
     * 
     * @param text
     */
    public Text(String text) {
        this(text, Game.WIDTH/2-(Game.WIDTH/14), Game.HEIGHT/2);
    }
    
    /**
     * Constructor for Text with a specific location
     * 
     * @param text
     * @param x
     * @param y
     */
    public Text(String text, float x, float y) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.size = 1;
        active = false;
        id = UUID.randomUUID();
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public int getSize() {
        return size;
    }
    
    public String getText() {
        return text;
    }
    
    public void toggleActive() {
        active = !active;
    }
    
    public boolean isActive() {
        return active;
    }
    
    @Override
    public String toString() {
        return text;
    }
    
    @Override
    public int hashCode() {
        return text.hashCode();
    }    
    
    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Text)) {
            return false;
        }
        else {
            Text text = (Text)object;
            return text.getText().equals(this.text) && text.id == this.id;  
        }
    }

    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
}
