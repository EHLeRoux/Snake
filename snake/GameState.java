package snake;
/**
 * Enum to keep track of the game state
 */
public enum GameState {
	Paused, 
	GameOver,
	Running,
	Replay,
	StartScreen;
	
	private GameState currentState;

	public GameState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(GameState currentState) {
		this.currentState = currentState;
	}
}
