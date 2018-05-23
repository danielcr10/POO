package view;

import javax.swing.*;

public class MainWindow {
	JFrame frame;
	
	public MainWindow() {
		frame = new JFrame("window title");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(210, 250);
		frame.setVisible(true);
	}
}
