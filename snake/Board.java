package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Here is the main Board class where the board will be printed, game will start and the snake, along with 
 * the food will be created. A score tracker variable will keep track of the current score 
 */

public class Board extends JPanel implements Runnable, KeyListener {

	/**
	 * Automatic serialVersion generated with the constants that will be used
	 * throughout the program
	 */
	private static final long serialVersionUID = 1L;
	private int WIDTH = 700;
	private int HEIGHT = 700;
	private Thread thread;
	private int xCoordinate = 15;
	private int yCoordinate = 15;
	private int size = 4;
	private int ticksCount = 0;
	private int score = -1;

	/*
	 * Three arraylists to keep track of the snake, food and obstacles.
	 */
	private Snake body;
	private ArrayList<Snake> snake;
	private Food apple;
	private ArrayList<Food> food;
	Random randomNumber;

	/*
	 * Two enums used - to keep track of gamestate and direction of snake
	 */
	GameState currentState;
	Direction currentDirection;

	public Board() {
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		// Important to declare the Arraylist in the constructor
		snake = new ArrayList<Snake>();
		food = new ArrayList<Food>();
		// Declaring the enums and their states
		currentState = GameState.Running;
		currentDirection = Direction.Right;
		// Starting the thread
		start();
	}

	/**
	 * Setting the Gamestate and the Direction of the snake Also, creating and
	 * starting the thread
	 */
	public void start() {
		currentState.setCurrentState(GameState.Running);
		currentDirection.setCurrentDirection(Direction.Right);
		thread = new Thread(this);
		thread.start();
	}
	/**
	 * Here the thread will stop and the gamestate will be set to GameOver
	 */
	public void stop() {
		currentState.setCurrentState(GameState.GameOver);
		try {
			// thread.join will only be called when the thread stops running and returns
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void ticks() {
		/**
		 * Generating random positions for the food and for the obstacles
		 */
		randomNumber = new Random();
		int foodXCoordinate = randomNumber.nextInt(35);
		int foodYCoordinate = randomNumber.nextInt(35);
		if (food.size() == 0) {
			apple = new Food(foodXCoordinate, foodYCoordinate, 20);
			food.add(apple);
			score++;
		}
		ticksCount++;
		// The integer number here set the speed, the higher the slower the game will be
		if (ticksCount > 600000) {
			/**
			 * Coordinates start at the top left, thats why the y Coordinates increase when
			 * going down and decrease when going up
			 */
			if (currentDirection.getCurrentDirection() == Direction.Left) {
				xCoordinate--;
			}
			if (currentDirection.getCurrentDirection() == Direction.Right) {
				xCoordinate++;
			}
			if (currentDirection.getCurrentDirection() == Direction.Up) {
				yCoordinate--;
			}
			if (currentDirection.getCurrentDirection() == Direction.Down) {
				yCoordinate++;
			}
			ticksCount = 0;

			/**
			 * Creating a new snake bodypart to be added to the snake as a whole
			 */
			body = new Snake(xCoordinate, yCoordinate, 20);
			snake.add(body);

			// This is vital, as it removes the snake bodyparts as it moves along
			if (snake.size() > size) {
				snake.remove(0);
			}
			// Checking for collision on the outskirts of the screen (700 / 20) = 35
			if (body.getxCoordinates() >= 35 || body.getyCoordinates() >= 35 || body.getyCoordinates() < 0
					|| body.getxCoordinates() < 0) {
				currentState.setCurrentState(GameState.GameOver);
				stop();
			}
			// Checking for collision with apple
			if (body.getxCoordinates() == apple.getxCoordinates()
					&& body.getyCoordinates() == apple.getyCoordinates()) {
				food.remove(0);
				size++;
			}
			// Checking for collision with snake itself
			for (int i = 0; i < snake.size(); i++) {
				if (xCoordinate == snake.get(i).getxCoordinates() && yCoordinate == snake.get(i).getyCoordinates()) {
					if (i != snake.size() - 1) {
						currentState.setCurrentState(GameState.GameOver);
					}
				}
			}
		}
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//Drawing of the grid
//		for (int i = 0; i < HEIGHT / 20; i++) {
//			g.drawLine(i * 20, 0, i * 20, HEIGHT);
//
//		}
//
//		for (int i = 0; i < WIDTH / 20; i++) {
//			g.drawLine(0, i * 20, WIDTH, i * 20);
//
//		}

		// Getting the position of the snake body and drawing it out to the screen
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		// Getting the position of the food and drawing it out to the screen
		for (int i = 0; i < food.size(); i++) {
			food.get(i).draw(g);
		}
		g.setColor(Color.YELLOW);
		g.drawString("Score :" + String.valueOf(score), 40, 40);
		// g.drawString("ticksCount" + String.valueOf(ticksCount), 60, 60);
		g.drawString("PAUSE: SPACEBAR", 60, 60);
		if (currentState.getCurrentState() == GameState.Paused) {
			Font myFont = new Font("Courier New", 1, 60);
			g.setFont(myFont);
			g.drawString("GAME PAUSED", 150, 150);
		}
		if (currentState.getCurrentState() == GameState.GameOver) {
			Font myFont = new Font("Courier New", 1, 60);
			g.setFont(myFont);
			g.drawString("GAME OVER", 150, 150);
		}
	}

	@Override
	public void run() {
		while (currentState.getCurrentState() != GameState.GameOver) {
			if (currentState.getCurrentState() != GameState.Paused) {
				ticks();
				repaint();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	/**
	 * Here all the key events will be processed, e.g when the user presses the left
	 * arrow key
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT && currentDirection.getCurrentDirection() != Direction.Right) {
			currentDirection.setCurrentDirection(Direction.Left);
		}
		if (key == KeyEvent.VK_RIGHT && currentDirection.getCurrentDirection() != Direction.Left) {
			currentDirection.setCurrentDirection(Direction.Right);
		}
		if (key == KeyEvent.VK_UP && currentDirection.getCurrentDirection() != Direction.Down) {
			currentDirection.setCurrentDirection(Direction.Up);
		}
		if (key == KeyEvent.VK_DOWN && currentDirection.getCurrentDirection() != Direction.Up) {
			currentDirection.setCurrentDirection(Direction.Down);
		}
		if (key == KeyEvent.VK_SPACE) {
			currentState.setCurrentState(GameState.Paused);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE) {
			currentState.setCurrentState(GameState.Running);
		}
	}
}