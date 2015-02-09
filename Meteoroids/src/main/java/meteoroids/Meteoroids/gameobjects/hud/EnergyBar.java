package meteoroids.Meteoroids.gameobjects.hud;

import org.lwjgl.opengl.GL11;

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
    private float size;
    
    public EnergyBar(Energy energy, float x, float y, float size) {
        this.energy = energy;
        this.red = 0.0f;
        this.green = 1.0f;
        this.position.set(x, y);
        this.size = energy.getMaxEnergy()/size;
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
        GL11.glColor4f(red+0.2f, green-0.2f, 0.0f, 0.5f);
        GL11.glBegin(GL11.GL_QUADS);
        
        GL11.glVertex2f(this.position.x, this.position.y);
        GL11.glVertex2f(this.position.x+(this.energy.getEnergy()/size), this.position.y);
        GL11.glVertex2f(this.position.x+(this.energy.getEnergy()/size), this.position.y+7);
        GL11.glVertex2f(this.position.x, this.position.y+7);
        
        GL11.glVertex2f(10, 30);
        
        GL11.glEnd();
    }
}
