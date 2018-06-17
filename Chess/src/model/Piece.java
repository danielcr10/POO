package model;

import java.util.ArrayList;
import java.awt.Point;

public abstract class Piece {
	
	protected Board pieceBoard;

	protected Color pieceColor;
	
	protected Piece(Board board, Color color) {
		pieceBoard = board;
		pieceColor = color;
	}
	
	public Color getColor() {
		return pieceColor;
	}

	public Board getBoard() {
		return pieceBoard;
	}
	
	abstract public ArrayList<Point> movePossibilities(Point from);

	public ArrayList<Point> attackPossibilities(Point from) {
		return movePossibilities(from);
	}

	public void move(Point from, Point to) {
		pieceBoard.clearPosition(from);
		pieceBoard.setPieceAt(this, to);

		// Muda o status 'passed' de todos os peões da mesma cor para false
		pieceBoard.setPawnsPassedStatus(pieceColor, false);
	}
	
}
