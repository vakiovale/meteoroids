package meteoroids.Meteoroids.utilities.highscores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import meteoroids.Meteoroids.controllers.utilities.ErrorController;

/**
 * High scores
 * 
 * @author vpyyhtia
 *
 */
public class HighScores {

    private List<Score> scores;
    
    private final String HIGHSCORES_PATH;
    
    /**
     * Constructor for high scores. Creates a new file in the specific path if there's not
     * already a high scores table in the file location.
     * 
     * @param PATH to the file location
     */
    public HighScores(final String PATH) {
        this.HIGHSCORES_PATH = PATH;
        scores = new ArrayList<>();
    }
    
    /**
     * Check if player with specific points is allowed to the Top Ten
     * 
     * @param points of the player
     * @return true if player is allowed to the top ten
     */
    public boolean topTenCheck(long points) {
        loadScoreFile();
        Collections.sort(scores);
        
        if(scores == null || scores.size() == 0) {
            return true;
        }
        if(scores.size() < 10) {
            return true;
        }
        else {
            return points > scores.get(9).getPoints();
        }
    }
    
    public List<Score> getScores() {
        loadScoreFile();
        Collections.sort(scores);
        return scores;
    }
    
    /**
     * Save Score to the high scores table.
     * 
     * @param score of the player
     */
    public void saveScore(Score score) {
        loadScoreFile();
        FileOutputStream scoresOutputStream = null;
        ObjectOutputStream scoresObjectStream = null;
        
        try {
            scoresOutputStream = new FileOutputStream(HIGHSCORES_PATH);
            scoresObjectStream = new ObjectOutputStream(scoresOutputStream);
            scores.add(score);
            scoresObjectStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            ErrorController.highScoresSaveFileError();
            System.exit(0);
        } catch (IOException e) {
            ErrorController.highScoresSaveFileError();
            System.exit(0);
        } finally {
            if(scoresObjectStream != null) {
                try {
                    scoresObjectStream.flush();
                    scoresObjectStream.close();
                } catch (IOException e) {
                    ErrorController.highScoresSaveFileError();
                    System.exit(0);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void loadScoreFile() {
        FileInputStream scoresInputStream = null;
        ObjectInputStream scoresObjectStream = null;
        
        try {
            scoresInputStream = new FileInputStream(HIGHSCORES_PATH);
            scoresObjectStream = new ObjectInputStream(scoresInputStream);
            scores = (ArrayList<Score>)scoresObjectStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No high scores.");
        } catch (IOException e) {
            ErrorController.highScoresFileError();
            System.exit(0);
        } catch (ClassNotFoundException e) {
            ErrorController.highScoresFileError();
            System.exit(0);
        } finally {
            if(scoresObjectStream != null) {
                try {
                    scoresObjectStream.close();
                } catch (IOException e) {
                    ErrorController.highScoresFileError();
                    System.exit(0);
                }
            }
        }
    }
    
}
