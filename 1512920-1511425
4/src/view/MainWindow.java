package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.*;

import controller.ChessController;


public class MainWindow implements ActionListener {
	
	private JFrame frame;

	private InitialView initialView;

	private BoardView boardView;

	private ChessController controller;
	
	public MainWindow(ChessController controller) {
		setController(controller);
		frame = new JFrame("Chess");
		initialView = new InitialView(this);
		boardView = new BoardView(this, controller);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JPanel cards = new JPanel(new CardLayout());
		cards.add(initialView, 0);
		cards.add(boardView, 1);
		frame.setContentPane(cards);
		showInitialView();
		frame.setVisible(true);
	}

	public void showInitialView() {
		final JPanel cards = (JPanel)frame.getContentPane();
		final CardLayout layout = (CardLayout)cards.getLayout();
		layout.first(cards);
		adjustSize(initialView);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "New Game") {
			controller.startNewMatch();
			controller.addModelListener(boardView);
			boardView.loadBoard();
			final JPanel cards = (JPanel)frame.getContentPane();
			final CardLayout layout = (CardLayout)cards.getLayout();
			layout.next(cards);
			adjustSize(boardView);
		}
		else {
			File dir = new File("matches");
			JFileChooser file = new JFileChooser() ;
			file.setCurrentDirectory(dir);
			final int returnState = file.showOpenDialog(null);
			if(returnState == JFileChooser.APPROVE_OPTION) {
				String fileDir = file.getSelectedFile().getAbsolutePath();
				controller.continueGame(fileDir);
				controller.addModelListener(boardView);
				boardView.loadBoard();
				final JPanel cards = (JPanel)frame.getContentPane();
				final CardLayout layout = (CardLayout)cards.getLayout();
				layout.next(cards);
				adjustSize(boardView);
			}
		}
	}

	private void setController(ChessController c) {
		controller = c;
	}
	private void adjustSize(JPanel panel) {
		final int widthMargin = 0;
		final int heightMargin = 22;
		
		final Dimension contentPaneSize = panel.getSize();
		frame.setSize(contentPaneSize.width + widthMargin, contentPaneSize.height + heightMargin);
	}
}
