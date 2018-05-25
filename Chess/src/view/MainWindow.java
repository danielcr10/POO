package view;

import java.awt.Dimension;
import javax.swing.*;

public class MainWindow {
	
	JFrame frame;
	
	BoardView boardView;
	
	public MainWindow(BoardView boardView) {
		frame = new JFrame("Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.boardView = boardView;
		frame.setContentPane(boardView);
		adjustSize();
		frame.setVisible(true);
	}
	
	private void adjustSize() {
		final int widthMargin = 0;
		final int heightMargin = 22;
		
		final Dimension contentPaneSize = frame.getContentPane().getSize();	
		frame.setSize(contentPaneSize.width + widthMargin, contentPaneSize.height + heightMargin);
	}
}
