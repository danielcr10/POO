import javax.swing.SwingUtilities;

import model.Match;
import view.MainWindow;
import controller.ChessController;


class Chess implements Runnable {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Chess());
	}
	
	public void run() {
		final Match chessMatch = new Match();
		final ChessController controller = new ChessController(chessMatch);
		final MainWindow mainWindow = new MainWindow(controller);
		chessMatch.addPropertyChangeListener(mainWindow.getBoardView());
	}
}
