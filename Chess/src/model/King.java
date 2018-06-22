package model;

import java.awt.Point;
import java.util.ArrayList;

class King extends Piece {

	private boolean moved = false;

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public King(Board board, Color color) {
		super(board, color);
	}

	public boolean isInCheck() {
		return pieceBoard.pieceIsVulnerableAt(this, getCurrentPosition());
	}

	public ArrayList<Point> movePossibilities() {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		for (Point p : getTargetPositions()) {
			if (pieceBoard.contains(p) && pieceBoard.squareIsVacant(p) && !pieceBoard.pieceIsVulnerableAt(this, p)) {
				possibilitiesList.add(p);
			}
		}

		possibilitiesList.addAll(attackPossibilities());
		possibilitiesList.addAll(castlePossibilities());

		return possibilitiesList;
	}

	public ArrayList<Point> attackPossibilities() {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		for (Point p : getTargetPositions()) {
			if (pieceBoard.contains(p) && !pieceBoard.squareIsVacant(p)) {
				final Piece piece = pieceBoard.getPieceAt(p);
				if (piece.getColor() != pieceColor
						&& (piece instanceof King || !pieceBoard.pieceIsVulnerableAt(this, p))) {
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

	private ArrayList<Point> castlePossibilities() {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();

		if (!moved) {
			final PieceSet set = pieceBoard.getPieceSet(pieceColor);
			final ArrayList<Rook> rooks = set.getRooks();
			Point p = (Point) this.getCurrentPosition().clone();
			for (Rook rook : rooks) {
				if (!rook.getMoved()) {
					if (rook.getCurrentPosition().x == 0) {
						p.x = 2;
					} else {
						p.x = 6;
					}
					if (isVacantBtw(rook, this) && !pieceBoard.pieceIsVulnerableAt(this, p)) { //
						possibilitiesList.add((Point) p.clone());
					}
				}
			}
		}

		return possibilitiesList;
	}

	private boolean isVacantBtw(Piece p1, Piece p2) {
		boolean vacant = true;
		Point aux = (Point) p2.getCurrentPosition().clone();
		if (p1.getCurrentPosition().x < p2.getCurrentPosition().x) {
			aux = (Point) p1.getCurrentPosition().clone();
		}
		for (int i = 1; i < Math.abs(p1.getCurrentPosition().x - p2.getCurrentPosition().x); i++) {
			aux.x++;
			vacant = pieceBoard.squareIsVacant(aux);
			if (!vacant) {
				return vacant;
			}
		}
		return vacant;
	}

	public void move(Point to) {
		if (Math.abs(getCurrentPosition().x - to.x) > 1) {
			final Rook closestRook = getClosestRook(to);
			closestRook.doCastle();
		}
		super.move(to);
		moved = true;
	}

	private Rook getClosestRook(Point to) {
		Rook closestRook;
		final PieceSet set = pieceBoard.getPieceSet(pieceColor);
		final ArrayList<Rook> rooks = set.getRooks();
		final Rook rook1 = rooks.get(0);
		final Rook rook2 = rooks.get(1);

		if (Math.abs((rook1.getCurrentPosition().x - to.x)) < Math.abs((rook2.getCurrentPosition().x - to.x))) {
			closestRook = rook1;
		} else {
			closestRook = rook2;
		}

		return closestRook;
	}

}
