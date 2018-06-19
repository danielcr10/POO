package model;

import java.util.ArrayList;
import java.awt.Point;

public abstract class Piece {
	
	protected Board pieceBoard;

	protected Color pieceColor;

	protected Point currentPosition;
	
	protected Piece(Board board, Color color) {
		pieceBoard = board;
		pieceColor = color;
	}

	public void setCurrentPosition(Point position) {
		currentPosition = position;
	}
	
	public Color getColor() {
		return pieceColor;
	}

	public Board getBoard() {
		return pieceBoard;
	}

	public Point getCurrentPosition() {
		return currentPosition;
	}
	
	abstract public ArrayList<Point> movePossibilities();

	public ArrayList<Point> attackPossibilities() {
		return movePossibilities();
	}

	public void move(Point to) {
		pieceBoard.clearPosition(currentPosition);
		if(!pieceBoard.squareIsVacant(to)) {
			final Mortal pieceAtPosition = (Mortal)pieceBoard.getPieceAt(to);
			pieceAtPosition.die();
		}
		pieceBoard.setPieceAt(this, to);

		// Muda o status 'passed' de todos os peões da mesma cor para false
		pieceBoard.setPawnsPassedStatus(pieceColor, false);
	}
	
}
