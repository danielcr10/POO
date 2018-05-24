package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


@SuppressWarnings("serial")
class BoardPanel extends JPanel {

	Image boardFrameImage;
	
	public BoardPanel() {
		try {
			boardFrameImage = ImageIO.read(new File("images/board.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		setSize(boardFrameImage.getWidth(null), boardFrameImage.getHeight(null));
	}
	
	private void drawBoard(Graphics2D g) {
		final double boardFrameSize = 71;
		
		final int dimension = 8;
		
		final double squareWidth = (boardFrameImage.getWidth(null) - 2 * boardFrameSize) / dimension;
		final double squareHeight = (boardFrameImage.getHeight(null) - 2 * boardFrameSize) / dimension;
		
		for(int i = 0; i < dimension; i++) {
			for(int j = i % 2; j < dimension; j += 2) {
				final double x = boardFrameSize + j * squareWidth;
				final double y = boardFrameSize + i * squareHeight;
				g.fill(new Rectangle2D.Double(x, y, squareWidth, squareHeight));
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(boardFrameImage, 0, 0, null);
		drawBoard((Graphics2D) g);
	}
	
}

public class MainWindow {
	
	JFrame frame;
	
	public MainWindow() {
		frame = new JFrame("Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new BoardPanel());
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
