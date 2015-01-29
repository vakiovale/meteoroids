package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

import javax.vecmath.Vector2f;

import meteoroids.Meteoroids.gameobjects.Updateable;

/**
 * Abstract class for weapons.
 * 
 * @author vpyyhtia
 *
 */
public abstract class Weapon implements Updateable {

    /**
     * Fire a weapon.
     * @param velocity 
     * @param position 
     * 
     */
    public abstract Projectile fire(Vector2f position, Vector2f velocity, Vector2f orientation);
    
    /**
     * Bind projectile to the weapon.
     * 
     * @param projectile
     */
    public abstract void bindProjectile(Projectile projectile);
    
    /**
     * Currently binded projectile to the weapon.
     * 
     * @return Projectile
     */
    public abstract Projectile getProjectile();
}
