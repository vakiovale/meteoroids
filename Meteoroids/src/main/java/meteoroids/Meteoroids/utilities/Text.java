package meteoroids.Meteoroids.utilities;

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
    
    public Text(String text) {
        this(text, Game.WIDTH/2-(Game.WIDTH/14), Game.HEIGHT/2);
    }
    
    public Text(String text, float x, float y) {
        this.text = text;
        this.x = x;
        this.y = y;
        active = false;
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
            return text.getText().equals(this.text);  
        }
    }

    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
}
