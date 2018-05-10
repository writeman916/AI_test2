import java.awt.Graphics;
import java.awt.Point;

public abstract class  Piece {
	private int x; //locations denoted are top-left hand corner of squares.
	private int y;
	private Point squareOn; //keeps track of what square the piece is on, in terms of squares on the board
	private boolean color; //true:black, false:white
	
	private boolean selected; //keeps track of if  a piece has been selected or not
	
	public Piece(int x, int y, boolean color ){ //note that false is white and true is black for the color
		squareOn = new Point(x,y);
		this.x = x*62;
		this.y = y*62;
		this.selected = false;
		this.color = color;

	}
	
	public void setSelected(boolean selected){
		this.selected =  selected;
	}
	public boolean isSelected(){
		return selected;
	}
	public Point getLocation(){
		return new Point(x,y);
	}
	
	public boolean getColor(){
		return color;
	}
	public Point getSquareOn(){
		return squareOn;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void translate(int dx, int dy){
		x += dx;
		y += dy;
	}
	public boolean setLocation(int row, int col){
		x = row*62;
		y  = col*62;
		squareOn.setLocation(row, col);
		return true;
	}
	// Point2D
	public boolean contains(Point p){
		return x<= p.getX() && (x + 62) >= p.getX() && y<= p.getY() && (y+62) >= p.getY();
	}
	public abstract String getType();
	public abstract void draw(Graphics g);
	public abstract boolean checkLegalMove(Point p, Board b);
}