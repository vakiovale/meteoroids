package meteoroids.Meteoroids.controllers.graphics;

import java.util.List;

import javax.vecmath.Vector2f;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.gameobjects.Drawable;
import meteoroids.Meteoroids.gameobjects.GameObject;

import org.lwjgl.opengl.GL11;

/**
 * Handles drawing all the objects. OpenGL is also handled here.
 * 
 * @author vpyyhtia
 *
 */
public class GraphicsController implements Controller {

    private int width;
    private int height;
    private boolean followPlayerCamera;
    private GameObject followObject;

    /**
     * GraphicsController.
     * 
     * @param width of the screen
     * @param height of the screen
     */
    public GraphicsController(int width, int height) {
        this.width = width;
        this.height = height;
        this.followPlayerCamera = false;
        this.followObject = null;
    }

    @Override
    public void update(float deltaTime) {
        // Clear the screen and depth buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    /**
     * Draw objects to the screen.
     * 
     * @param objects list of Drawable objects
     */
    public void draw(List<Drawable> objects) {
        for(Drawable d : objects) {
            draw(d);
        }
    }
    
    /**
     * Set camera to follow player.
     * 
     * @param true if camera follow player
     */
    public void setFollowPlayerCamera(boolean follow, GameObject object) {
        followPlayerCamera = follow;
        if(followPlayerCamera) {
            followObject = object;
        }
        else {
            followObject = null;
        }
    }
    
    /**
     * Draw a single object to the screen.
     * 
     * @param object drawable
     */
    public void draw(Drawable object) {        
        if(followPlayerCamera) {
            drawFollowCamera();
        }
        else {
            drawBasic();
        }
        object.draw();        
    }
    
    /**
     * Get the middle point in the screen.
     * 
     * @return vector
     */
    public Vector2f getMidPoint() {
        if(followObject == null) {
            return new Vector2f(width/2, height/2);
        }
        return followObject.getPosition();
    }

    private void drawFollowCamera() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(followObject.getPosition().x - Game.WIDTH / 2.0, 
                followObject.getPosition().x + Game.WIDTH / 2.0, 
                followObject.getPosition().y - Game.HEIGHT / 2.0, 
                followObject.getPosition().y + Game.HEIGHT / 2.0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glDisable(GL11.GL_TEXTURE_2D);        
    }

    private void drawBasic() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, 0, height, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glDisable(GL11.GL_TEXTURE_2D);        
    }

    /**
     * Initialize OpenGL.
     * 
     * @return true
     */
    public boolean init() {           
        GL11.glShadeModel(GL11.GL_SMOOTH);       
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);                   
  
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);               
        GL11.glClearDepth(1);                                      
  
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, 0, height, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        
        return true;
    }
}
