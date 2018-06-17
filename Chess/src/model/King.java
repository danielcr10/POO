package model;

import java.awt.Point;
import java.util.ArrayList;

class King extends Piece {

	public King(Board board, Color color) {
		super(board, color);
	}
	
	public ArrayList<Point> movePossibilities(Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		for(Point p : getTargetPositions(from)) {
			if(pieceBoard.contains(p) && pieceBoard.squareIsVacant(p) && !pieceBoard.pieceIsVulnerableAt(this, p)) {
				possibilitiesList.add(p);
			}
		}

		possibilitiesList.addAll(attackPossibilities(from));

		return possibilitiesList;
	}

	public ArrayList<Point> attackPossibilities(Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		for(Point p : getTargetPositions(from)) {
			if(pieceBoard.contains(p) && !pieceBoard.squareIsVacant(p)) {
				final Piece piece = pieceBoard.getPieceAt(p);
				if(piece.getColor() != pieceColor && (piece instanceof King || !pieceBoard.pieceIsVulnerableAt(this, p))) {
					possibilitiesList.add(p);
				}
			}
		}

		return possibilitiesList;
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
