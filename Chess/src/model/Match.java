package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

@SuppressWarnings("serial")
class InvalidMoveException extends Exception {

	public InvalidMoveException(String message) {
		super(message);
	}

}

@SuppressWarnings("serial")
class InvalidPromotionException extends Exception {

	public InvalidPromotionException(String message) {
		super(message);
	}

}

public class Match implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final transient PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private Board matchBoard = new Board();

	private Color currentPlayer = Color.WHITE;

	public void movePieceFromTo(Point from, Point to) {
		try {
			final Piece pieceAtPosition = matchBoard.getPieceAt(from);
			if(pieceAtPosition == null || !pieceAtPosition.validMovePossibilities().contains(to)) {
				throw new InvalidMoveException("Invalid Move Exception");
			}
			final String[][] boardBefore = getBoardState();
			pieceAtPosition.move(to);
			final String[][] boardAfter = getBoardState();
			pcs.firePropertyChange("board", boardBefore, boardAfter);
		} catch(InvalidMoveException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		// TODO: Melhorar a forma como fazemos essa conversão.
		currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
	}

	public void promotePawnAt(Point position, String piece) {
		try {
			final Piece pieceAtPosition = matchBoard.getPieceAt(position);
			if(!(pieceAtPosition instanceof Pawn) || !(((Pawn)pieceAtPosition).canBePromoted())) {
				throw new InvalidPromotionException("Invalid Promotion Exception");
			}
			final String[][] boardBefore = getBoardState();
			matchBoard.setPieceAt(createPieceFromString(piece, pieceAtPosition.getColor()), position);
			final String[][] boardAfter = getBoardState();
			pcs.firePropertyChange("board", boardBefore, boardAfter);
		} catch(InvalidPromotionException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public ArrayList<Point> getMovePossibilities(Point p) {
		ArrayList<Point> possibilities; 

		if(!matchBoard.squareIsVacant(p)) {
			final Piece pieceAtPosition = matchBoard.getPieceAt(p);	
			possibilities = pieceAtPosition.validMovePossibilities();
		}
		else {
			possibilities = new ArrayList<Point>();
		}

		return possibilities;
	}

	public String[][] getBoardState() {
		String[][] boardAsString = new String[Board.dimension][Board.dimension];
		for(int i = 0; i < boardAsString.length; i++) {
			for(int j = 0; j < boardAsString.length; j++) {
				final Piece piece = matchBoard.getPieceAt(new Point(j, i));
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

	public Color getPieceColorAt(Point p) {
		Piece pieceAtPosition = matchBoard.getPieceAt(p);
		return pieceAtPosition.getColor();
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean currentPlayerIsInCheck() {
		final PieceSet set = matchBoard.getPieceSet(currentPlayer);
		final King currentPlayerKing = set.getKing();

		return currentPlayerKing.isInCheck();
	}

	public boolean currentPlayerIsInCheckmate() {
		final PieceSet set = matchBoard.getPieceSet(currentPlayer);
		final King currentPlayerKing = set.getKing();
		if(!currentPlayerKing.isInCheck()) {
			return false;
		}

		for(Piece piece : set.getAll()) {
			if(piece.validMovePossibilities().size() > 0) {
				return false;
			}
		}

		return true;
	}

	public Point getCurrentPlayerKingPosition() {
		final PieceSet set = matchBoard.getPieceSet(currentPlayer);
		final King currentPlayerKing = set.getKing();

		return currentPlayerKing.getCurrentPosition();
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	private Piece createPieceFromString(String pieceName, Color color) {
		Piece piece = null;
		if(pieceName.contains("Rook")) {
			piece = new Rook(matchBoard, color);
		}
		else if(pieceName.contains("Knight")) {
			piece = new Knight(matchBoard, color);
		}
		else if(pieceName.contains("Bishop")) {
			piece = new Bishop(matchBoard, color);
		}
		else if(pieceName.contains("Queen")) {
			piece = new Queen(matchBoard, color);
		}

		return piece;
	}

}
