package model;

import java.util.ArrayList;
import java.awt.Point;

public abstract class Piece {
	
	protected Color pieceColor;
	
	protected Piece(Color color) {
		pieceColor = color;
	}
	
	public Color getColor() {
		return pieceColor;
	}
	
	abstract public ArrayList<Point> movePossibilities(Board domain, Point from);

	public void move(Board domain,Point from, Point to) {
		domain.clearPosition(from);
		domain.addPieceAt(this, to);
	}
	
}
