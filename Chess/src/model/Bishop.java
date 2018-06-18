package model;

import java.awt.Point;
import java.util.ArrayList;

class Bishop extends Piece implements Mortal {

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	public ArrayList<Point> movePossibilities(Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		Point p = (Point) from.clone();
		//movimenta na diagonal direita inferior
		p.x = from.x;
		p.y = from.y;
		for (int i = 1; p.y < Board.dimension - 1 && p.x < Board.dimension - 1; i++) {
			p.x = from.x + i;
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
		//movimenta na diagonal esquerda inferior
		p.x = from.x;
		p.y = from.y;
		for (int k = 1; p.y < Board.dimension - 1 && p.x > 0; k++) {
			p.x = from.x - k;
			p.y = from.y + k;
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
		p.x = from.x;
		p.y = from.y;
		for (int j = 1; p.y > 0 && p.x < Board.dimension - 1; j++) {
			p.x = from.x + j;
			p.y = from.y - j;
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
		p.x = from.x;
		p.y = from.y;
		for (int l = 1; p.y > 0 && p.x > 0; l++) {
			p.x = from.x - l;
			p.y = from.y - l;
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
