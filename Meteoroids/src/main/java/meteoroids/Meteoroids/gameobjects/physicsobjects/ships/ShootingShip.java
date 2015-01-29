package meteoroids.Meteoroids.gameobjects.physicsobjects.ships;

/**
 * Interface for ships than can shoot.
 * 
 * @author vpyyhtia
 *
 */
public interface ShootingShip {
    
    /**
     * Bind a weapon to the ship.
     * 
     * @param weapon
     */
    public void bindWeapon(Weapon weapon);
    
    /**
     * Fire!
     * 
     * @return Projectile
     */
    public Projectile fire();
}
