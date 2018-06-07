package controller;

import java.util.ArrayList;
import java.awt.Point;
import model.Match;
import view.BoardView;

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
		return chessMatch.getBoard();
	}

	public ArrayList<Point> getMovePossibilities(Point p) {
		return chessMatch.getMovePossibilities(p);
	}

	public boolean playerHasPermission(Point p) {
		return chessMatch.getPieceColorAt(p) == chessMatch.getCurrentPlayer();
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

}
