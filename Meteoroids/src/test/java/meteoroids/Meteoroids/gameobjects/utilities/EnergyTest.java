package meteoroids.Meteoroids.gameobjects.utilities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EnergyTest {

    Energy energy;
    
    @Before
    public void setUp() {
        energy = new Energy(10);
    }
    
    @Test
    public void checkPercentageFull() {
        assertEquals("Percentage should be 100", energy.getPercentage(), 1.0, 0.01);
    }
    
    @Test
    public void checkPercentageZero() {
        energy.decrease(10);
        assertEquals("Percentage should be 0", energy.getPercentage(), 0.0, 0.01);
    }
    
    @Test
    public void checkPercentageQuarter() {
        energy = new Energy(100);
        energy.decrease(75);
        assertEquals("Percentage should be 0.25", energy.getPercentage(), 0.25, 0.01);
    }
    
    @Test
    public void zeroMaxEnergyNotAllowed() {
        energy = new Energy(0);
        assertTrue("Start energy should not be 0", energy.getEnergy() != 0);
    }
    
    @Test
    public void testAlive() {
        assertTrue("Energy object should not be dead", !energy.dead());
    }
    
    @Test
    public void testAlive2() {
        energy.decrease(9);
        assertTrue("Energy object should not be dead", !energy.dead());
    }    

    @Test
    public void testDead() {
        energy.decrease(9);
        energy.decrease(1);
        assertTrue("Energy object should be dead", energy.dead());
    }
    
    @Test
    public void testReviving() {
        energy.decrease(9);
        energy.decrease(1);
        energy.reset();
        assertTrue("Energy object should not be dead", !energy.dead());
    }
    
    @Test
    public void testCriticalHit() {
        energy.decrease(121231132);
        assertEquals("Energy should be zero", energy.getEnergy(), 0);
    }
    
    @Test
    public void testCriticalHitDead() {
        energy.decrease(121231132);
        assertTrue("Energy object should be dead", energy.dead());
    }
    
    @Test
    public void testNegativeDamage() {
        energy.decrease(-123414123);
        assertEquals("Energy should not be changed", energy.getEnergy(), energy.getMaxEnergy());
    }
    

}
