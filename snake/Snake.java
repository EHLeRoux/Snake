package snake;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Here we create the snake object where the snake body will be created and
 * stored inside an arraylist for reference
 */
public class Snake {

	private int xCoordinates, yCoordinates, width, height;

	public Snake(int xCoordinates, int yCoordinates, int tileSize) {
		this.xCoordinates = xCoordinates;
		this.yCoordinates = yCoordinates;
		//Setting the tilesize (20) equal to the height and width of the snake (one block)
		height = tileSize;
		width = tileSize;
	}


	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(xCoordinates * width, yCoordinates * height, width, height);
		//System.out.println(xCoordinates * width);
		//System.out.println(height*width);

	}

	public int getxCoordinates() {
		return xCoordinates;
	}

	public void setxCoordinates(int xCoordinates) {
		this.xCoordinates = xCoordinates;
	}

	public int getyCoordinates() {
		return yCoordinates;
	}

	public void setyCoordinates(int yCoordinates) {
		this.yCoordinates = yCoordinates;
	}

}
