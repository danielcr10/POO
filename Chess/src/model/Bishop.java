package model;

import java.awt.Point;
import java.util.ArrayList;

class Bishop extends KingdomProtector {

	private static final long serialVersionUID = 1L;

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	public ArrayList<Point> movePossibilities() {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		Point p = (Point) currentPosition.clone();
		//movimenta na diagonal direita inferior
		p.x = currentPosition.x;
		p.y = currentPosition.y;
		for (int i = 1; p.y < Board.dimension - 1 && p.x < Board.dimension - 1; i++) {
			p.x = currentPosition.x + i;
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
		//movimenta na diagonal esquerda inferior
		p.x = currentPosition.x;
		p.y = currentPosition.y;
		for (int k = 1; p.y < Board.dimension - 1 && p.x > 0; k++) {
			p.x = currentPosition.x - k;
			p.y = currentPosition.y + k;
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
		//movimenta na diagonal direita superior
		p.x = currentPosition.x;
		p.y = currentPosition.y;
		for (int j = 1; p.y > 0 && p.x < Board.dimension - 1; j++) {
			p.x = currentPosition.x + j;
			p.y = currentPosition.y - j;
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
		//movimenta na diagonal esquerda superior
		p.x = currentPosition.x;
		p.y = currentPosition.y;
		for (int l = 1; p.y > 0 && p.x > 0; l++) {
			p.x = currentPosition.x - l;
			p.y = currentPosition.y - l;
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
		set.removeBishop(this);
	}

}
