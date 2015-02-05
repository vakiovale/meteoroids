package meteoroids.Meteoroids.controllers.gamestates;

import java.util.ArrayDeque;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.controllers.graphics.GraphicsController;
import meteoroids.Meteoroids.controllers.resources.TextHandler;

/**
 * Handles the controlling of game states.
 * 
 * @author vpyyhtia
 *
 */
public class GameStateController implements Controller {

    private GameStateMachine currentGameState;
    private ArrayDeque<GameStateMachine> gameStates;
    private GraphicsController graphicsController;
    private TextHandler textHandler;
    
    public GameStateController(GraphicsController graphicsController) {
        this.graphicsController = graphicsController;
        this.textHandler = new TextHandler();
        currentGameState = null;
        gameStates = new ArrayDeque<>();
    }
    
    @Override
    public void update(float deltaTime) {
        if(currentGameState != null) {
            if(currentGameState.isFinished()) {
                if(!gameStates.isEmpty()) {
                    currentGameState = gameStates.pop();
                }
                else {
                    currentGameState = null;
                }
            }
            else {
                currentGameState.update(deltaTime);
            }
        }
    }
    
    public void changeGameState(GameStateMachine gameState) {
        currentGameState = gameState;
    }
    
    public void addGameState(GameStateMachine gameState) {
        gameStates.push(gameState);
        currentGameState = gameState;
    }
    
    public void removeGameState() {
        if(!gameStates.isEmpty()) {
            gameStates.poll();
            currentGameState = gameStates.peek();
        }
    }
    
    public GraphicsController getGraphicsController() {
        return graphicsController;
    }
    
    public GameState getGameState() {
        return currentGameState.getGameState();
    }
    
    public TextHandler getTextHandler() {
        return textHandler;
    }

}
