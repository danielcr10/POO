package model;

import java.util.ArrayList;
import java.awt.Point;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class Match {
	
	private Board matchBoard = new Board();

	private Color currentPlayer = Color.WHITE;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void movePieceFromTo(Point from, Point to) {
		String[][] boardBefore = getBoard();
		matchBoard.movePieceFromTo(from, to);
		String[][] boardAfter = getBoard();
		this.pcs.firePropertyChange("board", boardBefore, boardAfter);
		// TODO: Melhorar a forma como fazemos essa conversão.
		currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
	}

	public ArrayList<Point> getMovePossibilities(Point p) {
		ArrayList<Point> possibilities = new ArrayList<Point>();

		Piece pieceAtPosition = matchBoard.getPieceAt(p);	
		if(pieceAtPosition != null) {
			possibilities = pieceAtPosition.movePossibilities(matchBoard, p);
		}

		return possibilities;
	}
	
	public String[][] getBoard() {
		String[][] boardAsString = new String[Board.dimension][Board.dimension];
		for(int i = 0; i < boardAsString.length; i++) {
			for(int j = 0; j < boardAsString.length; j++) {
				final Piece piece = matchBoard.getPieceAt(new Point(j, i)); 
				if(piece != null) {
					boardAsString[i][j] = piece.getClass().getSimpleName() + piece.getColor().toString();
				}
				else {
					boardAsString[i][j] = "";
				}
				
			}
		}
		
		return boardAsString;
	}

	public Color getPieceColorAt(Point p) {
		Piece pieceAtPosition = matchBoard.getPieceAt(p);
		return pieceAtPosition.getColor();
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

}
