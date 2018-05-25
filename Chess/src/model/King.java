package model;

import java.awt.Point;

public class King extends Piece {

	public King(Color color) {
		super(color);
	}
	
	public boolean move(Board domain, Point position) {
		return true;
	}
	
}
