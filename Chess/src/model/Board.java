package model;

import java.awt.Point;

public class Board {
	
	static public final int dimension = 8;
	
	private Piece[][] pieces = new Piece[dimension][dimension];
	
	public Board() {
		for (int k=0; k<8; k++) {
			pieces[1][k] = new Pawn(Color.BLACK);
			pieces[6][k] = new Pawn(Color.WHITE);
			switch(k)
			{
			case 0: pieces[0][k] = new Rook(Color.BLACK);
					pieces[7][k] = new Rook(Color.WHITE);
			break;
			
			case 1: pieces[0][k] = new Knight(Color.BLACK);
					pieces[7][k] = new Knight(Color.WHITE);
			break;
			
			case 2: pieces[0][k] = new Bishop(Color.BLACK);
					pieces[7][k] = new Bishop(Color.WHITE);
			break;
			
			case 3: pieces[0][k] = new Queen(Color.BLACK);
					pieces[7][k] = new Queen(Color.WHITE);
			break;
			
			case 4: pieces[0][k] = new King(Color.BLACK);
					pieces[7][k] = new King(Color.WHITE);
			break;
			
			case 5: pieces[0][k] = new Bishop(Color.BLACK);
					pieces[7][k] = new Bishop(Color.WHITE);
			break;
			
			case 6: pieces[0][k] = new Knight(Color.BLACK);
					pieces[7][k] = new Knight(Color.WHITE);
			break;
			
			case 7: pieces[0][k] = new Rook(Color.BLACK);
					pieces[7][k] = new Rook(Color.WHITE);
			break;
		
			}
		}	
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
