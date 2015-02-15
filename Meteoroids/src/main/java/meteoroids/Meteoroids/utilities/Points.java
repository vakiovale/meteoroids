package meteoroids.Meteoroids.utilities;

/**
 * Player's points
 * 
 * @author vpyyhtia
 *
 */
public class Points {

    private long points;
    
    public Points() {
        points = 0L;
    }
    
    /**
     * Add points to the player. It's also possible to give negative points.
     * 
     * @param points to be added to the total number of points
     */
    public void addPoints(long points) {
        long tmp = this.points;
        this.points += points;
        if(this.points < 0) {
            this.points = tmp;
        }
    }
    
    /**
     * Set points.
     * 
     * @param points
     */
    public void setPoints(long points) {
        this.points = points;
    }
    
    public long getPoints() {
        return points;
    }

    public void reset() {
        points = 0L;        
    }
}
