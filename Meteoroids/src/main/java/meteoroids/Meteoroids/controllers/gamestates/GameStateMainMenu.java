package meteoroids.Meteoroids.controllers.gamestates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.gameobjects.GameObjectController;
import meteoroids.Meteoroids.controllers.utilities.TextHandler;
import meteoroids.Meteoroids.gameobjects.StarField;
import meteoroids.Meteoroids.gameobjects.physicsobjects.Asteroid;
import meteoroids.Meteoroids.utilities.Text;

/**
 * Main menu.
 * 
 * @author vpyyhtia
 *
 */
public class GameStateMainMenu extends GameStateMachine {

    private StarField starField;
    private GameObjectController objectController;
    private TextHandler textHandler;
    
    private Text gameText;
    private Text playText;
    private Text highScoreText;
    private Text exitText;
    private List<Text> texts;
    
    private MenuButton active;    
    private HashMap<MenuButton, Text> buttons;
            
    public GameStateMainMenu(GameStateController controller) {
        super(controller);
        gameState = GameState.MAIN_MENU;
        objectController = new GameObjectController();
        starField = objectController.getStarField(Game.WIDTH, Game.HEIGHT, 100);
        Asteroid asteroids[] = objectController.getAsteroids(25, 100.0f, 15.0f);
        for(Asteroid a : asteroids) {
            a.setKeepInsideWindow(true);
        }
        textHandler = new TextHandler();
        buttons = new HashMap<>();
        createMenu();
    }

    private void createMenu() {
        texts = new ArrayList<>();
        
        gameText = new Text("Meteoroids", Game.WIDTH/2-200.0f, Game.HEIGHT/10);
        texts.add(gameText);
                
        playText = new Text("Play", Game.WIDTH/2-150.0f, Game.HEIGHT/10+300.0f);
        playText.toggleActive();
        active = MenuButton.PLAY;
        buttons.put(MenuButton.PLAY, playText);
        texts.add(playText);
                
        highScoreText = new Text("High Scores", Game.WIDTH/2-150.0f, Game.HEIGHT/10+400.0f);
        buttons.put(MenuButton.HIGH_SCORES, highScoreText);
        texts.add(highScoreText);
        
        exitText = new Text("Exit", Game.WIDTH/2-150.0f, Game.HEIGHT/10+500.0f);
        buttons.put(MenuButton.EXIT, exitText);
        texts.add(exitText);
        
        for(Text text : texts) {
            textHandler.addText(text);
        }
        
    }

    @Override
    public void exit() {
        super.exit();
        for(Text text : texts) {
            textHandler.removeText(text);
        }
    }
    
    @Override
    public void update(float deltaTime) {
        controller.getGraphicsController().setFollowPlayerCamera(false, null);
        objectController.update(deltaTime);
        controller.getGraphicsController().draw(objectController.getDrawables());
        textHandler.draw();
    }
    
    /**
     * Move up in menu.
     * 
     */
    public void moveUp() {
        buttons.get(active).toggleActive();
        int buttonNumber = active.ordinal();
        buttonNumber--;
        
        if(buttonNumber < 0) {
            buttonNumber = MenuButton.values().length-1;
            active = MenuButton.values()[buttonNumber];
        }
        else {
            active = MenuButton.values()[buttonNumber];
        }
        buttons.get(active).toggleActive();
    }
    
    /**
     * Move down in menu.
     * 
     */
    public void moveDown() {
        
        buttons.get(active).toggleActive();
        int buttonNumber = active.ordinal();
        buttonNumber++;
        
        if(buttonNumber >= MenuButton.values().length) {
            buttonNumber = 0;
            active = MenuButton.values()[buttonNumber];
        }
        else {
            active = MenuButton.values()[buttonNumber];
        }
        buttons.get(active).toggleActive();        
    }    
    
    public GameObjectController getGameObjectController() {
        return objectController;
    }
    
    /**
     * Get current active GameState in the menu.
     * 
     * @return GameState
     */
    public GameState getActiveButtonState() {
        switch (active) {
            case EXIT:
                return GameState.EXIT;
            case HIGH_SCORES:
                return GameState.HIGH_SCORES;
            case PLAY:
                return GameState.PLAY;
            default:
                return GameState.EXIT;
        }
    }
    
    public enum MenuButton {
        PLAY,
        HIGH_SCORES,
        EXIT
    }

}
