package model;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Point;
import java.io.Serializable;

@SuppressWarnings("serial")
class Board implements Serializable {

	static public final int dimension = 8;

	private Piece[][] board = new Piece[dimension][dimension];

	private HashMap<Color, PieceSet> pieceSet = new HashMap<Color, PieceSet>();

	public Board() {
		final PieceSet blackPieces = new PieceSet(this, Color.BLACK);
		final PieceSet whitePieces = new PieceSet(this, Color.WHITE);
		pieceSet.put(Color.BLACK, blackPieces);
		pieceSet.put(Color.WHITE, whitePieces);
		arrangeBoard();
	}

	private void arrangeBoard() {
		final PieceSet blackPieceSet = pieceSet.get(Color.BLACK);
		final PieceSet whitePieceSet = pieceSet.get(Color.WHITE);
		final ArrayList<Pawn> blackPawns = blackPieceSet.getPawns();
		final ArrayList<Pawn> whitePawns = whitePieceSet.getPawns();
		final ArrayList<Rook> blackRooks = blackPieceSet.getRooks();
		final ArrayList<Rook> whiteRooks = whitePieceSet.getRooks();
		final ArrayList<Knight> blackKnights = blackPieceSet.getKnights();
		final ArrayList<Knight> whiteKnights = whitePieceSet.getKnights();
		final ArrayList<Bishop> blackBishops = blackPieceSet.getBishops();
		final ArrayList<Bishop> whiteBishops = whitePieceSet.getBishops();
		final King blackKing = blackPieceSet.getKing();
		final King whiteKing = whitePieceSet.getKing();
		final Queen blackQueen = blackPieceSet.getQueens().get(0);
		final Queen whiteQueen = whitePieceSet.getQueens().get(0);

		for (int k=0; k<8; k++) {
			setPieceAt(blackPawns.get(k), new Point(k, 1));
			setPieceAt(whitePawns.get(k), new Point(k, 6));
		}

		setPieceAt(blackRooks.get(0), new Point(7, 0));
		setPieceAt(blackKnights.get(0), new Point(6, 0));
		setPieceAt(blackBishops.get(0), new Point(5, 0));
		setPieceAt(blackKing, new Point(4, 0));
		setPieceAt(blackQueen, new Point(3, 0));
		setPieceAt(blackBishops.get(1), new Point(2, 0));
		setPieceAt(blackKnights.get(1), new Point(1, 0));
		setPieceAt(blackRooks.get(1), new Point(0, 0));

		setPieceAt(whiteRooks.get(0), new Point(7, 7));
		setPieceAt(whiteKnights.get(0), new Point(6, 7));
		setPieceAt(whiteBishops.get(0), new Point(5, 7));
		setPieceAt(whiteKing, new Point(4, 7));
		setPieceAt(whiteQueen, new Point(3, 7));
		setPieceAt(whiteBishops.get(1), new Point(2, 7));
		setPieceAt(whiteKnights.get(1), new Point(1, 7));
		setPieceAt(whiteRooks.get(1), new Point(0, 7));
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
		final Point piecePosition = piece.getCurrentPosition();
		clearPosition(piecePosition);
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

		clearPosition(position);
		if(livingPiece != null) {
			setPieceAt(livingPiece, position);
		}

		setPieceAt(piece, piecePosition);

		return isVunerable;
	}

}
