package meteoroids.Meteoroids.controllers.gamestates;

import java.util.ArrayDeque;

import meteoroids.Meteoroids.Game;
import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.controllers.graphics.GraphicsController;
import meteoroids.Meteoroids.controllers.input.InputController;

/**
 * Handles the controlling of game states. Controller for different GameStateMachines.
 * Changes, removes and updates different GameStateMachines. At the start of the game,
 * GameStateController usually has GameStateMainMenu as its first (and only) GameStateMachine.
 * 
 * @author vpyyhtia
 *
 */
public class GameStateController implements Controller {

    private GameStateMachine currentGameState;
    private ArrayDeque<GameStateMachine> gameStates;
    private GraphicsController graphicsController;
    private InputController inputController;
    
    /**
     * Constructor for GameStateController. Creates an InputController
     * for polling user's inputs.
     * 
     * @param graphicsController to be binded to the GameStateController
     */
    public GameStateController(GraphicsController graphicsController) {
        this.graphicsController = graphicsController;
        this.inputController = new InputController(this);
        currentGameState = null;
        gameStates = new ArrayDeque<>();        
    }
    
    @Override
    public void update(float deltaTime) {        
        if(currentGameState != null) {
            if(currentGameState.isFinished()) {
                gameStates.poll();
                if(!gameStates.isEmpty()) {
                    currentGameState = gameStates.peek();
                    inputController.bindState(currentGameState);
                }
                else {
                    currentGameState = null;
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
    
    /**
     * Changes to the specific GameStateMachine.
     * 
     * @param gameStateMachine
     */
    public void changeGameState(GameStateMachine gameStateMachine) {
        currentGameState = gameStateMachine;
        inputController.bindState(currentGameState);
    }
    
    /**
     * Changes to the specific GameStateMachine and adds it to the top of
     * GameStateMachines.
     * 
     * @param gameStateMachine
     */
    public void addGameState(GameStateMachine gameState) {
        gameStates.push(gameState);
        currentGameState = gameState;
        inputController.bindState(currentGameState);
    }
    
    /**
     * Remove current GameState and change to the next one on the deck.
     * 
     */
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
    
    /**
     * Get current GameState
     * 
     * @return GameState
     */
    public GameState getGameState() {
        return currentGameState.getGameState();
    }
}
