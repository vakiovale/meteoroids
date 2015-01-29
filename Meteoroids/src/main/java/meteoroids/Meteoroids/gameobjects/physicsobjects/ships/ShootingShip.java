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
    
    /**
     * Ship's main weapon.
     * 
     * @return Weapon
     */
    public Weapon getWeapon();
}
