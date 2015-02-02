package meteoroids.Meteoroids.gameobjects;

/**
 * Drawable game object.
 * 
 * @author vpyyhtia
 *
 */
public class DrawableGameObject extends GameObject implements Drawable {

    @Override
    public void draw() {
        System.out.println(this + " draw");
    }

}
