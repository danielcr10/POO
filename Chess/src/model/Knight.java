package model;

import java.awt.Point;
import java.util.ArrayList;

class Knight extends KingdomProtector {

	public Knight(Board board, Color color) {
		super(board, color);
	}
	
	public ArrayList<Point> movePossibilities() {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();

		final Point[] targetPoints = getTargetPoints();
		for(Point p : targetPoints) {
			if(pieceBoard.contains(p)) {
				if(pieceBoard.squareIsVacant(p) || pieceBoard.getPieceAt(p).getColor() != pieceColor) {
					possibilitiesList.add(p);
				}
			}
		}
		
		return possibilitiesList;
	}
	
	private Point[] getTargetPoints() {
		final int targetPointCount = 8;
		final Point[] targetPoints = new Point[targetPointCount];
		targetPoints[0] = new Point(currentPosition.x - 1, currentPosition.y + 2);
		targetPoints[1] = new Point(currentPosition.x + 1, currentPosition.y + 2);
		targetPoints[2] = new Point(currentPosition.x + 2, currentPosition.y + 1);
		targetPoints[3] = new Point(currentPosition.x + 2, currentPosition.y - 1);
		targetPoints[4] = new Point(currentPosition.x + 1, currentPosition.y - 2);
		targetPoints[5] = new Point(currentPosition.x - 1, currentPosition.y - 2);
		targetPoints[6] = new Point(currentPosition.x - 2, currentPosition.y - 1);
		targetPoints[7] = new Point(currentPosition.x - 2, currentPosition.y + 1);

		return targetPoints;
	}

	public void die() {
		final PieceSet set = pieceBoard.getPieceSet(pieceColor);
		set.removeKnight(this);
	}
}
