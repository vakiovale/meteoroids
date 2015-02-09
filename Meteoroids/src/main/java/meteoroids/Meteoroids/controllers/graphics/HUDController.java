package meteoroids.Meteoroids.controllers.graphics;

import java.util.ArrayList;
import java.util.List;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.gameobjects.Drawable;
import meteoroids.Meteoroids.gameobjects.hud.HUDElement;

/**
 * Handles all the HUD elements.
 * 
 * @author vpyyhtia
 *
 */
public class HUDController implements Controller, Drawable {

    private List<HUDElement> hudElements;
        
    public HUDController() {
        this.hudElements = new ArrayList<>();
    }
    
    public void addHUDElement(HUDElement element) {
        hudElements.add(element);
    }
    
    @Override
    public void update(float deltaTime) {
        for(HUDElement hudElement : hudElements) {
            hudElement.update(deltaTime);
        }
    }

    @Override
    public void draw() {
        for(int i = 0; i < hudElements.size(); i++) {
            hudElements.get(i).draw();
        }
    }

    @Override
    public int getID() {
        return -1;
    }

}
