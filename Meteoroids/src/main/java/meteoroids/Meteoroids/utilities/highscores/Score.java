package meteoroids.Meteoroids.utilities.highscores;

import java.io.Serializable;

/**
 * Holds data for player's score to be used in HighScores
 * 
 * @author vpyyhtia
 *
 */
public class Score implements Serializable, Comparable<Score> {
    
    private final String name;
    private final long points;
    
    public Score(String name, long points) {
        if(name.length() > 8) {
            this.name = name.substring(0, 8);    
        }
        else {
            this.name = name;
        }
        this.points = points;
    }
    
    public String getName() {
        return name;
    }
    
    public long getPoints() {
        return points;
    }
    
    @Override
    public int compareTo(Score score) {
        if(this.points == score.points) {
            return 0;
        }
        else if(this.points > score.points) {
            return -1;
        }
        else {
            return 1;
        }
    }
    
    @Override
    public String toString() {
        return name + ":\t" + points;
    }
}
