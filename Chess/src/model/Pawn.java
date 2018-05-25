package model;

import java.awt.Point;

public class Pawn extends Piece {
	
	public Pawn(Color color) {
		super(color);
	}
	
	public boolean move(Board domain, Point position) {
		return true;
	}
	
}
