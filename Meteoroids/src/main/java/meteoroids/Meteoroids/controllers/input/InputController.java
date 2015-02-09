package meteoroids.Meteoroids.controllers.input;

import org.lwjgl.input.Keyboard;

import meteoroids.Meteoroids.controllers.Controller;
import meteoroids.Meteoroids.controllers.gamestates.GameState;
import meteoroids.Meteoroids.controllers.gamestates.GameStateController;
import meteoroids.Meteoroids.controllers.gamestates.GameStateGameOver;
import meteoroids.Meteoroids.controllers.gamestates.GameStateGotHighScore;
import meteoroids.Meteoroids.controllers.gamestates.GameStateHighScores;
import meteoroids.Meteoroids.controllers.gamestates.GameStateMachine;
import meteoroids.Meteoroids.controllers.gamestates.GameStatePause;
import meteoroids.Meteoroids.controllers.gamestates.GameStatePlay;
import meteoroids.Meteoroids.controllers.gamestates.GameStateMainMenu;

/**
 * Handles the game input in all different game states.
 * 
 * @author vpyyhtia
 *
 */
public class InputController implements Controller {

    private GameState state;
    private GameStateMachine stateMachine;
    private GameStateController gameStateController;
    
    public InputController(GameStateController gameStateController) {
        this.gameStateController = gameStateController;
        state = null;
        stateMachine = null;
    }
    
    @Override
    public void update(float deltaTime) {
        if(state != null) {
            pollInputs(deltaTime);
        }
    }

    private void pollInputs(float deltaTime) {
        switch (state) {
            case GAME_OVER:
                pollGameOverInputs(deltaTime);
                break;
            case PAUSE:
                pollPauseInputs(deltaTime);
                break;
            case HIGH_SCORES:
                pollHighScoresInputs(deltaTime);
                break;
            case GOT_HIGH_SCORE:
                pollGotHighScoreInputs(deltaTime);
                break;
            case MAIN_MENU:
                pollMenuInputs(deltaTime);
                break;
            case PLAY:
                pollPlayInputs(deltaTime);
                break;
            default:
                break;
        }
    }

    /**
     * GameState.GOT_HIGH_SCORE inputs
     * 
     * @param deltaTime
     */
    private void pollGotHighScoreInputs(float deltaTime) {
        while(Keyboard.next()) {
            int key = Keyboard.getEventKey();
            if((key >= 16 && key <= 25) || (key >= 30 && key <= 38) || (key >= 44 && key <= 50)) {
                if(!Keyboard.getEventKeyState()) {
                    GameStateGotHighScore.name.append(Keyboard.getKeyName(Keyboard.getEventKey()));
                    if(GameStateGotHighScore.name.length() > 8) {
                        GameStateGotHighScore.name = new StringBuilder(GameStateGotHighScore.name.substring(0, 8));
                    }
                }
            }
            if(Keyboard.getEventKey() == Keyboard.KEY_BACK) {
                if(!Keyboard.getEventKeyState()) {    
                    if(GameStateGotHighScore.name.length() > 0) {
                        GameStateGotHighScore.name.delete(GameStateGotHighScore.name.length()-1, GameStateGotHighScore.name.length());
                    }
                }
            }            
            if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE ||
               Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
                if(!Keyboard.getEventKeyState()) {
                    GameStateGotHighScore tmpState = (GameStateGotHighScore)stateMachine;
                    tmpState.save();
                    tmpState.exit();
                }
            }
        }
    }

    /**
     * GameState.HIGH_SCORES inputs
     * 
     * @param deltaTime
     */
    private void pollHighScoresInputs(float deltaTime) {
        while(Keyboard.next()) {
            if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE ||
               Keyboard.getEventKey() == Keyboard.KEY_RETURN ||
               Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
                if(!Keyboard.getEventKeyState()) {
                    ((GameStateHighScores)stateMachine).exit();   
                }
            }
        }
    }

    /**
     * GameState.MAIN_MENU inputs
     * 
     * @param deltaTime
     */
    private void pollMenuInputs(float deltaTime) {
        while(Keyboard.next()) {
            if(Keyboard.getEventKey() == Keyboard.KEY_UP) {
                if(!Keyboard.getEventKeyState()) {
                    ((GameStateMainMenu)stateMachine).moveUp();
                }
            }
            if(Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
                if(!Keyboard.getEventKeyState()) {
                    ((GameStateMainMenu)stateMachine).moveDown();
                }
            }
            if(Keyboard.getEventKey() == Keyboard.KEY_RETURN || Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
                if(!Keyboard.getEventKeyState()) {
                    switch (((GameStateMainMenu)stateMachine).getActiveButtonState()) {
                        case EXIT:
                            ((GameStateMainMenu)stateMachine).exit();
                            break;
                        case GAME_OVER:
                            break;
                        case HIGH_SCORES:
                            gameStateController.addGameState(new GameStateHighScores(gameStateController,
                                    ((GameStateMainMenu)stateMachine).getGameObjectController(), false));
                            break;
                        case MAIN_MENU:
                            break;
                        case PLAY:
                            gameStateController.addGameState(new GameStatePlay(gameStateController));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    /**
     * GameState.GAME_OVER inputs
     * 
     * @param deltaTime
     */
    private void pollGameOverInputs(float deltaTime) {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            ((GameStateGameOver)stateMachine).exit();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_N)) {
            ((GameStateGameOver)stateMachine).exit();
            gameStateController.addGameState(new GameStatePlay(gameStateController));
        }
    }
    
    /**
     * GameState.PAUSE inputs
     * 
     * @param deltaTime
     */
    private void pollPauseInputs(float deltaTime) {
        while(Keyboard.next()) {
            if(Keyboard.getEventKey() == Keyboard.KEY_SPACE || Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
                ((GameStatePause)stateMachine).exit();
            }
        }
    }

    /**
     * GameState.PLAY inputs
     * 
     * @param deltaTime
     */
    private void pollPlayInputs(float deltaTime) {
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            ((GameStatePlay)stateMachine).getShip().rotate(0.3f, deltaTime);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            ((GameStatePlay)stateMachine).getShip().rotate(-0.3f, deltaTime);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            ((GameStatePlay)stateMachine).getShip().accelerate(0.0015f, deltaTime);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            // ((GameStatePlay)stateMachine).getShip().accelerate(-0.0015f, deltaTime);
            ((GameStatePlay)stateMachine).getShip().slowDown(0.003f, deltaTime);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            ((GameStatePlay)stateMachine).getObjectController().fire(((GameStatePlay)stateMachine).getShip());
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            ((GameStatePlay)stateMachine).exit();
        }
        while(Keyboard.next()) {
            if(Keyboard.getEventKey() == Keyboard.KEY_LCONTROL) {
                if(!Keyboard.getEventKeyState()) {
                    ((GameStatePlay)stateMachine).getObjectController().changeWeapon(((GameStatePlay)stateMachine).getShip());
                }
            }
            if(Keyboard.getEventKey() == Keyboard.KEY_P) {
                if(!Keyboard.getEventKeyState()) {
                    gameStateController.addGameState(new GameStatePause(gameStateController, ((GameStatePlay)stateMachine).getObjectController()));
                }
            }
        }
    }

    /**
     * Bind current state to InputController.
     * 
     * @param gameStateMachine
     */
    public void bindState(GameStateMachine gameStateMachine) {
        stateMachine = gameStateMachine;
        if(stateMachine != null) {
            state = stateMachine.getGameState();
        }
        else {
            state = null;
        }
    }
}
