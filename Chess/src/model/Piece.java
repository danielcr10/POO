package model;

import java.awt.Point;

enum Color {
	BLACK, WHITE;
	
	public String toString() {
		final String colorString = super.toString();
		return colorString.substring(0, 1) + colorString.substring(1).toLowerCase();
	}
}

public abstract class Piece {
	
	protected Color pieceColor;
	
	protected Piece(Color color) {
		pieceColor = color;
	}
	
	public Color getColor() {
		return pieceColor;
	}
	
	abstract public boolean move(Board domain, Point position);
	
//	abstract Point[] movePossibilities();
}
