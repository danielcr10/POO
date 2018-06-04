package controller;

import java.awt.Point;
import model.Match;
import view.BoardView;

public class ChessController {

	private Match chessMatch;
		
	public ChessController(Match chessMatch) {
		this.chessMatch = chessMatch;
	}

	public String[][] getBoard() {
		return chessMatch.getBoard();
	}

	public Point[] getMovePossibilities(Point p) {
		return chessMatch.getMovePossibilities(p);
	}

}
