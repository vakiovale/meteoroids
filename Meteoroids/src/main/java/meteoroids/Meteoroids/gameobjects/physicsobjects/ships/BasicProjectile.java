package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

import javax.vecmath.Vector2f;

import org.lwjgl.opengl.GL11;

/**
 * Basic projectile for ship's weapon.
 * 
 * @author vpyyhtia
 *
 */
public class BasicProjectile extends Projectile {

    private float startSpeed;
    private int killOffTime;
    private float radius;
    
    public BasicProjectile(float posX, float posY) {
        super(posX, posY);
        this.startSpeed = 10.0f;
        this.killOffTime = 5000;
        this.radius = 2.0f;
    }
    
    @Override
    public void update(float deltaTime) {
        // Calculate position with delta time
        Vector2f positionAdd = (Vector2f)velocity.clone();
        positionAdd.scale(deltaTime);       
        position.add(positionAdd);
        
        killOffTime -= deltaTime;
        
        if(killOffTime < 0) {
            this.die();
        }
    }
    
    @Override
    public Projectile getProjectile(Vector2f position, Vector2f velocity, Vector2f orientation) {
        BasicProjectile projectile = new BasicProjectile(position.x, position.y);
        
        velocity.add(orientation);
        orientation.scale(0.03f*startSpeed*velocity.length());
        projectile.setVelocity(orientation);
        
        return projectile;
    }
    
    @Override
    public void draw() {
        GL11.glPushMatrix();
        GL11.glColor3f(1.0f, 0.2f, 0.2f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(this.position.x-radius, this.position.y-radius);
        GL11.glVertex2f(this.position.x+radius, this.position.y-radius);
        GL11.glVertex2f(this.position.x+radius, this.position.y+radius);
        GL11.glVertex2f(this.position.x-radius, this.position.y+radius);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    @Override
    public float getRadius() {
        return radius;
    }
    
}
