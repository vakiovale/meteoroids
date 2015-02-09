package meteoroids.Meteoroids.gameobjects.hud;

import meteoroids.Meteoroids.controllers.utilities.TextHandler;
import meteoroids.Meteoroids.gameobjects.utilities.Points;
import meteoroids.Meteoroids.utilities.Text;

/**
 * Shows player's points.
 * 
 * @author vpyyhtia
 *
 */
public class PointsBox extends HUDElement {

    private Points points;
    private TextHandler textHandler;
    private Text pointsText;
    
    public PointsBox(Points points) {
        this.points = points;
        this.textHandler = new TextHandler();
        textHandler.addText(new Text("Points: "));
        if(points != null) {
            pointsText = new Text(Long.toString(points.getPoints()));
        }
        textHandler.addText(pointsText);
    }
    
    @Override
    public void update(float deltaTime) {
        textHandler.removeText(pointsText);
        pointsText = new Text(Long.toString(points.getPoints()));
        textHandler.addText(pointsText);
    }    

    @Override
    public void draw() {
        textHandler.draw();
    }
    
}
