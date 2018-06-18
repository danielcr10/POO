package model;

import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends Piece {

	private static final Class[] canPromoteTo = {Rook.class, Knight.class, Bishop.class, Queen.class};

	private boolean moved = false;

	private boolean passed = false;

	private boolean promoted = false;

	public Pawn(Board board, Color color) {
		super(board, color);
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

	public boolean canBePromoted() {
		return promoted;
	}

	public ArrayList<Point> movePossibilities(Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		final int sense = pieceColor.getValue();
		Point p = (Point)from.clone();
		if(!moved) {
			p.y = from.y + 2 * sense;
			if(pieceBoard.squareIsVacant(p)) {
				possibilitiesList.add((Point)p.clone());
			}
		}

		p.y = from.y + 1 * sense;
		if(p.y >= 0 && p.y < Board.dimension && pieceBoard.squareIsVacant(p)) {
			possibilitiesList.add((Point)p.clone());
		}

		possibilitiesList.addAll(attackPossibilities(from));

		return possibilitiesList;
	}

	public ArrayList<Point> attackPossibilities(Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		final int sense = pieceColor.getValue();
		Point p = (Point)from.clone();
		p.y = from.y + 1 * sense;
		if(p.y >= 0 && p.y < Board.dimension) {
			p.x -= 1;
			if(p.x >= 0 && !pieceBoard.squareIsVacant(p)) {
				final Piece piece = pieceBoard.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
			}

			p.x = from.x + 1;
			if(p.x < Board.dimension && !pieceBoard.squareIsVacant(p)) {
				final Piece piece = pieceBoard.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
			}
		}

		possibilitiesList.addAll(enPassantPossibilities(from));

		return possibilitiesList;
	}

	private ArrayList<Point> enPassantPossibilities(Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		final int sense = pieceColor.getValue();
		if((pieceColor == Color.WHITE && from.y == 3) || (pieceColor == Color.BLACK && from.y == 4)) {
			Point p = (Point)from.clone();
			p.x = from.x - 1;
			if(p.x >= 0 && !pieceBoard.squareIsVacant(p) && pieceBoard.getPieceAt(p) instanceof Pawn 
					&& pieceBoard.getPieceAt(p).getColor() != pieceColor && ((Pawn)pieceBoard.getPieceAt(p)).justPassed()) {
				possibilitiesList.add(new Point(p.x, from.y + 1 * sense));
			}
			p.x = from.x + 1;
			if(p.x < Board.dimension && !pieceBoard.squareIsVacant(p) && pieceBoard.getPieceAt(p) instanceof Pawn 
					&& pieceBoard.getPieceAt(p).getColor() != pieceColor && ((Pawn)pieceBoard.getPieceAt(p)).justPassed()) {
				possibilitiesList.add(new Point(p.x, from.y + 1 * sense));
			}
		}

		return possibilitiesList;
	}

	public void move(Point from, Point to) {
		final int sense = pieceColor.getValue();
		moved = true;
		if((pieceColor == Color.WHITE && to.y == 0) || (pieceColor == Color.BLACK && to.y == Board.dimension - 1)) {
			promoted = true;
		}
		// Verifica se é um movimento de En Passant
		else if(to.x != from.x && pieceBoard.squareIsVacant(to)) {
			pieceBoard.clearPosition(new Point(to.x, to.y - 1 * sense));
		}
		super.move(from, to);
		passed = to.y == from.y + 2 * sense ? true : false;
	}

	public static String[] getPromotionPossibilities() {
		final ArrayList<String> possibilities = new ArrayList<String>();
		for(Class c : canPromoteTo) {
			possibilities.add(c.getSimpleName());
		}

		return possibilities.toArray(new String[possibilities.size()]);
	}
	
}
