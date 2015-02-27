package meteoroids.Meteoroids.controllers.gameobjects;

import java.util.ArrayList;
import java.util.List;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Projectile;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.ShootingShip;

/**
 * Controller for shooting and firing. FiringController will hold
 * all the projectiles in the game world and destroy them when needed.
 * 
 * @author vpyyhtia
 *
 */
public class FiringController implements Controller {

    private List<Projectile> projectiles;
    private GameObjectController objectController;

    /**
     * Constructor for FiringController.
     * 
     * @param objectController current GameObjectController in the game
     */
    public FiringController(GameObjectController objectController) {
        projectiles = new ArrayList<>();
        this.objectController = objectController;
    }

    @Override
    public void update(float deltaTime) {
        for(int i = projectiles.size() - 1; i >= 0; i--) {
            if(projectiles.get(i).isDead()) {
                objectController.killGameObject(projectiles.get(i));
                projectiles.remove(i);
            }
        }
    }

    /**
     * Uses a ShootingShip for shooting a projectile. This method will
     * use ship to fire and grabs the new projectile from the ship's guns.
     * This projectile will be added to the game world. 
     * 
     * @param ship that's going to shoot
     * @return new Projectile from the ship
     */
    public Projectile fire(ShootingShip ship) {
        Projectile projectile = ship.fire();
        if(projectile != null) {
            projectiles.add(projectile);
        }
        return projectile;
    }

}
