package model;

import java.awt.Point;

public class Queen extends Piece {

	public Queen(Color color) {
		super(color);
	}
	
	public boolean move(Board domain, Point position) {
		return true;
	}
	
}
