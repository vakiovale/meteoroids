package meteoroids.Meteoroids.utilities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PointsTest {

    Points points;
    
    @Before
    public void setUp() {
        points = new Points();
    }
    
    @Test
    public void startingPointsZero() {
        assertEquals("Starting points should be zero", 0L, points.getPoints());
    }
    
    @Test
    public void addingNegativePoints() {
        points.addPoints(-1L);
        assertEquals("Points can't be negative", 0L, points.getPoints());
    }
    
    @Test
    public void settingNegativePoints() {
        points.setPoints(-5);
        assertEquals("Points can't be negative", 0L, points.getPoints());
    }
    
    @Test
    public void addingPoints() {
        points.addPoints(2);
        points.addPoints(10);
        assertEquals("Points should be 12", 12, points.getPoints());
    }
    
    @Test
    public void addingZeroPoints() {
        points.addPoints(0);
        points.addPoints(2);
        assertEquals("Points should be 2", 2, points.getPoints());
    }
    
    @Test
    public void settingPoints() {
        points.addPoints(5);
        points.setPoints(1);
        assertEquals("Points should be 1", 1, points.getPoints());
    }
    
    @Test
    public void settingZeroPoints() {
        points.addPoints(23);
        points.setPoints(0);
        assertEquals("Points should be zero", 0, points.getPoints());
    }
    
    @Test
    public void resettingPoints() {
        points.addPoints(121212);
        points.addPoints(134);
        points.reset();
        assertEquals("Points should be zero", 0, points.getPoints());
    }

}
