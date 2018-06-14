package model;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Point;

@SuppressWarnings("serial")
class InvalidMoveException extends Exception {

	public InvalidMoveException(String message) {
		super(message);
	}

}

public class Board {
	
	static public final int dimension = 8;

	private Piece[][] board = new Piece[dimension][dimension];

	private HashMap<Color, ArrayList<Pawn>> pawns = new HashMap<Color, ArrayList<Pawn>>();

	private Pawn promotePawn;
	
	public Board() {
		final ArrayList<Pawn> blackPawns = new ArrayList<>();
		final ArrayList<Pawn> whitePawns = new ArrayList<>();
		for(int k = 0; k < Board.dimension; k++) {
			blackPawns.add(new Pawn(Color.BLACK));
			whitePawns.add(new Pawn(Color.WHITE));
		}
		pawns.put(Color.BLACK, blackPawns);
		pawns.put(Color.WHITE, whitePawns);
		arrangeBoard();
	}

	private void arrangeBoard() {
		final ArrayList<Pawn> blackPawns = pawns.get(Color.BLACK);
		final ArrayList<Pawn> whitePawns = pawns.get(Color.WHITE);

		for (int k=0; k<8; k++) {
			board[1][k] = blackPawns.get(k);
			board[6][k] = whitePawns.get(k);
		}
		board[0][7] = new Rook(Color.BLACK);
		board[0][6] = new Knight(Color.BLACK);
		board[0][5] = new Bishop(Color.BLACK);
		board[0][4] = new King(Color.BLACK);
		board[0][3] = new Queen(Color.BLACK);
		board[0][2] = new Bishop(Color.BLACK);
		board[0][1] = new Knight(Color.BLACK);
		board[0][0] = new Rook(Color.BLACK);
			
		board[7][7] = new Rook(Color.WHITE);
		board[7][6] = new Knight(Color.WHITE);
		board[7][5] = new Bishop(Color.WHITE);
		board[7][4] = new King(Color.WHITE);
		board[7][3] = new Queen(Color.WHITE);
		board[7][2] = new Bishop(Color.WHITE);
		board[7][1] = new Knight(Color.WHITE);
		board[7][0] = new Rook(Color.WHITE);	
	}

	public void setPawnsPassedStatus(Color color, boolean status) {
		final ArrayList<Pawn> pawnsList = pawns.get(color);
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

	public void setPromotePawn(Pawn piece) {
		promotePawn = piece;
		System.out.println("promote pawn set!");
	}

	public void clearPosition(Point position) {
		board[position.y][position.x] = null;
	}
	
	public Piece getPieceAt(Point position) {
		return board[position.y][position.x];
	}

	public void addPieceAt(Piece piece, Point position) {
		board[position.y][position.x] = piece;
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
				if(pieceAtPosition != null && pieceAtPosition.getColor() != piece.getColor() && pieceAtPosition.attackPossibilities(this, p).contains(position)) {
					isVunerable = true;
					break;
				}
			}
		}

		setPieceAt(livingPiece, position);

		return isVunerable;
	}

	public void movePieceFromTo(Point from, Point to) {
		try {
			Piece pieceAtPosition = getPieceAt(from);
			if(pieceAtPosition == null || !pieceAtPosition.movePossibilities(this, from).contains(to)) {
				throw new InvalidMoveException("Invalid Move Exception");
			}
			pieceAtPosition.move(this, from, to);
		} catch(InvalidMoveException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

}
