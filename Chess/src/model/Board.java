package model;

import java.awt.Point;

@SuppressWarnings("serial")
class InvalidMoveException extends Exception {

	public InvalidMoveException(String message) {
		super(message);
	}

}

public class Board {
	
	static public final int dimension = 8;

	private Piece[][] pieces = new Piece[dimension][dimension];
	
	public Board() {
		for (int k=0; k<8; k++) {
			pieces[1][k] = new Pawn(Color.BLACK);
			pieces[6][k] = new Pawn(Color.WHITE);
		}
		pieces[0][7] = new Rook(Color.BLACK);
		pieces[0][6] = new Knight(Color.BLACK);
		pieces[0][5] = new Bishop(Color.BLACK);
		pieces[0][4] = new King(Color.BLACK);
		pieces[0][3] = new Queen(Color.BLACK);
		pieces[0][2] = new Bishop(Color.BLACK);
		pieces[0][1] = new Knight(Color.BLACK);
		pieces[0][0] = new Rook(Color.BLACK);
			
		pieces[7][7] = new Rook(Color.WHITE);
		pieces[7][6] = new Knight(Color.WHITE);
		pieces[7][5] = new Bishop(Color.WHITE);
		pieces[7][4] = new King(Color.WHITE);
		pieces[7][3] = new Queen(Color.WHITE);
		pieces[7][2] = new Bishop(Color.WHITE);
		pieces[7][1] = new Knight(Color.WHITE);
		pieces[7][0] = new Rook(Color.WHITE);	
	}

	public boolean squareIsVacant(Point position) {
		return pieces[position.y][position.x] == null;
	}

	public void clearPosition(Point position) {
		pieces[position.y][position.x] = null;
	}
	
	public Piece getPieceAt(Point position) {
		return pieces[position.y][position.x];
	}

	public void addPieceAt(Piece piece, Point position) {
		pieces[position.y][position.x] = piece;
	}
	
	public boolean contains(Point position) {
		return position.x >= 0 && position.x < Board.dimension && position.y >= 0 && position.y < Board.dimension;
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
