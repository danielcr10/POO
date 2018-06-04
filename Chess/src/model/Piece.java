package model;

import java.awt.Point;

enum Color {
	BLACK(1), WHITE(-1);

	private final int value;

	Color(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	
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
	
	abstract public Point[] movePossibilities(Board domain, Point from);
	
}
