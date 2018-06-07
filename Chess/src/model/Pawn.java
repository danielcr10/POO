package model;

import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends Piece {
	
	private boolean moved = false;

	public Pawn(Color color) {
		super(color);
	}
	
	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public ArrayList<Point> movePossibilities(Board domain, Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		final int sense = pieceColor.getValue();
		Point p = (Point)from.clone();
		if(!moved) {
			p.y = from.y + 2 * sense;
			if(domain.squareIsVacant(p)) {
				possibilitiesList.add((Point)p.clone());
			}
		}

		p.y = from.y + 1 * sense;
		if(p.y >= 0 && p.y < Board.dimension) {
			if(domain.squareIsVacant(p)) {
				possibilitiesList.add((Point)p.clone());
			}

			p.x -= 1;
			if(p.x >= 0 && !domain.squareIsVacant(p)) {
				final Piece piece = domain.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
			}

			p.x = from.x;
			p.x += 1;
			if(p.x < Board.dimension && !domain.squareIsVacant(p)) {
				final Piece piece = domain.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
			}
		}
		
		return possibilitiesList;
	}

	public ArrayList<Point> reachablePositions(Board domain, Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		final int sense = pieceColor.getValue();
		Point p = (Point)from.clone();
		if(!moved) {
			p.y = from.y + 2 * sense;
			if(domain.squareIsVacant(p)) {
				possibilitiesList.add((Point)p.clone());
			}
		}

		p.y = from.y + 1 * sense;
		if(p.y >= 0 && p.y < Board.dimension) {
			if(domain.squareIsVacant(p)) {
				possibilitiesList.add((Point)p.clone());
			}

			p.x -= 1;
			if(p.x >= 0 && domain.squareIsVacant(p)) {
				possibilitiesList.add((Point)p.clone());
			}

			p.x = from.x;
			p.x += 1;
			if(p.x < Board.dimension && domain.squareIsVacant(p)) {
				possibilitiesList.add((Point)p.clone());
			}
		}

		return possibilitiesList;
	}

	public void move(Board domain, Point from, Point to) {
		super.move(domain, from, to);
		moved = true;
	}
	
}
