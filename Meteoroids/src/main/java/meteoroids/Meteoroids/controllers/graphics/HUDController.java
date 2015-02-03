package meteoroids.Meteoroids.controllers.graphics;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.gameobjects.Drawable;
import meteoroids.Meteoroids.gameobjects.hud.EnergyBar;

public class HUDController implements Controller, Drawable {

    private EnergyBar energyBar;
    
    public HUDController() {
        energyBar = new EnergyBar(100);
    }
    
    @Override
    public void update(float deltaTime) {
        // TODO Auto-generated method stub
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
    }

    @Override
    public int getID() {
        return -1;
    }

}
