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
public class EnergyBar extends HUDElement {

    private Energy energy;
    private float red;
    private float green;
    
    public EnergyBar(Energy energy) {
        this.energy = energy;
        this.red = 0.0f;
        this.green = 1.0f;
    }
        
    @Override
    public void update(float deltaTime) {
        // Do nothing
    }    

    @Override
    public void draw() {
        this.red = ((float)this.energy.getMaxEnergy()/this.energy.getMaxEnergy())
                   -((float)this.energy.getEnergy()/this.energy.getMaxEnergy());
        this.green = (float)this.energy.getEnergy()/this.energy.getMaxEnergy();
        GL11.glColor3f(red+0.2f, green-0.2f, 0.0f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(10, 10);
        GL11.glVertex2f(this.energy.getEnergy()*5, 10);
        GL11.glVertex2f(this.energy.getEnergy()*5, 30);
        GL11.glVertex2f(10, 30);
        GL11.glEnd();
    }
}
