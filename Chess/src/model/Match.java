package model;

import java.util.ArrayList;
import java.awt.Point;
import java.beans.PropertyChangeListener;

public class Match {
	
	private Board matchBoard = new Board();

	private Color currentPlayer = Color.WHITE;

	public void movePieceFromTo(Point from, Point to) {
		matchBoard.movePieceFromTo(from, to);
		// TODO: Melhorar a forma como fazemos essa conversão.
		currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
	}

	public void promotePawn(String piece) {
		matchBoard.promotePawnTo(piece);
	}

	public ArrayList<Point> getMovePossibilities(Point p) {
		ArrayList<Point> possibilities; 

		if(!matchBoard.squareIsVacant(p)) {
			final Piece pieceAtPosition = matchBoard.getPieceAt(p);	
			possibilities = pieceAtPosition.movePossibilities(p);
		}
		else {
			possibilities = new ArrayList<Point>();
		}

		return possibilities;
	}

	public String[][] getBoard() {
		return matchBoard.getBoardState();
	}

	public Color getPieceColorAt(Point p) {
		Piece pieceAtPosition = matchBoard.getPieceAt(p);
		return pieceAtPosition.getColor();
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public void setBoardListener(PropertyChangeListener boardListener) {
		matchBoard.addPropertyChangeListener(boardListener);
	}

}
