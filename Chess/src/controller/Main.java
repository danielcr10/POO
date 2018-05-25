package controller;

import javax.swing.SwingUtilities;

import model.Board;
import view.BoardView;
import view.MainWindow;

class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Chess());
	}
}

class Chess implements Runnable {
	
	Board matchBoard = new Board();
	
	public void run() {
		BoardView boardView = new BoardView(matchBoard.currentState());
		new MainWindow(boardView);
	}
}
