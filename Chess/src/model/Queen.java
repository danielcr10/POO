package model;

import java.awt.Point;
import java.util.ArrayList;

class Queen extends Piece implements Mortal {

	public Queen(Board board, Color color) {
		super(board, color);
	}

	public ArrayList<Point> movePossibilities(Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		Point p = (Point) from.clone();
		p.y = from.y;
		for (int i = 1; p.y < Board.dimension - 1; i++) {
			p.y = from.y + i;
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
		p.y = from.y;
		for (int k = 1; p.y > 0; k++) {
			p.y = from.y - k;
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
		p.y = from.y;
		p.x = from.x;
		for (int j = 1; p.x < Board.dimension - 1; j++) {
			p.x = from.x + j;
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
		p.x = from.x;
		for (int l = 1; p.x > 0; l++) {
			p.x = from.x - l;
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
		p.x = from.x;
		p.y = from.y;
		for (int ii = 1; p.y < Board.dimension - 1 && p.x < Board.dimension - 1; ii++) {
			p.x = from.x + ii;
			p.y = from.y + ii;
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
		p.x = from.x;
		p.y = from.y;
		for (int kk = 1; p.y < Board.dimension - 1 && p.x > 0; kk++) {
			p.x = from.x - kk;
			p.y = from.y + kk;
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
		p.x = from.x;
		p.y = from.y;
		for (int jj = 1; p.y > 0 && p.x < Board.dimension - 1; jj++) {
			p.x = from.x + jj;
			p.y = from.y - jj;
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
		p.x = from.x;
		p.y = from.y;
		for (int ll = 1; p.y > 0 && p.x > 0; ll++) {
			p.x = from.x - ll;
			p.y = from.y - ll;
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
