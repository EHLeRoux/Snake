package snake;

import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 * Here is the main class, where the Board class is created. 
 */
public class Main {
	
	public Main () { 
		Board mainBoard = new Board();
		JFrame mainFrame = new JFrame(); 
		mainFrame.add(mainBoard); 
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setTitle("Snake");
	}

	
	public static void main(String[] args) { 
		new Main();
	}
}
