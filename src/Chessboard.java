import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class Chessboard {
	
	

	public static void main(String[] args) {
		
		
		Board b = new Board();
		View w = new View(b);
		JFrame f = new JFrame();

		f.add(w);
		f.setSize(700,700);
		f.setVisible(true);
	
	}

	
}
