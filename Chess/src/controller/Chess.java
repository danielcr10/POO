package controller;

import javax.swing.SwingUtilities;

import model.Board;
import view.BoardView;
import view.MainWindow;


class Chess implements Runnable {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Chess());
	}
	
	public void run() {
		Board matchBoard = new Board();
		BoardView boardView = new BoardView(matchBoard.currentState());
		new MainWindow(boardView);
	}
}
