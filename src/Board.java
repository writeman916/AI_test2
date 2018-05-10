import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;


public class Board extends JPanel{
private Piece[][] board;
	
	public Board(){
		
		board = new Piece[8][8]; //initializes board
		
		
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				board[i][j] = null;
			}
		}	
		//this adds pieces to the board
				//black pieces
				setSquare(4,0,new King(4,0,true));
				setSquare(0,0,new Rook(0,0,true));
				setSquare(1,0,new Knight(1,0,true));
				setSquare(2,0,new Bishop(2,0,true));
				setSquare(3,0,new Queen(3,0,true));
				setSquare(5,0,new Bishop(5,0,true));
				setSquare(6,0,new Knight(6,0,true));
				setSquare(7,0, new Rook(7,0,true));
				for(int i = 0; i<8; i++){
					setSquare(i,1,new Pawn(i,1,true));
				}
				//white pieces
				setSquare(4,7,new King(4,7,false));
				setSquare(0,7,new Rook(0,7,false));
				setSquare(1,7,new Knight(1,7,false));
				setSquare(2,7,new Bishop(2,7,false));
				setSquare(3,7,new Queen(3,7,false));
				setSquare(5,7, new Bishop(5,7,false));
				setSquare(6,7, new Knight(6,7,false));
				setSquare(7,7,new Rook(7,7,false));
				for(int i=0;i<8;i++){
					setSquare(i,6, new Pawn(i,6,false));
				}
	}
	
	
	
	public Board(Board b){
		board = new Piece[8][8]; //initializes board
		//this for loop interates through board and fills it with null objects
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				board[i][j] = null;
			}
		}
		for(int i = 0; i<8; i++){
			for(int j=0; j<8; j++){
				if(b.hasPiece(i,j)){
					if(b.getSquare(i, j).getColor() == false){ //case that piece is white
						if(b.getSquare(i, j).getType() == "King"){
							board[i][j] = new King(i,j,false);
						}else if(b.getSquare(i,j).getType() == "Queen"){
							board[i][j] = new Queen(i,j,false);
						}else if(b.getSquare(i,j).getType() == "Rook"){
							board[i][j] = new Rook(i,j,false);
						}else if(b.getSquare(i,j).getType() == "Bishop"){
							board[i][j] = new Bishop(i,j,false);
						}else if(b.getSquare(i,j).getType() == "Knight"){
							board[i][j] = new Knight(i,j,false);
						}else if(b.getSquare(i, j).getType() == "Pawn"){
							board[i][j] = new Pawn(i,j,false);
						}
					}else{ //case that piece is black
						if(b.getSquare(i, j).getType() == "King"){
							board[i][j] = new King(i,j,true);
						}else if(b.getSquare(i,j).getType() == "Queen"){
							board[i][j] = new Queen(i,j,true);
						}else if(b.getSquare(i,j).getType() == "Rook"){
							board[i][j] = new Rook(i,j,true);
						}else if(b.getSquare(i,j).getType() == "Bishop"){
							board[i][j] = new Bishop(i,j,true);
						}else if(b.getSquare(i,j).getType() == "Knight"){
							board[i][j] = new Knight(i,j,true);
						}else if(b.getSquare(i, j).getType() == "Pawn"){
							board[i][j] = new Pawn(i,j,true);
						}
					}
				}
			}
		}
	}
	
	
	
	
	public Piece getSquare(int row, int col){
		return board[row][col];
	}
	
	public void setSquare(int row, int col, Piece piece){
		board[row][col] = piece;
	}
	
	
	public void clearSquare(int row, int col){
		board[row][col] = null;
	}
	
	// Check o do co quan nao ko 
	public boolean hasPiece(int row, int col){
		if(getSquare(row,col) != null){
			return true;
		}else{
			return false;
		}
	}
	
	
    public void paintComponent(Graphics g){
        
    }   
}
