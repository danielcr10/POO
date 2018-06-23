package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

import controller.ChessController;


public class MainWindow implements ActionListener {
	
	private JFrame frame;

	private InitialView initialView;

	private BoardView boardView;
	
	public MainWindow(ChessController controller) {
		frame = new JFrame("Chess");
		initialView = new InitialView(this);
		boardView = new BoardView(controller);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JPanel cards = new JPanel(new CardLayout());
		cards.add(initialView, 0);
		cards.add(boardView, 1);
		final CardLayout layout = (CardLayout)cards.getLayout();
		layout.first(cards);
		adjustSize(initialView);
		frame.setContentPane(cards);
		frame.setVisible(true);
	}

	public BoardView getBoardView() {
		return boardView;
	}

	public void actionPerformed(ActionEvent e) {
		final JPanel cards = (JPanel)frame.getContentPane();
		final CardLayout layout = (CardLayout)cards.getLayout();
		layout.next(cards);
		adjustSize(boardView);
	}
	
	private void adjustSize(JPanel panel) {
		final int widthMargin = 0;
		final int heightMargin = 22;
		
		final Dimension contentPaneSize = panel.getSize();
		frame.setSize(contentPaneSize.width + widthMargin, contentPaneSize.height + heightMargin);
	}
}
