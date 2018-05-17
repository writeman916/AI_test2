import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Chessboard {
	
	

	public static void main(String[] args) {
		
		
		Board b = new Board();
		JPanel textPanel = new JPanel(new BorderLayout());
		
		JLabel turnLabel = new JLabel("Nam's Turn");
		Game g = new Game("Nam","Minh", turnLabel);
		
		View w = new View(b,g);
		JFrame f = new JFrame();
		
		textPanel.add(turnLabel, BorderLayout.CENTER);
		f.add(w);
		f.add(textPanel, BorderLayout.EAST);
		f.pack();

		f.setSize(700,700);
		f.setVisible(true);
	
	}

	
}
