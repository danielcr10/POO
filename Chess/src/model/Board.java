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
			board[1][k] = blackPieceSet.getPawns().get(k);
			board[6][k] = whitePieceSet.getPawns().get(k);
		}
		board[0][7] = blackPieceSet.getRooks().get(0);
		board[0][6] = blackPieceSet.getKnights().get(0);
		board[0][5] = blackPieceSet.getBishops().get(0);
		board[0][4] = blackPieceSet.getKing();
		board[0][3] = blackPieceSet.getQueens().get(0);
		board[0][2] = blackPieceSet.getBishops().get(1);
		board[0][1] = blackPieceSet.getKnights().get(1);
		board[0][0] = blackPieceSet.getRooks().get(1);
			
		board[7][7] = whitePieceSet.getRooks().get(0);
		board[7][6] = whitePieceSet.getKnights().get(0);
		board[7][5] = whitePieceSet.getBishops().get(0);
		board[7][4] = whitePieceSet.getKing();
		board[7][3] = whitePieceSet.getQueens().get(0);
		board[7][2] = whitePieceSet.getBishops().get(1);
		board[7][1] = whitePieceSet.getKnights().get(1);
		board[7][0] = whitePieceSet.getRooks().get(1);
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
				if(pieceAtPosition != null && pieceAtPosition.getColor() != piece.getColor() && pieceAtPosition.attackPossibilities(p).contains(position)) {
					isVunerable = true;
					break;
				}
			}
		}

		setPieceAt(livingPiece, position);

		return isVunerable;
	}

}
