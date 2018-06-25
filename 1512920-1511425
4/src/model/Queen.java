package model;

import java.awt.Point;
import java.util.ArrayList;

class Queen extends KingdomProtector {

	private static final long serialVersionUID = 1L;

	public Queen(Board board, Color color) {
		super(board, color);
	}

	public ArrayList<Point> movePossibilities() {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		Point p = (Point) currentPosition.clone();
		p.y = currentPosition.y;
		for (int i = 1; p.y < Board.dimension - 1; i++) {
			p.y = currentPosition.y + i;
			if (pieceBoard.squareIsVacant(p)) {
				possibilitiesList.add((Point) p.clone());
			} else {
				final Piece piece = pieceBoard.getPieceAt(p);
				if (piece.getColor() != pieceColor) {
					possibilitiesList.add((Point) p.clone());
				}
				break;
			}
		}
		p.y = currentPosition.y;
		for (int k = 1; p.y > 0; k++) {
			p.y = currentPosition.y - k;
			if (pieceBoard.squareIsVacant(p)) {
				possibilitiesList.add((Point) p.clone());
			} else {
				final Piece piece = pieceBoard.getPieceAt(p);
				if (piece.getColor() != pieceColor) {
					possibilitiesList.add((Point) p.clone());
				}
				break;
			}
		}
		p.y = currentPosition.y;
		p.x = currentPosition.x;
		for (int j = 1; p.x < Board.dimension - 1; j++) {
			p.x = currentPosition.x + j;
			if (pieceBoard.squareIsVacant(p)) {
				possibilitiesList.add((Point) p.clone());
			} else {
				final Piece piece = pieceBoard.getPieceAt(p);
				if (piece.getColor() != pieceColor) {
					possibilitiesList.add((Point) p.clone());
				}
				break;
			}
		}
		p.x = currentPosition.x;
		for (int l = 1; p.x > 0; l++) {
			p.x = currentPosition.x - l;
			if (pieceBoard.squareIsVacant(p)) {
				possibilitiesList.add((Point) p.clone());
			} else {
				final Piece piece = pieceBoard.getPieceAt(p);
				if (piece.getColor() != pieceColor) {
					possibilitiesList.add((Point) p.clone());
				}
				break;
			}
		}
		p.x = currentPosition.x;
		p.y = currentPosition.y;
		for (int ii = 1; p.y < Board.dimension - 1 && p.x < Board.dimension - 1; ii++) {
			p.x = currentPosition.x + ii;
			p.y = currentPosition.y + ii;
			if (pieceBoard.squareIsVacant(p)) {
				possibilitiesList.add((Point) p.clone());
			} else {
				final Piece piece = pieceBoard.getPieceAt(p);
				if (piece.getColor() != pieceColor) {
					possibilitiesList.add((Point) p.clone());
				}
				break;
			}
		}
		p.x = currentPosition.x;
		p.y = currentPosition.y;
		for (int kk = 1; p.y < Board.dimension - 1 && p.x > 0; kk++) {
			p.x = currentPosition.x - kk;
			p.y = currentPosition.y + kk;
			if (pieceBoard.squareIsVacant(p)) {
				possibilitiesList.add((Point) p.clone());
			} else {
				final Piece piece = pieceBoard.getPieceAt(p);
				if (piece.getColor() != pieceColor) {
					possibilitiesList.add((Point) p.clone());
				}
				break;
			}
		}
		p.x = currentPosition.x;
		p.y = currentPosition.y;
		for (int jj = 1; p.y > 0 && p.x < Board.dimension - 1; jj++) {
			p.x = currentPosition.x + jj;
			p.y = currentPosition.y - jj;
			if (pieceBoard.squareIsVacant(p)) {
				possibilitiesList.add((Point) p.clone());
			} else {
				final Piece piece = pieceBoard.getPieceAt(p);
				if (piece.getColor() != pieceColor) {
					possibilitiesList.add((Point) p.clone());
				}
				break;
			}
		}
		p.x = currentPosition.x;
		p.y = currentPosition.y;
		for (int ll = 1; p.y > 0 && p.x > 0; ll++) {
			p.x = currentPosition.x - ll;
			p.y = currentPosition.y - ll;
			if (pieceBoard.squareIsVacant(p)) {
				possibilitiesList.add((Point) p.clone());
			} else {
				final Piece piece = pieceBoard.getPieceAt(p);
				if (piece.getColor() != pieceColor) {
					possibilitiesList.add((Point) p.clone());
				}
				break;
			}
		}

		return possibilitiesList;
	}

	public void die() {
		final PieceSet set = pieceBoard.getPieceSet(pieceColor);
		set.removeQueen(this);
	}

}
