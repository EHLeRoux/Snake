package snake;
/**
 * Enum to keep track of the direction of the snake
 */
public enum Direction {
	Left, 
	Right,
	Up, 
	Down;
	
	private Direction currentDirection;

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(Direction currentDirection) {
		this.currentDirection = currentDirection;
	} 

}
