package model;

import java.util.ArrayList;
import java.awt.Point;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class Match {
	
	private Board matchBoard = new Board();

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void movePieceFromTo(Point from, Point to) {
		String[][] boardBefore = getBoard();
		matchBoard.movePieceFromTo(from, to);
		String[][] boardAfter = getBoard();
		this.pcs.firePropertyChange("board", boardBefore, boardAfter);
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

}
