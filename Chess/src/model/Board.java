package model;

public class Board {
	
	static private final int dimension = 8;
	
	private Piece[][] pieces = new Piece[dimension][dimension];
	
	public Board() {
		pieces[6][0] = new Pawn(Color.BLACK);
		pieces[6][1] = new Pawn(Color.BLACK);
		pieces[6][2] = new Pawn(Color.BLACK);
		pieces[6][3] = new Pawn(Color.BLACK);
		pieces[6][4] = new Pawn(Color.BLACK);
		pieces[6][5] = new Pawn(Color.BLACK);
		pieces[6][6] = new Pawn(Color.BLACK);
		pieces[6][7] = new Pawn(Color.BLACK);
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
