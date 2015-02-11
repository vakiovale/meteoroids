package meteoroids.Meteoroids.controllers.physics;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.gameobjects.physicsobjects.PhysicsObject;
import meteoroids.Meteoroids.gameobjects.utilities.IEnergy;

/**
 * Handles the damage control of objects implementin IEnergy
 * 
 * @author vpyyhtia
 *
 */
public class DamageController implements Controller {

    @Override
    public void update(float deltaTime) {
        // Do nothing for now
    }

    /**
     * Hit some damage to the IEnergy object. There will be
     * a decrease in object's energy. An amount depends
     * on the object that is hitting.
     * 
     * @param objectToHit   object that will have a decrease in it's energy
     * @param hittingObject object that is hitting the other object
     */
    public void hitIEnergyObject(IEnergy objectToHit, PhysicsObject hittingObject) {
        objectToHit.decreseEnergy((int)(hittingObject.getMass()/10.0f));
    }
    
    /**
     * Hit some damage to the IEnergy object. There will be
     * a decrease in object's energy.
     * 
     * @param objectToHit object that will have a decrease in it's energy
     * @param damage      how much damage is dealt
     */
    public void hitIEnergyObjectWithDamage(IEnergy objectToHit, int damage) {
        objectToHit.decreseEnergy(damage);
    }

}
