import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rook extends Piece{
	 private BufferedImage img;
		
		
		public Rook(int x, int y, boolean color){
			super(x,y, color);
			img = null;
			if(super.getColor() == false){
				try {
				    img = ImageIO.read(new File("whiterook.png"));
				} catch (IOException e) {
					System.out.println("file not found");
				}
			}else{
				try{
					img = ImageIO.read(new File("blackrook.png"));
				}catch(IOException e){
					System.out.println("filenotfound");
				}
			}
		}
		

		public void draw(Graphics g){
			g.drawImage(img, super.getX(), super.getY(), null);
		}
		
	
		public String getType(){
			return "Rook";
		}


		public boolean checkLegalMove(Point p, Board b){
			
			int oldX = (int)super.getSquareOn().getX();
			int oldY = (int)super.getSquareOn().getY();
			
			
			int newX = (int)p.getX()/62; 
			int newY = (int)p.getY()/62;
			
	
			if(b.hasPiece(newX, newY)){
				if(b.getSquare(newX, newY).getColor() == super.getColor()){
					return false;
				}
			}
			
			
			// Rook di doc
			if((oldX == newX) && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)){
				if(newY > oldY){ 
					for(int i = 1; i< newY-oldY; i++){
						if(b.hasPiece(oldX, oldY + i)){   // neu co bat ki quan co nao tren duong di thi ko dc di
							return false;
						}
					}
					return true;
				}
				if(newY < oldY){
					for(int i= 1; i<oldY - newY; i++){ 
						if(b.hasPiece(oldX, newY + i)){
							return false;
						}
					}
					return true;
				}
				
				if(newY == oldY){
					return true;
				}
				return true;
			}
			
			// Rook di ngang
			if((oldY == newY) && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)){
				if(newX > oldX){ 
					for(int i = 1; i<newX-oldX; i++){ 
						if(b.hasPiece(oldX + i, oldY)){
							return false;
						}
					}
					return true;
				}
				
				if(newX < oldX){ //checks if there are pieces in teh way if the rook moves left
					for(int i = 1; i<oldX-newX; i++){ //iterates through board
						if(b.hasPiece(newX + i, oldY)){
							return false;
						}
					}
					return true;
				}
				if(newX == oldX){
					return true;
				}
			}
			return false;
		}

}
