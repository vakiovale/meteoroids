package meteoroids.Meteoroids.controllers.input;

import org.lwjgl.input.Keyboard;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.gameobjects.physicsobjects.ships.Ship;

/**
 * Handles the game input.
 * 
 * @author vpyyhtia
 *
 */
public class InputController implements Controller {

    private Ship ship;
    private GameObjectController objectController;
    
    public InputController(GameObjectController objectController, Ship ship) {
        this.objectController = objectController;
        this.ship = ship;
    }
    
    @Override
    public void update(float deltaTime) {
        pollInputs(deltaTime);
    }

    private void pollInputs(float deltaTime) {
        while(Keyboard.next()) {
            if(Keyboard.getEventKey() == Keyboard.KEY_LCONTROL) {
                if(!Keyboard.getEventKeyState()) {
                    objectController.changeWeapon(ship);
                }
            }
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            ship.rotate(0.3f, deltaTime);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            ship.rotate(-0.3f, deltaTime);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            ship.accelerate(0.002f, deltaTime);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            ship.accelerate(-0.002f, deltaTime);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            objectController.fire(ship);
        }
    }
}
