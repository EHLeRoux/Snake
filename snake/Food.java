package snake;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Here we create an object for the food where the apple will be created
 * Class is identical to the snake class, as the food and snake has identical properties
 */
public class Food {
	
	private int xCoordinates, yCoordinates, width, height; 
	
	public Food(int xCoordinates, int yCoordinates, int tileSize) { 
		this.xCoordinates = xCoordinates; 
		this.yCoordinates = yCoordinates;
		//Setting the tilesize (20) equal to the height and width of the food (one block)
		height = tileSize; 
		width = tileSize;
	}
	
	public void ticks() { 
		
	}
	
	public void draw(Graphics g) { 
		g.setColor(Color.GREEN);
		g.fillRect(xCoordinates * width, yCoordinates * height, width, height);
		
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
