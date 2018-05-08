import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JTextArea;

public class View extends JComponent implements MouseListener{
	
	private final Board board; 
	private JTextArea textBox;
	private Point mouseP;
	
	public View(Board board)
	{
		this.addMouseListener(this);
		this.board = board;
		
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		boolean isBlack = false;
		
		for(int i = 0; i<8; i++){
			isBlack = !(isBlack);
			for(int j = 0; j<8; j++){
				isBlack = !(isBlack);
				Rectangle rect = new Rectangle(i*62,j*62,62,62);
				if(isBlack){	
					g2.setColor(Color.darkGray);
				}else{
					g2.setColor(Color.white);
				}
				g2.fill(rect);
				g2.setColor(Color.red);
				g2.drawRect(0, 0, 496, 496);
				
			}
		}
		
		
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				if(board.hasPiece(i, j)){ //perform draw action if piece exists on board
					board.getSquare(i, j).draw(g2);
				}
			}
		}
		//draws selected pieces on top to ensure they are on the top layer
		for(int i=0; i<8; i++){
			for(int j =0; j<8; j++){
				if(board.hasPiece(i, j)){
					if(board.getSquare(i, j).isSelected()){
						board.getSquare(i,j).draw(g2);
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		mouseP = e.getPoint();
		System.out.println(board.getSquare(mouseP.x/62, mouseP.y/62).getType() +"  "+board.getSquare(mouseP.x/62, mouseP.y/62).getColor() );
		board.getSquare(mouseP.x/62, mouseP.y/62).setSelected(true);
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Point lastMousePoint = mouseP; //mousePoint used to keep track of where the piece should be drawn
		mouseP = e.getPoint();
		for(int i = 0; i<8; i++){ //nested for loops iterate through the whole board
			for(int j=0; j<8; j++){
				if(View.this.board.hasPiece(i, j)){
					if(View.this.board.getSquare(i,j).isSelected()){
						double dx = mouseP.getX() - lastMousePoint.getX(); //calculate how much to change piece position
						double dy = mouseP.getY() - lastMousePoint.getY();
						System.out.println(dx);
						System.out.println(dy);
						View.this.board.getSquare(i, j).translate((int)dx,(int)dy); //translate piece
					}
				}
			}
		}
		repaint(); //repaints the View everytime mouse is dragged
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	} 

}
