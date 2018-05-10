import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pawn extends Piece {

	 private BufferedImage img;
		
		
		public Pawn(int x, int y, boolean color){
			super(x,y, color);
			img = null;
			if(super.getColor() == false){
				try {
				    img = ImageIO.read(new File("whitepawn.png"));
				} catch (IOException e) {
					System.out.println("file not found");
				}
			}else{
				try{
					img = ImageIO.read(new File("blackpawn.png"));
				}catch(IOException e){
					System.out.println("filenotfound");
				}
			}
		}
		

		public void draw(Graphics g){
			g.drawImage(img, super.getX(), super.getY(), null);
		}
		
	
		public String getType(){
			return "Pawn";
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
			
			
			// white pawn
			if(super.getColor() == false){ 
				if(oldY == 6){  // neu o vi tri ban dau thi di 1 or 2 buoc
					if(Math.abs(newX - oldX) == 1 && newY == oldY -1 && b.hasPiece(newX, newY)){ // an quan khac
						return true;
					}else if(newX == oldX && newY == oldY -1 && !b.hasPiece(newX, newY)){
						return true;
					}else if(newX == oldX && newY == oldY - 2 && !b.hasPiece(newX, newY)){
						return true;
					}
					return false;
				}else{ // nguoc lai di 1 buoc
					if(Math.abs(newX - oldX) == 1 && newY == oldY -1 && b.hasPiece(newX, newY)){  // an quan khac
						return true;
					}else if(newX == oldX && newY == oldY -1 && !b.hasPiece(newX, newY)){
						return true;
					}
					return false;
				}
				
			// black pawn	
			}else{ 
				if(oldY == 1){
					if(Math.abs(newX - oldX) == 1 && newY == oldY +1 && b.hasPiece(newX, newY)){
						
						return true;
					}else if(newX == oldX && newY == oldY +1 && !b.hasPiece(newX, newY)){
			

						return true;
					}else if(newX == oldX && newY == oldY + 2 && !b.hasPiece(newX, newY)){

						return true;
					}
					return false;
				}else{
					if(Math.abs(newX - oldX) == 1 && newY == oldY +1 && b.hasPiece(newX, newY)){

						return true;
					}else if(newX == oldX && newY == oldY +1 && !b.hasPiece(newX, newY)){

						return true;
					}
					return false;
				}
			}
		}
}
