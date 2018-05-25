package model;

import java.awt.Point;

public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color);
	}
	
	public boolean move(Board domain, Point position) {
		return true;
	}
	
}
