package model;

import java.awt.Point;
import java.util.ArrayList;

public class Knight extends Piece {

	public Knight(Color color) {
		super(color);
	}
	
	public Point[] movePossibilities(Board domain, Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();

		final Point[] targetPoints = getTargetPoints(from);
		for(Point p : targetPoints) {
			if(domain.contains(p)) {
				if(domain.squareIsVacant(p) || domain.getPieceAt(p).getColor() != pieceColor) {
					possibilitiesList.add(p);
				}
			}
		}
		
		final Point[] possibilitiesArray = possibilitiesList.toArray(new Point[possibilitiesList.size()]);
		
		return possibilitiesArray;
	}
	
	private Point[] getTargetPoints(Point from) {
		final int targetPointCount = 8;
		final Point[] targetPoints = new Point[targetPointCount];
		targetPoints[0] = new Point(from.x - 1, from.y + 2);
		targetPoints[1] = new Point(from.x + 1, from.y + 2);
		targetPoints[2] = new Point(from.x + 2, from.y + 1);
		targetPoints[3] = new Point(from.x + 2, from.y - 1);
		targetPoints[4] = new Point(from.x + 1, from.y - 2);
		targetPoints[5] = new Point(from.x - 1, from.y - 2);
		targetPoints[6] = new Point(from.x - 2, from.y - 1);
		targetPoints[7] = new Point(from.x - 2, from.y + 1);

		return targetPoints;
	}
}
