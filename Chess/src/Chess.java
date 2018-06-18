import javax.swing.SwingUtilities;

import model.Match;
import view.BoardView;
import view.MainWindow;
import controller.ChessController;


class Chess implements Runnable {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Chess());
	}
	
	public void run() {
		Match chessMatch = new Match();
		ChessController controller = new ChessController(chessMatch);
		BoardView boardView = new BoardView(controller);
		chessMatch.addPropertyChangeListener(boardView);
		new MainWindow(boardView);
	}
}
