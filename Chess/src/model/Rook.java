package model;

import java.awt.Point;
import java.util.ArrayList;

public class Rook extends Piece {

	public Rook(Color color) {
		super(color);
	}
	
	Point[] movePossibilities(Board domain, Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		
		final Point[] possibilitiesArray = (Point[]) possibilitiesList.toArray();
		
		return possibilitiesArray;	
	}
	
}
