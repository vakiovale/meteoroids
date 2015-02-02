package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

import javax.vecmath.Vector2f;

/**
 * BasicGun for the space ships to use.
 * 
 * @author vpyyhtia
 *
 */
public class BasicGun extends Weapon {

    private Projectile projectileTemplate;
    private int coolOffTime;

    public BasicGun() {
        this.projectileTemplate = null;
        this.coolOffTime = 0;
    }

    @Override
    public void bindProjectile(Projectile projectile) {
        if(projectile != null) {
            this.projectileTemplate = projectile;
            coolOffTime = projectile.getCoolOffTime();
        }
    }

    @Override
    public Projectile fire(Vector2f position, Vector2f velocity,
            Vector2f orientation) {
        if(projectileTemplate != null && coolOffTime <= 0) {
            coolOffTime = projectileTemplate.getCoolOffTime();
            return projectileTemplate.getProjectile(position, velocity, orientation);
        } else {
            return null;
        }
    }

    @Override
    public void update(float deltaTime) {
        if(coolOffTime > 0) {
            coolOffTime -= deltaTime;
        }
        if(coolOffTime < 0)
            coolOffTime = 0;
    }

    @Override
    public int getID() {
        return this.getID();
    }

    @Override
    public Projectile getProjectile() {
        return projectileTemplate;
    }
}