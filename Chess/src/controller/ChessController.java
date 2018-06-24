package controller;

import java.util.ArrayList;
import java.awt.Point;
import model.Match;
import model.Pawn;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.beans.PropertyChangeListener;

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

	public void addModelListener(PropertyChangeListener listener) {
		chessMatch.addPropertyChangeListener(listener);
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

	public void saveGame() {
		try {
			FileOutputStream fos = new FileOutputStream("matches" + File.separator + "save.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(chessMatch);
			oos.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void continueGame(String path) {
		try {
			FileInputStream file = new FileInputStream(path);
			ObjectInputStream object = new ObjectInputStream(file);
			chessMatch = (Match) object.readObject();
			object.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String requestMatchStatus() {
		return chessMatch.getMatchStatus();
	}

}
