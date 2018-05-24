package controller;

import javax.swing.SwingUtilities;
import view.MainWindow;

class Chess implements Runnable {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Chess());
	}
	
	public void run() {
		new MainWindow();
	}
}
