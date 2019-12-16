package snake;

import java.awt.Color;
import java.awt.Graphics;

public class Obstacles {
	
	private int xCoordinates, yCoordinates, width, height; 
	
	public Obstacles(int xCoordinates, int yCoordinates, int tileSize) { 
		this.xCoordinates = xCoordinates; 
		this.yCoordinates = yCoordinates;
		height = tileSize; 
		width = tileSize;
	}
	
	public void draw(Graphics g) { 
		g.setColor(Color.RED);
		g.clearRect(xCoordinates * width, yCoordinates * height, width, height);
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
