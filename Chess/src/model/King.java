package model;

import java.awt.Point;
import java.util.ArrayList;

class King extends Piece {

	public King(Board board, Color color) {
		super(board, color);
	}
	
	public ArrayList<Point> movePossibilities() {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		for(Point p : getTargetPositions()) {
			if(pieceBoard.contains(p) && pieceBoard.squareIsVacant(p) && !pieceBoard.pieceIsVulnerableAt(this, p)) {
				possibilitiesList.add(p);
			}
		}

		possibilitiesList.addAll(attackPossibilities());

		return possibilitiesList;
	}

	public ArrayList<Point> attackPossibilities() {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		for(Point p : getTargetPositions()) {
			if(pieceBoard.contains(p) && !pieceBoard.squareIsVacant(p)) {
				final Piece piece = pieceBoard.getPieceAt(p);
				if(piece.getColor() != pieceColor && (piece instanceof King || !pieceBoard.pieceIsVulnerableAt(this, p))) {
					possibilitiesList.add(p);
				}
			}
		}

		return possibilitiesList;
	}

	private Point[] getTargetPositions() {
		final int targetPointCount = 8;
		final Point[] targetPositions = new Point[targetPointCount];

		targetPositions[0] = new Point(currentPosition.x, currentPosition.y - 1);
		targetPositions[1] = new Point(currentPosition.x + 1, currentPosition.y);
		targetPositions[2] = new Point(currentPosition.x - 1, currentPosition.y);
		targetPositions[3] = new Point(currentPosition.x, currentPosition.y + 1);
		targetPositions[4] = new Point(currentPosition.x - 1, currentPosition.y - 1);
		targetPositions[5] = new Point(currentPosition.x + 1, currentPosition.y - 1);
		targetPositions[6] = new Point(currentPosition.x - 1, currentPosition.y + 1);
		targetPositions[7] = new Point(currentPosition.x + 1, currentPosition.y + 1);

		return targetPositions;
	}

}
