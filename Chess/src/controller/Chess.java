package controller;

import javax.swing.SwingUtilities;
import view.MainWindow;

class Application implements Runnable {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Application());
	}
	
	public void run() {
		new MainWindow();
	}
}
