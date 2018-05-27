package model;

import java.awt.Point;

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
		return pieces[position.x][position.y] == null;
	}
	
	public Piece getPieceAt(Point position) {
		return pieces[position.x][position.y];
	}
	
	public String[][] currentState() {
		String[][] boardAsString = new String[dimension][dimension];
		for(int i = 0; i < boardAsString.length; i++) {
			for(int j = 0; j < boardAsString.length; j++) {
				final Piece piece = pieces[i][j];
				if(piece != null) {
					boardAsString[i][j] = piece.getClass().getSimpleName() + piece.getColor().toString();
				}
				else {
					boardAsString[i][j] = "";
				}
				
			}
		}
		
		return boardAsString;
	}
}
