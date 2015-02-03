package meteoroids.Meteoroids.gameobjects.utilities;

/**
 * Energy for game objects.
 * 
 * @author vpyyhtia
 *
 */
public class Energy {

    private int energy;
    private final int MAX_ENERGY;
    private boolean dead;
    
    public Energy(final int MAX_ENERGY) {
        this.MAX_ENERGY = MAX_ENERGY;
        this.energy = MAX_ENERGY;
    }
    
    /**
     * Check if energy is zero and object is dead.
     * 
     * @return
     */
    public boolean dead() {
        return dead;
    }
    
    /**
     * Decrease energy by one.
     * 
     * @return energy
     */
    public int decrease() {
        if(dead) return 0;
        decrease(1);
        return energy;
    }
    
    /**
     * Decrease energy.
     * 
     * @param damage
     * @return
     */
    public int decrease(int damage) {
        if(dead) return 0;
        energy -= damage;
        if(energy <= 0) {
            dead = true;
            energy = 0;
        }
        return energy;        
    }
    
    /**
     * Get the start (MAX) energy
     * 
     * @return MAX_ENERGY
     */
    public int getMaxEnergy() {
        return MAX_ENERGY;
    }
    
    /**
     * Get current energy.
     * 
     * @return energy
     */
    public int getEnergy() {
        return energy;
    }
}
