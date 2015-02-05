package meteoroids.Meteoroids.controllers.gamestates;

import java.util.ArrayDeque;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.controllers.graphics.GraphicsController;
import meteoroids.Meteoroids.controllers.input.InputController;
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
    private InputController inputController;
    
    
    public GameStateController(GraphicsController graphicsController) {
        this.graphicsController = graphicsController;
        this.inputController = new InputController(this);
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
                    inputController.bindState(gameStates.peek());
                }
                else {
                    currentGameState = null;
                    inputController.bindState(gameStates.peek());
                    Game.gameOver();
                }
            }
            else {
                currentGameState.update(deltaTime);
            }
            inputController.update(deltaTime);
        }
        else {
            Game.gameOver();
        }
    }
    
    public void changeGameState(GameStateMachine gameState) {
        currentGameState = gameState;
        inputController.bindState(currentGameState);
    }
    
    public void addGameState(GameStateMachine gameState) {
        gameStates.push(gameState);
        currentGameState = gameState;
        inputController.bindState(currentGameState);
    }
    
    public void removeGameState() {
        if(!gameStates.isEmpty()) {
            gameStates.poll();
            currentGameState = gameStates.peek();
            inputController.bindState(currentGameState);
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
