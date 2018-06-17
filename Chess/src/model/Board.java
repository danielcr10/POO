package model;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Point;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

@SuppressWarnings("serial")
class InvalidMoveException extends Exception {

	public InvalidMoveException(String message) {
		super(message);
	}

}

class Board {
	
	static public final int dimension = 8;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private Piece[][] board = new Piece[dimension][dimension];

	private HashMap<Color, ArrayList<Pawn>> pawns = new HashMap<Color, ArrayList<Pawn>>();

	private Pawn promotePawn;

	private Point promotePawnPosition;
	
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

	public void setPromotePawnPosition(Pawn piece, Point position) {
		promotePawn = piece;
		final Point lastPosition = promotePawnPosition;
		promotePawnPosition = position;
		pcs.firePropertyChange("promotePawnPosition", lastPosition, promotePawnPosition);
	}

	public void clearPosition(Point position) {
		board[position.y][position.x] = null;
	}
	
	public Piece getPieceAt(Point position) {
		return board[position.y][position.x];
	}

	private Point getPromotePawnPosition() {
		return promotePawnPosition;
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
			final String[][] boardBefore = getBoardState();
			pieceAtPosition.move(this, from, to);
			final String[][] boardAfter = getBoardState();
			pcs.firePropertyChange("board", boardBefore, boardAfter);
		} catch(InvalidMoveException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public void promotePawnTo(String piece) {
		final String[][] boardBefore = getBoardState();
		setPieceAt(createPieceFromString(piece, promotePawn.getColor()), getPromotePawnPosition());
		final String[][] boardAfter = getBoardState();
		pcs.firePropertyChange("board", boardBefore, boardAfter);
		promotePawn = null;
		promotePawnPosition = null;
	}

	public String[][] getBoardState() {
		String[][] boardAsString = new String[Board.dimension][Board.dimension];
		for(int i = 0; i < boardAsString.length; i++) {
			for(int j = 0; j < boardAsString.length; j++) {
				final Piece piece = getPieceAt(new Point(j, i));
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

	private Piece createPieceFromString(String pieceName, Color color) {
		Piece piece = null;
		if(pieceName.contains("Rook")) {
			piece = new Rook(color);
		}
		else if(pieceName.contains("Knight")) {
			piece = new Knight(color);
		}
		else if(pieceName.contains("Bishop")) {
			piece = new Bishop(color);
		}
		else if(pieceName.contains("Queen")) {
			piece = new Queen(color);
		}

		return piece;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}
}
