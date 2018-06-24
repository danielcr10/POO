package model;

import java.util.ArrayList;
import java.awt.Point;

public abstract class KingdomProtector extends Piece {

	private static final long serialVersionUID = 1L;

	protected KingdomProtector(Board board, Color color) {
		super(board, color);
	}

	abstract public void die();

	protected King myKing() {
		final PieceSet set = pieceBoard.getPieceSet(pieceColor);
		final King king = set.getKing();

		return king;
	}

	public ArrayList<Point> validMovePossibilities() {
		ArrayList<Point> possibilitiesList = movePossibilities();
		final ArrayList<Point> safePossibilities = new ArrayList<>();
		final Point originalPosition = getCurrentPosition();
		for(Point position : possibilitiesList) {
			final Piece livingPiece = pieceBoard.getPieceAt(position);
			pieceBoard.clearPosition(originalPosition);
			pieceBoard.setPieceAt(this, position);
			if(!myKing().isInCheck()) {
				safePossibilities.add(position);
			}
			pieceBoard.setPieceAt(this, originalPosition);
			if(livingPiece != null) {
				pieceBoard.setPieceAt(livingPiece, position);
			}
			else {
				pieceBoard.clearPosition(position);
			}
		}

		possibilitiesList = safePossibilities;

		return possibilitiesList;
	}
}
