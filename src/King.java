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
}
