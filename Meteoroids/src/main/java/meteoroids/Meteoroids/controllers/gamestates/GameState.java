package meteoroids.Meteoroids.controllers.gamestates;

/**
 * Game states. GameStates represents the state of the GameStateMachine.
 * All the different states in the game should have own GameState.
 * 
 * @author vpyyhtia
 *
 */
public enum GameState {
    PLAY,
    MAIN_MENU,
    PAUSE,
    HIGH_SCORES,
    GOT_HIGH_SCORE,
    GAME_OVER,
    EXIT
}
