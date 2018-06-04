import javax.swing.SwingUtilities;

import model.Match;
import view.BoardView;
import view.MainWindow;


class Chess implements Runnable {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Chess());
	}
	
	public void run() {
		Match chessMatch = new Match();
		BoardView boardView = new BoardView(chessMatch.getBoard());
		chessMatch.addPropertyChangeListener(boardView);
		new MainWindow(boardView);
	}
}
