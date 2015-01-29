package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

import javax.vecmath.Vector2f;

public class BasicGun extends Weapon {

    private Projectile projectileTemplate;
    private int coolOffTime;
    
    public BasicGun(Projectile projectile) {
        this.projectileTemplate = projectile;
        this.coolOffTime = 0;
    }

    @Override
    public Projectile fire(Vector2f position, Vector2f velocity, Vector2f orientation) {
        if(coolOffTime <= 0) {
            coolOffTime = 100;
            return projectileTemplate.getProjectile(position, velocity, orientation);
        }
        else {
            return null;
        }
    }

    @Override
    public void update(float deltaTime) {
        if(coolOffTime > 0) {
            coolOffTime -= deltaTime;
        }
        if(coolOffTime < 0) coolOffTime = 0;
    }

    @Override
    public int getID() {
        return this.getID();
    }

}
