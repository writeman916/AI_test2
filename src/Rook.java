import java.awt.Graphics;
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

}
