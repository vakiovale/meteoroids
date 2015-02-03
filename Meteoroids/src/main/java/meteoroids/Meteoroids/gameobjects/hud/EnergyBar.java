package meteoroids.Meteoroids.gameobjects.hud;

import org.lwjgl.opengl.GL11;

import meteoroids.Meteoroids.gameobjects.DUGameObject;
import meteoroids.Meteoroids.gameobjects.utilities.Energy;

/**
 * Energy bar for game object.
 * 
 * @author vpyyhtia
 *
 */
public class EnergyBar extends DUGameObject {

    private Energy energy;
    
    public EnergyBar(int energy) {
        this.energy = new Energy(energy);
    }
    
    public void decreaseEnergy() {
        this.energy.decrease();
    }
    
    @Override
    public void update(float deltaTime) {
        // Do nothing
    }    

    @Override
    public void draw() {
        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(10, 10);
        GL11.glVertex2f(this.energy.getEnergy()*5, 10);
        GL11.glVertex2f(this.energy.getEnergy()*5, 30);
        GL11.glVertex2f(10, 30);
        GL11.glEnd();
    }
}
