package model;

import java.awt.Point;
import java.util.ArrayList;

class Pawn extends Piece {
	
	private boolean moved = false;

	private boolean passed = false;

	public Pawn(Color color) {
		super(color);
	}
	
	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public boolean justPassed() {
		return passed;
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
		if(p.y >= 0 && p.y < Board.dimension && domain.squareIsVacant(p)) {
			possibilitiesList.add((Point)p.clone());
		}

		possibilitiesList.addAll(attackPossibilities(domain, from));

		return possibilitiesList;
	}

	public ArrayList<Point> attackPossibilities(Board domain, Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		final int sense = pieceColor.getValue();
		Point p = (Point)from.clone();
		p.y = from.y + 1 * sense;
		if(p.y >= 0 && p.y < Board.dimension) {
			p.x -= 1;
			if(p.x >= 0 && !domain.squareIsVacant(p)) {
				final Piece piece = domain.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
			}

			p.x = from.x + 1;
			if(p.x < Board.dimension && !domain.squareIsVacant(p)) {
				final Piece piece = domain.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
			}
		}

		possibilitiesList.addAll(enPassantPossibilities(domain, from));

		return possibilitiesList;
	}

	private ArrayList<Point> enPassantPossibilities(Board domain, Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		final int sense = pieceColor.getValue();
		if((pieceColor == Color.WHITE && from.y == 3) || (pieceColor == Color.BLACK && from.y == 4)) {
			Point p = (Point)from.clone();
			p.x = from.x - 1;
			if(p.x >= 0 && !domain.squareIsVacant(p) && domain.getPieceAt(p) instanceof Pawn 
					&& domain.getPieceAt(p).getColor() != pieceColor && ((Pawn)domain.getPieceAt(p)).justPassed()) {
				possibilitiesList.add(new Point(p.x, from.y + 1 * sense));
			}
			p.x = from.x + 1;
			if(p.x < Board.dimension && !domain.squareIsVacant(p) && domain.getPieceAt(p) instanceof Pawn 
					&& domain.getPieceAt(p).getColor() != pieceColor && ((Pawn)domain.getPieceAt(p)).justPassed()) {
				possibilitiesList.add(new Point(p.x, from.y + 1 * sense));
			}
		}

		return possibilitiesList;
	}

	public void move(Board domain, Point from, Point to) {
		final int sense = pieceColor.getValue();
		moved = true;
		if((pieceColor == Color.WHITE && to.y == 0) || (pieceColor == Color.BLACK && to.y == Board.dimension - 1)) {
			domain.setPromotePawn(this);
		}
		// Verifica se é um movimento de En Passant
		else if(to.x != from.x && domain.squareIsVacant(to)) {
			domain.clearPosition(new Point(to.x, to.y - 1 * sense));
		}
		super.move(domain, from, to);
		passed = to.y == from.y + 2 * sense ? true : false;
	}
	
}
