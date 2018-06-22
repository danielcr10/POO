package controller;

import java.util.ArrayList;
import java.awt.Point;
import model.Match;
import model.Pawn;

@SuppressWarnings("serial")
class PlayerPermissionException extends Exception {

	PlayerPermissionException(String message) {
		super(message);
	}

}

public class ChessController {

	private Match chessMatch;
		
	public ChessController(Match chessMatch) {
		this.chessMatch = chessMatch;
	}

	public String[][] getBoard() {
		return chessMatch.getBoardState();
	}

	public ArrayList<Point> getMovePossibilities(Point p) {
		return chessMatch.getMovePossibilities(p);
	}

	public String[] getPawnPromotionPossibilities() {
		return Pawn.getPromotionPossibilities();
	}

	public boolean playerHasPermission(Point p) {
		return chessMatch.getPieceColorAt(p) == chessMatch.getCurrentPlayer();
	}

	public boolean currentPlayerIsInCheck() {
		return chessMatch.currentPlayerIsInCheck();
	}

	public Point requestCurrentPlayerKingPosition() {
		return chessMatch.getCurrentPlayerKingPosition();
	}

	public void requestPieceMove(Point from, Point to) {
		try {
			if(!playerHasPermission(from)) {
				final String message = "PlayerPermissionException: This player cannot make a move now. It's the opponent's turn.";
				throw new PlayerPermissionException(message);
			}
			chessMatch.movePieceFromTo(from, to);
		} catch(PlayerPermissionException e) {
			System.out.println(e.getMessage());
		}

	}

	public void requestPawnPromotionAt(Point position, String piece) {
		chessMatch.promotePawnAt(position, piece);
	}

}
