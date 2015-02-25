package meteoroids.Meteoroids.gameobjects.utilities.highscores;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import meteoroids.Meteoroids.utilities.highscores.Score;

import org.junit.Before;
import org.junit.Test;

public class ScoreTest {

    Score score1;
    Score score2;
    Score score3;
    Score score4;
    Score score5;

    List<Score> scores;
    
    @Before
    public void setUp() {
        score1 = new Score("Ville", 25);
        score2 = new Score("Jaakko", 20);
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        score4 = new Score("Pelle", 9);
        score3 = new Score("Keijo", 20);
        score5 = new Score("Huono", 1);
        scores = new ArrayList<>();
    }
    
    @Test
    public void testCorrectOrder() {
        scores.add(score3);
        scores.add(score5);
        scores.add(score2);
        scores.add(score4);
        scores.add(score1);
        Collections.sort(scores);
        
        assertEquals(scores.get(0).getName(), "Ville");
        assertEquals(scores.get(1).getName(), "Jaakko");
        assertEquals(scores.get(2).getName(), "Keijo");
        assertEquals(scores.get(3).getName(), "Pelle");
        assertEquals(scores.get(4).getName(), "Huono");
    }
    
    @Test
    public void testlongName() {
        score1 = new Score("ValtteriLIIANPITKÄNIMILIAANPITKÄNIMI", 1000);
        assertEquals(score1.getName(), "Valtteri");
    }

}
