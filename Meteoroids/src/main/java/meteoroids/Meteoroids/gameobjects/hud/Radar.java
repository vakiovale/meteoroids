package meteoroids.Meteoroids.gameobjects.hud;

import java.util.List;

import javax.vecmath.Vector2f;

import org.lwjgl.opengl.GL11;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Planet;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Projectile;

/**
 * Radar - HUD Element
 * 
 * @author vpyyhtia
 *
 */
public class Radar extends HUDElement {
    
    private GameObjectController objectController;
    private GameObject objectToFollow;
    
    /**
     * Constructor for Radar
     * 
     * @param objectController for drawing objects in the radar
     * @param object that is going to be the center object in the radar
     */
    public Radar(GameObjectController objectController, GameObject object) {
        this.objectController = objectController;
        this.objectToFollow = object;
    }
    
    @Override
    public void update(float deltaTime) {
        // Do nothing
    }    
    
    @Override
    public void draw() {
        List<PhysicsObject> objects = objectController.getPhysicsObjects();
        for(PhysicsObject o : objects) {
            if(o instanceof Projectile) {
                continue;
            }
            
            Vector2f vector = calculateObjectPositionFromRadar(o.getPosition());
            if(outOfRadar(vector)) {
                continue;
            }
                       
            if(vector.length() > 100.0f) {
                continue;
            }
            
            drawObjectToRadar(o, vector);
        }     
        
        drawPlayerToRadar();
        drawRadarCircle();                
    }

    private void drawRadarCircle() {
        GL11.glPushMatrix(); 

        GL11.glColor4f(1.0f, 0.0f, 0.0f, 0.1f);
        GL11.glTranslatef(150, 150, 0);
        GL11.glScalef(100, 100, 1);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex2f(0, 0);
        for(int i = 0; i <= 64; i++) {
            double angle = Math.PI * 2 * i / 64;
            GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
        }
        GL11.glEnd();
    
        GL11.glPopMatrix();  
    }

    private void drawPlayerToRadar() {
        GL11.glPushMatrix();

        GL11.glTranslatef(150, 150, 0);
        
        GL11.glColor4f(1.0f, 1.0f, 1-0f, 1.0f);
        GL11.glBegin(GL11.GL_QUADS);
        
        GL11.glVertex2f(-1.5f, -1.5f);
        GL11.glVertex2f(1.5f, -1.5f);
        GL11.glVertex2f(1.5f, 1.5f);
        GL11.glVertex2f(-1.5f, 1.5f);

        GL11.glEnd();
        
        GL11.glPopMatrix();    
    }

    private void drawObjectToRadar(GameObject o, Vector2f vector) {
        float x = vector.x;
        float y = vector.y;
        
        float red = 1.0f;
        float green = 0.0f;
        float blue = 0.0f;
        
        float radius = 1.5f;
        
        if(o instanceof Planet) {
            red = 0.0f;
            blue = 1.0f;
            radius = 6.0f;
        }
        
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, Game.WIDTH, 0, Game.HEIGHT, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
                    
        GL11.glPushMatrix();

        GL11.glTranslatef(150, 150, 0);
        
        GL11.glColor4f(red, green, blue, 0.5f);
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        
        GL11.glVertex2f(x-radius, y-radius/2);
        GL11.glVertex2f(x-radius/2, y-radius);
        GL11.glVertex2f(x+radius/2, y-radius);
        GL11.glVertex2f(x+radius, y-radius/2);
        GL11.glVertex2f(x+radius, y+radius/2);
        GL11.glVertex2f(x+radius/2, y+radius);
        GL11.glVertex2f(x-radius/2, y+radius);
        GL11.glVertex2f(x-radius, y+radius/2);

        GL11.glEnd();
        
        GL11.glPopMatrix();
        
    }

    private boolean outOfRadar(Vector2f vector) {
        return vector.length() > 100.0f;
    }

    private Vector2f calculateObjectPositionFromRadar(Vector2f object) {
        Vector2f vector = object;
        vector.sub(objectToFollow.getPosition());
        vector.scale(0.05f);
        return vector;
    }
}
