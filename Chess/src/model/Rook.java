package model;

import java.awt.Point;

public class Rook extends Piece {

	public Rook(Color color) {
		super(color);
	}
	
	public boolean move(Board domain, Point position) {
		return true;
	}
	
}
