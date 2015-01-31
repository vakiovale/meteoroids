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

    protected int coolOffTime;
    protected ProjectileType type;

    public Projectile(float posX, float posY) {
        super(posX, posY, 1.0f);
        coolOffTime = 100;
        type = ProjectileType.BASIC_PROJECTILE;
    }

    /**
     * Get projectile.
     * 
     * @return projectile
     */
    public abstract Projectile getProjectile(Vector2f position,
            Vector2f velocity, Vector2f orientation);

    /**
     * Clone projectile from this projectile.
     * 
     * @param position of the new projectile
     */
    public abstract Projectile clone(Vector2f position);

    /**
     * Cool off time.
     * 
     * @return time to wait after a second shot
     */
    public int getCoolOffTime() {
        return coolOffTime;
    }

    public ProjectileType getType() {
        return type;
    }

    /**
     * Types for different projectiles.
     * 
     * @author vpyyhtia
     *
     */
    public enum ProjectileType {
        BASIC_PROJECTILE, PLASMA_PROJECTILE
    }
}
