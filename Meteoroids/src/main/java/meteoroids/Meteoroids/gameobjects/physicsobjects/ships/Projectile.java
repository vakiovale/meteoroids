package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

import javax.vecmath.Vector2f;

import meteoroids.Meteoroids.gameobjects.physicsobjects.BoundingSphere;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;

/**
 * Abstract class for projectiles.
 * 
 * @author vpyyhtia
 *
 */
public abstract class Projectile extends PhysicsObject implements BoundingSphere {
      
    public Projectile(float posX, float posY) {
        super(posX, posY, 1.0f);
    }
    
    /**
     * Get projectile.
     * 
     * @return projectile
     */
    public abstract Projectile getProjectile(Vector2f position, Vector2f velocity, Vector2f orientation);
}
