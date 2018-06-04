package model;

import java.awt.Point;
import java.util.ArrayList;

public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color);
	}

	public Point[] movePossibilities(Board domain, Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		Point p = (Point) from.clone();
		//movimenta na diagonal direita inferior
		p.x = from.x;
		p.y = from.y;
		for (int i = 1; p.y < Board.dimension - 1 && p.x < Board.dimension - 1; i++) {
			p.x = from.x + i;
			p.y = from.y + i;
			if (domain.squareIsVacant(p)) {
				possibilitiesList.add((Point) p.clone());
			} else {
				final Piece piece = domain.getPieceAt(p);
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
			if (domain.squareIsVacant(p)) {
				possibilitiesList.add((Point) p.clone());
			} else {
				final Piece piece = domain.getPieceAt(p);
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
			if (domain.squareIsVacant(p)) {
				possibilitiesList.add((Point) p.clone());
			} else {
				final Piece piece = domain.getPieceAt(p);
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
			if (domain.squareIsVacant(p)) {
				possibilitiesList.add((Point) p.clone());
			} else {
				final Piece piece = domain.getPieceAt(p);
				if (piece.getColor() != pieceColor) {
					possibilitiesList.add((Point) p.clone());
				}
				break;
			}
		}
		final Point[] possibilitiesArray = possibilitiesList.toArray(new Point[possibilitiesList.size()]);

		return possibilitiesArray;
	}

}
