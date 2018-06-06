package controller;

import java.util.ArrayList;
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

	public ArrayList<Point> getMovePossibilities(Point p) {
		return chessMatch.getMovePossibilities(p);
	}

	public void requestPieceMove(Point from, Point to) {
		chessMatch.movePieceFromTo(from, to);
	}

}
