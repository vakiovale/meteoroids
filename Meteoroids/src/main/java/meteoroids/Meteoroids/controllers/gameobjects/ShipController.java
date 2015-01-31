package meteoroids.Meteoroids.controllers.gameobjects;

import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.BasicGun;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.BasicProjectile;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.PlasmaProjectile;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Projectile;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Ship;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.ShootingShip;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Weapon;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Projectile.ProjectileType;

/**
 * Ship controller for managing ships.
 * 
 * @author vpyyhtia
 *
 */
public class ShipController {
    
    /**
     * Creates new ship.
     * 
     * @return Ship
     */
    public Ship getShip() {
        Ship ship = new Ship(100.0f, 300.0f, 100.0f);      
        
        BasicGun gun = new BasicGun();
        bindProjectile(gun, ProjectileType.BASIC_PROJECTILE);

        ship.bindWeapon(gun);

        return ship;
    }
    
    /**
     * Bind projectile to the ship's main weapon.
     * 
     * @param ship
     * @param projectileType
     */
    public void bindProjectile(Ship ship, ProjectileType projectileType) {
        Weapon weapon = ship.getWeapon();
        if(weapon != null) {
            bindProjectile(weapon, projectileType);
        }
    }
    
    /**
     * Bind projectile to the specific weapon.
     * 
     * @param weapon
     * @param projectileType
     */
    public void bindProjectile(Weapon weapon, ProjectileType projectileType) {
        Projectile projectile = null;
        switch (projectileType) {
            case BASIC_PROJECTILE:
                projectile = new BasicProjectile(0.0f, 0.0f);
                break;
            case PLASMA_PROJECTILE:
                projectile = new PlasmaProjectile(0.0f, 0.0f);
                break;
            default:
                break;
        }
        weapon.bindProjectile(projectile);
    }
    
    /**
     * Change to the next weapon from specific ship.
     * 
     * @param ship
     */
    public void changeWeapon(Ship ship) {
        ProjectileType[] types = ProjectileType.values();
        int projectileNumber = ship.getWeapon().getProjectile().getType()
                .ordinal();
        projectileNumber++;
        if(projectileNumber >= types.length) {
            projectileNumber = 0;
        }
        bindProjectile(ship, types[projectileNumber]);
    }
}
