 import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Knight extends Piece {
	 private BufferedImage img;
		
		
		public Knight(int x, int y, boolean color){
			super(x,y, color);
			img = null;
			if(super.getColor() == false){
				try {
				    img = ImageIO.read(new File("whiteknight.png"));
				} catch (IOException e) {
					System.out.println("file not found");
				}
			}else{
				try{
					img = ImageIO.read(new File("darkknight.png"));
				}catch(IOException e){
					System.out.println("filenotfound");
				}
			}
		}
		
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
			
			// Knight tien 2 ngang 1 => |x2 - x1| = 2, |y2 - y1| =1
			if(Math.abs(newX - oldX) == 2 && Math.abs(newY - oldY) == 1 && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)){
				System.out.println("Legal move: " + getType()  + " from: (" + oldX + "," + oldY + ") to (" + newX + "," + newY + ")");
				return true;
			// Knight tien 1 ngang 2 => |x2 - x1| = 1, |y2 - y1| =2
			}else if(Math.abs(newX -oldX) == 1 && Math.abs(newY - oldY) == 2 && (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7)){
				System.out.println("Legal move: " + getType()  + " from: (" + oldX + "," + oldY + ") to (" + newX + "," + newY + ")");
				return true;
			}else{
				return false;
			}
		}
		public void draw(Graphics g){
			g.drawImage(img, super.getX(), super.getY(), null);
		}
		
	
		public String getType(){
			return "Knight";
		}

}
