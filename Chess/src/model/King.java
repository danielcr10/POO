package model;

import java.awt.Point;
import java.util.ArrayList;

public class King extends Piece {

	public King(Color color) {
		super(color);
	}
	
	public ArrayList<Point> movePossibilities(Board domain, Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		for(Point p : reachablePositions(domain, from)) {
			if(!domain.enemyPiecesCanReach(p, getColor())) {
				possibilitiesList.add(p);
			}
		}

		return possibilitiesList;
	}

	public ArrayList<Point> reachablePositions(Board domain, Point from) {
		final ArrayList<Point> positionsList = new ArrayList<>();
		final Point[] targetPositions = getTargetPositions(from);
		for(Point p : targetPositions) {
			if(domain.contains(p) && (domain.squareIsVacant(p) || domain.getPieceAt(p).getColor() != pieceColor)) {
				positionsList.add(p);
			}
		}

		return positionsList;
	}
	
	private Point[] getTargetPositions(Point from) {
		final int targetPointCount = 8;
		final Point[] targetPositions = new Point[targetPointCount];

		targetPositions[0] = new Point(from.x, from.y - 1);
		targetPositions[1] = new Point(from.x + 1, from.y);
		targetPositions[2] = new Point(from.x - 1, from.y);
		targetPositions[3] = new Point(from.x, from.y + 1);
		targetPositions[4] = new Point(from.x - 1, from.y - 1);
		targetPositions[5] = new Point(from.x + 1, from.y - 1);
		targetPositions[6] = new Point(from.x - 1, from.y + 1);
		targetPositions[7] = new Point(from.x + 1, from.y + 1);

		return targetPositions;
	}

}
