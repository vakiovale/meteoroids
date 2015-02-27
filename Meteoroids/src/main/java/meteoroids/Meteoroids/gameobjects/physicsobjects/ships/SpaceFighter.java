package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

import javax.vecmath.Vector2f;

import org.lwjgl.opengl.GL11;

import meteoroids.Meteoroids.controllers.graphics.TextureDrawer;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;

/**
 * SpaceFighter extended from a default Ship. More advanced ship with nice textures.
 * 
 * @author vpyyhtia
 *
 */
public class SpaceFighter extends Ship {

    private TextureDrawer textureDrawer;
    private TextureDrawer textureThrustDrawer;
    private TextureDrawer textureBreakDrawer;
    private TextureDrawer textureFireDrawer;
    private boolean accelerateOn;
    private boolean breakOn;
    private boolean fire;
    
    public SpaceFighter() {
        this(0.0f, 0.0f, 100.0f);
    }

    public SpaceFighter(float posX, float posY) {
        this(posX, posY, 100.0f);
    }

    public SpaceFighter(float posX, float posY, float mass) {
        super(posX, posY, mass);
        this.textureDrawer = null;
        this.textureThrustDrawer = null;
        this.textureBreakDrawer = null;
        this.textureFireDrawer = null;
        this.accelerateOn = false;
        this.breakOn = false;
        this.fire = false;
    }
    
    public void bindTextureDrawer(TextureDrawer textureDrawer,
                                  TextureDrawer textureThrustDrawer,
                                  TextureDrawer textureBreakDrawer,
                                  TextureDrawer textureFireDrawer) {
        this.textureDrawer = textureDrawer;
        this.textureThrustDrawer = textureThrustDrawer;
        this.textureBreakDrawer = textureBreakDrawer;
        this.textureFireDrawer = textureFireDrawer;
    }

    @Override
    public void accelerate(float amount, float deltaTime) {
        super.accelerate(amount, deltaTime);
        accelerateOn = true;
    }
    
    @Override
    public void slowDown(float amount, float deltaTime) {
        super.slowDown(amount, deltaTime);
        breakOn = true;
    }
    
    @Override
    public Projectile fire() {
        if(this.weapon != null) {
            Vector2f position = getPosition();
            Vector2f rotationVec = PhysicsObject.getRotationVector(rotation);
            Vector2f rotationTmp = (Vector2f)rotationVec.clone();
            rotationTmp.scale(10.5f);
            position.add(rotationTmp);
            Projectile projectile = weapon.fire(position, getVelocity(), rotationVec);
            if(projectile != null) {
                fire = true;
            }
            return projectile; 
        }
        return null;
    }
    
    @Override
    public void draw() {
        thrustFlame.draw();
        GL11.glPushMatrix();

        GL11.glTranslatef(this.position.x, this.position.y, 0);
        GL11.glRotatef(rotation, 0f, 0f, 1f);
        GL11.glTranslatef(-this.position.x, -this.position.y, 0);     

        if(textureDrawer != null) {
            if(accelerateOn) {
                accelerateOn = false;
                textureThrustDrawer.draw(this.position.x-16.0f, this.position.y-16.0f);
            }
            else {
                textureDrawer.draw(this.position.x-16.0f, this.position.y-16.0f);
            }
            if(breakOn) {
                breakOn = false;
                textureBreakDrawer.draw(this.position.x-16.0f, this.position.y-16.0f);
            }
            if(fire) {
                fire = false;
                textureFireDrawer.draw(this.position.x-16.0f, this.position.y-16.0f);
            }
        }        
               
        GL11.glPopMatrix();
    }    
    
}
