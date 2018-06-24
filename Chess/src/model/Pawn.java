package model;

import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends KingdomProtector {

	private static final Class<?>[] canPromoteTo = {Rook.class, Knight.class, Bishop.class, Queen.class};

	private static final long serialVersionUID = 1L;

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

	public ArrayList<Point> movePossibilities() {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		final int sense = pieceColor.getValue();
		Point p = (Point)currentPosition.clone();
		p.y = currentPosition.y + 1 * sense;
		if(p.y >= 0 && p.y < Board.dimension && pieceBoard.squareIsVacant(p)) {
			possibilitiesList.add((Point)p.clone());
			if(!moved) {
				p.y = currentPosition.y + 2 * sense;
				if(pieceBoard.squareIsVacant(p)) {
					possibilitiesList.add((Point)p.clone());
				}
			}
		}

		possibilitiesList.addAll(attackPossibilities());

		return possibilitiesList;
	}

	public ArrayList<Point> attackPossibilities() {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		final int sense = pieceColor.getValue();
		Point p = (Point)currentPosition.clone();
		p.y = currentPosition.y + 1 * sense;
		if(p.y >= 0 && p.y < Board.dimension) {
			p.x -= 1;
			if(p.x >= 0 && !pieceBoard.squareIsVacant(p)) {
				final Piece piece = pieceBoard.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
			}

			p.x = currentPosition.x + 1;
			if(p.x < Board.dimension && !pieceBoard.squareIsVacant(p)) {
				final Piece piece = pieceBoard.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
			}
		}

		possibilitiesList.addAll(enPassantPossibilities());

		return possibilitiesList;
	}

	private ArrayList<Point> enPassantPossibilities() {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		final int sense = pieceColor.getValue();
		if((pieceColor == Color.WHITE && currentPosition.y == 3) || (pieceColor == Color.BLACK && currentPosition.y == 4)) {
			Point p = (Point)currentPosition.clone();
			p.x = currentPosition.x - 1;
			if(p.x >= 0 && !pieceBoard.squareIsVacant(p) && pieceBoard.getPieceAt(p) instanceof Pawn 
					&& pieceBoard.getPieceAt(p).getColor() != pieceColor && ((Pawn)pieceBoard.getPieceAt(p)).justPassed()) {
				possibilitiesList.add(new Point(p.x, currentPosition.y + 1 * sense));
			}
			p.x = currentPosition.x + 1;
			if(p.x < Board.dimension && !pieceBoard.squareIsVacant(p) && pieceBoard.getPieceAt(p) instanceof Pawn 
					&& pieceBoard.getPieceAt(p).getColor() != pieceColor && ((Pawn)pieceBoard.getPieceAt(p)).justPassed()) {
				possibilitiesList.add(new Point(p.x, currentPosition.y + 1 * sense));
			}
		}

		return possibilitiesList;
	}

	public void move(Point to) {
		final int sense = pieceColor.getValue();
		moved = true;
		if((pieceColor == Color.WHITE && to.y == 0) || (pieceColor == Color.BLACK && to.y == Board.dimension - 1)) {
			promoted = true;
		}
		// Verifica se é um movimento de En Passant
		else if(to.x != currentPosition.x && pieceBoard.squareIsVacant(to)) {
			final Point p = new Point(to.x, to.y - 1 * sense);
			final KingdomProtector pieceAtPosition = (KingdomProtector)pieceBoard.getPieceAt(p);
			pieceAtPosition.die();
			pieceBoard.clearPosition(p);
		}
		final Point lastPosition = currentPosition;
		super.move(to);
		passed = currentPosition.y == lastPosition.y + 2 * sense ? true : false;
	}

	public static String[] getPromotionPossibilities() {
		final ArrayList<String> possibilities = new ArrayList<String>();
		for(Class<?> c : canPromoteTo) {
			possibilities.add(c.getSimpleName());
		}

		return possibilities.toArray(new String[possibilities.size()]);
	}

	public void die() {
		final PieceSet set = pieceBoard.getPieceSet(pieceColor);
		set.removePawn(this);
	}
	
}
