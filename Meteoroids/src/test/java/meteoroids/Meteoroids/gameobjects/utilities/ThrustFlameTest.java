package meteoroids.Meteoroids.gameobjects.utilities;

import static org.junit.Assert.*;

import java.util.ArrayDeque;

import meteoroids.Meteoroids.gameobjects.utilities.ThrustFlame;
import meteoroids.Meteoroids.gameobjects.utilities.ThrustFlame.Flame;

import org.junit.Before;
import org.junit.Test;

public class ThrustFlameTest {

    private ThrustFlame flame;
    
    @Before
    public void setUp() {
        this.flame = new ThrustFlame(100.0f, 100.0f);
    }
    
    @Test
    public void testAddingFlame() {
        for(int i = 1; i < 10; i++) {
            flame.addFlame(i, i);
        }
        ArrayDeque<Flame> flames = flame.getFlames();
        assertEquals("Problem adding flames.", 10, flames.size());
    }
    
    @Test
    public void testUpdating() {
        for(int i = 5; i < 23; i++) {
            flame.addFlame(i, i);
        }
        ArrayDeque<Flame> flames = flame.getFlames();
        flame.addFlame(1.0f, 1.0f);
        ArrayDeque<Flame> flames2 = flame.getFlames();
        flames.pollLast();
        
        Flame f1 = flames.pollLast();
        Flame f2 = flames2.pollLast();
        assertEquals("Flames should be the same.", f1.getX(), f2.getX(), 0.01f);
        assertEquals("Flames should be the same.", f1.getY(), f2.getY(), 0.01f);
    }

}
