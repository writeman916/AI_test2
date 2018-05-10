import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class King extends Piece{
	private BufferedImage img; //stores image object
	
	public King(int x, int y, boolean color){
		super(x,y,color);
		img = null;
		if(super.getColor() == false){
			try {
			    img = ImageIO.read(new File("whiteking.png"));
			    System.out.println("geted");
			} catch (IOException e) {
				System.out.println("file not found");
			}
		}else{
			try{
				img = ImageIO.read(new File("blackking.png"));
			    System.out.println("geted");
			}catch(IOException e){
				System.out.println("filenotfound");
			}
		}
	}

	
	
	
	public void draw(Graphics g){
		g.drawImage(img, super.getX(), super.getY(), null);
	}
	@Override
	public String getType() {
		return "King";
	}




	@Override
	public boolean checkLegalMove(Point p, Board b) {
		int oldX = (int)super.getSquareOn().getX();
		int oldY = (int)super.getSquareOn().getY();
		
		
		int newX = (int)p.getX()/62; 
		int newY = (int)p.getY()/62;
		

		if(b.hasPiece(newX, newY)){
			if(b.getSquare(newX, newY).getColor() == super.getColor()){
				return false;
			}
		}
		
		
		if(Math.abs(newX - oldX) <= 1 && Math.abs(newY - oldY) <=1 && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)){
			return true;
		}
		/*
		 * Allow for castling
		 */	
//		if(super.getColor() == false){ //case that piece is white
//			if(b.hasPiece(7, 7)){
//				if(b.getSquare(7,7).getType().equals("Rook")){ //checks if there is rook in correct position
//					if(oldX == 4 && oldY == 7 && newX == 6 && newY ==7){
//						if(!b.hasPiece(5, 7) && !b.hasPiece(6,7)){ //checks if proper castling rules followed
//							return true;
//						}
//					}
//				}
//			}
//			//applies same reasoning to a black king, hardcoded positions for rook and king used
//		}else if(super.getColor() == true){ //case that piece is black
//			if(b.hasPiece(7, 0)){
//				if(b.getSquare(7, 0).getType().equals("Rook")){
//					if(oldX == 4 && oldY == 0 && newX == 6 && newY ==0){
//						if(!b.hasPiece(5, 0) && !b.hasPiece(6,0)){
//							return true;
//						}
//					}
//				}
//			}
//		}
		return false;
	}
}
