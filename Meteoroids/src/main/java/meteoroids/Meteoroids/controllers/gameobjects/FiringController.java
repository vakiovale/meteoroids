package meteoroids.Meteoroids.controllers.gameobjects;

import java.util.ArrayList;
import java.util.List;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Projectile;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.ShootingShip;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Weapon;

/**
 * Controller for shooting and firing.
 * 
 * @author vpyyhtia
 *
 */
public class FiringController implements Controller {

    List<Projectile> projectiles;
    GameObjectController objectController;
    
    public FiringController(GameObjectController objectController) {
        projectiles = new ArrayList<>();
        this.objectController = objectController;
    }
    
    @Override
    public void update(float deltaTime) {
        for(int i = projectiles.size()-1; i >= 0; i--) {
            if(projectiles.get(i).isDead()) {
                objectController.killGameObject(projectiles.get(i));
                projectiles.remove(i);
            }
        }
    }

    public Projectile fire(ShootingShip ship) {
        Projectile projectile = ship.fire();
        if(projectile != null) {
            projectiles.add(projectile);
        }
        return projectile;
    }

}
