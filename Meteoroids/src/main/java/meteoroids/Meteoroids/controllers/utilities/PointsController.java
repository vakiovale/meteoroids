package meteoroids.Meteoroids.controllers.utilities;

import java.util.HashMap;
import java.util.Map;

import meteoroids.Meteoroids.gameobjects.GameObject;
import meteoroids.Meteoroids.gameobjects.utilities.Points;

/**
 * Handles calculating the points.
 * 
 * @author vpyyhtia
 *
 */
public class PointsController {
    
    private static Map<GameObject, Points> players = new HashMap<>();
    public static GameObject mainPlayer = null;
    
    /**
     * Reset points.
     * 
     */
    public static void resetPoints() {
        for(Map.Entry<GameObject, Points> entry : players.entrySet()) {
            entry.getValue().reset();
        }
    }
    
    public static void bindMainPlayer(GameObject player) {
        mainPlayer = player;
    }
    
    /**
     * Reset all.
     * 
     */
    public static void resetAll() {
        players = new HashMap<>();
    }
    
    /**
     * Add player to the game and assign zero points to that player.
     * 
     * @param player to be added
     */
    public static void addPlayer(GameObject player) {
        players.put(player, new Points());
    }
    
    /**
     * Add points.
     * 
     * @param player who gets the points
     * @param points to be added for the player
     */
    public static void addPoints(GameObject player, long points) {
        Points playersPoints = players.get(player);
        if(playersPoints != null) {
            playersPoints.addPoints(points);
        }
    }
    
    /**
     * Get player's points.
     * 
     * @param player
     * @return points from the player, -1 if player couldn't be found
     */
    public static long getPoints(GameObject player) {
        Points playersPoints = players.get(player);
        if(playersPoints != null) {
            return playersPoints.getPoints();
        }
        return -1;
    }
    
    /**
     * Get player's Points object
     * 
     * @param player
     * @return Points object from the player, null if player couldn't be found
     */
    public static Points getPointsObject(GameObject player) {
        return players.get(player);
    }
    
}
