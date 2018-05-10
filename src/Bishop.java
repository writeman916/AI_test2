import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bishop extends Piece {

	 private BufferedImage img;
		
		
		public Bishop(int x, int y, boolean color){
			super(x,y, color);
			img = null;
			if(super.getColor() == false){
				try {
				    img = ImageIO.read(new File("whitebishop.png"));
				} catch (IOException e) {
					System.out.println("file not found");
				}
			}else{
				try{
					img = ImageIO.read(new File("blackbishop.png"));
				}catch(IOException e){
					System.out.println("filenotfound");
				}
			}
		}
		

		public void draw(Graphics g){
			g.drawImage(img, super.getX(), super.getY(), null);
		}
		
	
		public String getType(){
			return "Bishop";
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
			
			// di cheo |x1 - x2| = |y1 - y2|
			if(Math.abs(newX - oldX) == Math.abs(newY - oldY) && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7))
			{ 
				//di cheo xuong duoi ben phai
				if(newX - oldX > 0 && newY - oldY > 0){ 
					for(int i= 1; i< newX-oldX; i++){   
						if(b.hasPiece(oldX + i,oldY + i)){
							return false;
						}
					}
					return true;
				}
				// di len ben trai
				if(newX - oldX < 0 && newY - oldY < 0){ 
					for(int i = 1; i< oldX - newX; i++){
						if(b.hasPiece(newX + i, newY + i)){
							return false;
						}
					}
					return true;
				}
				// di len ben phai
				if(newX - oldX > 0 && newY - oldY < 0){
					for(int i = 1; i< newX - oldX; i++){
						if(b.hasPiece(oldX + i, oldY - i)){
							return false;
						}
					}
					return true;
				}
				// di xuong ben trai
				if(newX - oldX < 0 && newY - oldY > 0){
					for(int i = 1; i< Math.abs(newX - oldX); i++){
						if(b.hasPiece(oldX -i, oldY + i)){
							return false;
						}
					}
					return true;
				}
				return true;
			}
			return false;
		}
}
