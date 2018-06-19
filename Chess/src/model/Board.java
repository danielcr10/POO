package model;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Point;

class Board {
	
	static public final int dimension = 8;

	private Piece[][] board = new Piece[dimension][dimension];

	private HashMap<Color, PieceSet> pieceSet = new HashMap<Color, PieceSet>();

	public Board() {
		pieceSet.put(Color.WHITE, new PieceSet(this, Color.WHITE));
		pieceSet.put(Color.BLACK, new PieceSet(this, Color.BLACK));
		arrangeBoard();
	}

	private void arrangeBoard() {
		final PieceSet blackPieceSet = pieceSet.get(Color.BLACK);
		final PieceSet whitePieceSet = pieceSet.get(Color.WHITE);
		for (int k=0; k<8; k++) {
			setPieceAt(blackPieceSet.getPawns().get(k), new Point(k, 1));
			setPieceAt(whitePieceSet.getPawns().get(k), new Point(k, 6));
		}

		setPieceAt(blackPieceSet.getRooks().get(0), new Point(7, 0));
		setPieceAt(blackPieceSet.getKnights().get(0), new Point(6, 0));
		setPieceAt(blackPieceSet.getBishops().get(0), new Point(5, 0));
		setPieceAt(blackPieceSet.getKing(), new Point(4, 0));
		setPieceAt(blackPieceSet.getQueens().get(0), new Point(3, 0));
		setPieceAt(blackPieceSet.getBishops().get(1), new Point(2, 0));
		setPieceAt(blackPieceSet.getKnights().get(1), new Point(1, 0));
		setPieceAt(blackPieceSet.getRooks().get(1), new Point(0, 0));

		setPieceAt(whitePieceSet.getRooks().get(0), new Point(7, 7));
		setPieceAt(whitePieceSet.getKnights().get(0), new Point(6, 7));
		setPieceAt(whitePieceSet.getBishops().get(0), new Point(5, 7));
		setPieceAt(whitePieceSet.getKing(), new Point(4, 7));
		setPieceAt(whitePieceSet.getQueens().get(0), new Point(3, 7));
		setPieceAt(whitePieceSet.getBishops().get(1), new Point(2, 7));
		setPieceAt(whitePieceSet.getKnights().get(1), new Point(1, 7));
		setPieceAt(whitePieceSet.getRooks().get(1), new Point(0, 7));
	}

	public void setPawnsPassedStatus(Color color, boolean status) {
		final PieceSet set = getPieceSet(color);
		final ArrayList<Pawn> pawnsList = set.getPawns();
		for(Pawn piece : pawnsList) {
			piece.setPassed(status);
		}
	}

	public boolean squareIsVacant(Point position) {
		return board[position.y][position.x] == null;
	}

	public void setPieceAt(Piece piece, Point position) {
		piece.setCurrentPosition(position);
		board[position.y][position.x] = piece;
	}

	public void clearPosition(Point position) {
		board[position.y][position.x] = null;
	}
	
	public Piece getPieceAt(Point position) {
		return board[position.y][position.x];
	}

	public PieceSet getPieceSet(Color color) {
		return pieceSet.get(color);
	}

	public boolean contains(Point position) {
		return position.x >= 0 && position.x < Board.dimension && position.y >= 0 && position.y < Board.dimension;
	}

	public boolean pieceIsVulnerableAt(Piece piece, Point position) {
		boolean isVunerable = false;
		final Piece livingPiece = getPieceAt(position);
		setPieceAt(piece, position);

		for(int i = 0; i < Board.dimension; i++) {
			for(int j = 0; j < Board.dimension; j++) {
				final Point p = new Point(j, i);
				final Piece pieceAtPosition = getPieceAt(p);
				if(pieceAtPosition != null && pieceAtPosition.getColor() != piece.getColor() && pieceAtPosition.attackPossibilities().contains(position)) {
					isVunerable = true;
					break;
				}
			}
		}

		setPieceAt(livingPiece, position);

		return isVunerable;
	}

}
