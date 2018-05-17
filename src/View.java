import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JTextArea;

public class View extends JComponent implements MouseListener, MouseMotionListener {

	private final Board board;
	private final String[] columns = { "A", "B", "C", "D", "E", "F", "G", "H" };
	private Game game;
	private JTextArea textBox;
	private Point mouseP;
	private String text;

	public View(Board board, Game game) {
		this.game = game;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.board = board;

	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		boolean isBlack = false;

		for (int i = 0; i < 8; i++) {
			isBlack = !(isBlack);
			for (int j = 0; j < 8; j++) {
				isBlack = !(isBlack);
				Rectangle rect = new Rectangle(i * 62, j * 62, 62, 62);
				if (isBlack) {
					g2.setColor(Color.darkGray);
				} else {
					g2.setColor(Color.white);
				}
				g2.fill(rect);
				g2.setColor(Color.red);
				g2.drawRect(0, 0, 496, 496);

			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.hasPiece(i, j)) { // perform draw action if piece exists on board
					board.getSquare(i, j).draw(g2);
				}
			}
		}
		// draws selected pieces on top to ensure they are on the top layer
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.hasPiece(i, j)) {
					if (board.getSquare(i, j).isSelected()) {
						board.getSquare(i, j).draw(g2);
					}
				}
			}
		}

		Piece cur_piece = null;

		for (int a = 0; a < 8; a++) {
			for (int b = 0; b < 8; b++) {
				if (View.this.board.hasPiece(a, b)) {
					if (View.this.board.getSquare(a, b).isSelected()) {
						cur_piece = View.this.board.getSquare(a, b);
						for (int i = 0; i < 8; i++) {
							for (int j = 0; j < 8; j++) {
								if (cur_piece.checkLegalMove(new Point(i * 62, j * 62), this.board) == true) {
									g2.setColor(Color.lightGray);
									g2.fillOval(i * 62 + 15, j * 62 + 15, 31, 31);
									// g2.drawRect(i*62, j*62, 62, 62);
								}
							}
						}
					}
				}
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// mouseP = e.getPoint();
		// System.out.println(board.getSquare(mouseP.x/62, mouseP.y/62).getType() +"
		// "+board.getSquare(mouseP.x/62, mouseP.y/62).getColor() );
		// board.getSquare(mouseP.x/62, mouseP.y/62).setSelected(true);

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
		mouseP = e.getPoint(); // sets mousepoint to the current point for when the mouse is dragged
		// this for loop cycles through all squares on the board

		if (!View.this.game.getVictory()) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (View.this.board.hasPiece(i, j)) { // checks if board square has a piece
						if (View.this.board.getSquare(i, j).contains(e.getPoint())) { // checks if piece contains the
																						// place mouse was clicked
							if(View.this.board.getSquare(i,j).getColor() == View.this.game.getTurn()) { // check if it is the
																								// right turn
								View.this.board.getSquare(i, j).setSelected(true); // set selected to true
								//View.this.text += View.this.board.getSquare(i, j).getType() + " selected! \n";
								// add to console
								// View.this.textBox.setText(View.this.text);
							}
						}
					}
				}
			}
		}
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		boolean pieceSelected = false; // keeps track of whether or not a piece was selected
		Piece selectedPiece = null; // keeps track of the selected piece
		Piece eatenPiece = null; // keeps track of piece on square another piece was moved to
		int oldX = 0; // keeps track of prior position if piece
		int oldY = 0;

		// for loop cyles through board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (View.this.board.hasPiece(i, j)) { // checks if board square has a piece
					if (View.this.board.getSquare(i, j).isSelected()) { // then checks if the piece was selected
						pieceSelected = true;
						selectedPiece = View.this.board.getSquare(i, j); // records data about piece
						oldX = i;
						oldY = j;
					}
				}
			}
		}

		if (pieceSelected) {
			if (selectedPiece.checkLegalMove(e.getPoint(), View.this.board)) { // check legal moves
				int newX = ((int) e.getPoint().getX()) / 62; // record new point
				int newY = ((int) e.getPoint().getY()) / 62;
				/*
				 * Make the move
				 */
				// check for castling here
				if (selectedPiece.getType().equals("King") && newX - oldX == 2) {
					if (selectedPiece.getColor() == false) { // case that piece is white
						View.this.board.clearSquare(oldX, oldY);
						View.this.board.setSquare(newX, newY, selectedPiece);
						selectedPiece.setLocation(newX, newY);
						Piece rook = View.this.board.getSquare(7, 7);
						View.this.board.setSquare(5, 7, rook);
						rook.setLocation(5, 7);
						View.this.board.clearSquare(7, 7);
						//View.this.text += "White castles. \n";
					} else {
						// case that piece is black
						View.this.board.clearSquare(oldX, oldY);
						View.this.board.setSquare(newX, newY, selectedPiece);
						selectedPiece.setLocation(newX, newY);
						Piece rook = View.this.board.getSquare(7, 0);
						View.this.board.setSquare(5, 0, rook);
						rook.setLocation(5, 0);
						View.this.board.clearSquare(7, 0);
						//View.this.text += "White castles. \n";
					}
				} else {
					if (View.this.board.hasPiece(newX, newY)) {
						eatenPiece = View.this.board.getSquare(newX, newY);
					}
					View.this.board.clearSquare(oldX, oldY);
					View.this.board.setSquare(newX, newY, selectedPiece);
					// snap to square
					if (selectedPiece.setLocation(newX, newY))
						System.out.println("location set"); // debug line
					if ((eatenPiece != null)) {
						//View.this.text += selectedPiece.getType() + " eats " + eatenPiece.getType() + "\n";
						// View.this.textBox.setText(View.this.text);
					}
//					View.this.text += selectedPiece.getType() + " was moved to: " + View.this.columns[newX] + (newY + 1)
//							+ "\n";
					System.out.println("Snapped to square");
				}

				// View.this.textBox.setText(View.this.text); //record to console

				View.this.game.checkVictory(View.this.board);
				
				
				if (View.this.game.getVictory()) {
					if (View.this.game.getTurn() == false) {
						// View.this.text += "White has taken black's king and won the game!";
					} else {
						// View.this.text += "Black has taken white's king and won the game!";
					}
					// View.this.textBox.setText(View.this.text);
				} else {
					View.this.game.changeTurn();
					
					System.out.println(game.getTurn());

//					if (View.this.game.getMode() != 0) { // case that the game is playing with AI
//
//						/*
//						 * This Thread is what runs in teh background and computes the AI's best move
//						 * and makes that move. It also updates the textArea with the appropriate text
//						 */
//						Thread t = new Thread(new Runnable() {
//							public void run() {
//								View.this.text += View.this.game.getAI().makeMove(View.this.board);
//								View.this.textBox.setText(View.this.text);
//								View.this.game.checkVictory(View.this.board);
//								if (View.this.game.getVictory()) {
//									if (View.this.game.getTurn() == false) {
//										View.this.text += "White has taken black's king and won the game!";
//									} else {
//										View.this.text += "Black has taken white's king and won the game!";
//									}
//									View.this.textBox.setText(View.this.text);
//								} else {
//									View.this.game.changeTurn();
//								}
//								repaint(); // repaints board after AI makes move
//							}
//						});
//						// Have AI make move if game is in AI mode
//						t.start(); // begins thread
//
//					}
				}

			} else {

				selectedPiece.setLocation(oldX, oldY);
				View.this.text += "Illegal move! \n";

				// View.this.textBox.setText(View.this.text);
			}
			selectedPiece.setSelected(false);
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point lastMousePoint = mouseP; // mousePoint used to keep track of where the piece should be drawn
		mouseP = e.getPoint();
		for (int i = 0; i < 8; i++) { // nested for loops iterate through the whole board
			for (int j = 0; j < 8; j++) {
				if (View.this.board.hasPiece(i, j)) {
					if (View.this.board.getSquare(i, j).isSelected()) {
						double dx = mouseP.getX() - lastMousePoint.getX(); // calculate how much to change piece
																			// position
						double dy = mouseP.getY() - lastMousePoint.getY();
						View.this.board.getSquare(i, j).translate((int) dx, (int) dy); // translate piece
					}
				}
			}
		}

		repaint(); // repaints the View everytime mouse is dragged

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
