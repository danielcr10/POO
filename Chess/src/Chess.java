import javax.swing.SwingUtilities;

import model.Match;
import view.MainWindow;
import controller.ChessController;


class Chess implements Runnable {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Chess());
	}
	
	public void run() {
		final ChessController controller = new ChessController();
		final MainWindow mainWindow = new MainWindow(controller);
	}
}
