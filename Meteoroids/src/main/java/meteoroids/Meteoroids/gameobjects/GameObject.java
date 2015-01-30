package meteoroids.Meteoroids.gameobjects;

import javax.vecmath.Vector2f;

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
}
