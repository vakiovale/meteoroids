package meteoroids.Meteoroids.gameobjects;

import javax.vecmath.Vector2f;

import meteoroids.Meteoroids.Game;

/**
 * Basic GameObject
 * 
 * @author vpyyhtia
 *
 */
public class GameObject implements IPosition {

    protected Vector2f position;
    protected int id;

    public GameObject() {
        position = new Vector2f(0, 0);
        id = 0;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    @Override
    public Vector2f getPosition() {
        return (Vector2f)position.clone();
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position;
    }

    @Override
    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    @Override
    public float getX() {
        return position.x;
    }

    @Override
    public float getY() {
        return position.y;
    }

    public String toString() {
        return "GameObject";
    }

    /**
     * Keeps position vector inside the Game window
     * 
     * @param position vector
     */
    public static void keepObjectInsideGameWindow(Vector2f position) {
        if(position.x > Game.WIDTH) {
            position.x = 0.0f;
        } else if(position.x < 0.0f) {
            position.x = Game.WIDTH;
        }
        if(position.y > Game.HEIGHT) {
            position.y = 0.0f;
        } else if(position.y < 0.0f) {
            position.y = Game.HEIGHT;
        }        
    }
}
