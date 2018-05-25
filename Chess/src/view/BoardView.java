package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BoardView extends JPanel {

	static final String imagesPath = "images/";
	static final double boardFrameSize = 71;
	static final int dimension = 8;
	
	Image boardFrameImage;
	
	String[][] pieces;
	
	public BoardView(String[][] pieces) {
		try {
			boardFrameImage = ImageIO.read(new File(imagesPath + "board.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		setSize(boardFrameImage.getWidth(null), boardFrameImage.getHeight(null));
		this.pieces = pieces;
	}
	
	private Dimension getSquareDimension() {
		final double width = (boardFrameImage.getWidth(null) - 2 * boardFrameSize) / dimension;
		final double height = (boardFrameImage.getHeight(null) - 2 * boardFrameSize) / dimension;
		
		final Dimension squareDimension = new Dimension();
		squareDimension.setSize(width, height);
		
		return squareDimension;
	}
	
	private void drawBoard(Graphics2D g) {	
		
		final Dimension squareDimension = getSquareDimension();
		
		for(int i = 0; i < dimension; i++) {
			for(int j = i % 2; j < dimension; j += 2) {
				final double x = boardFrameSize + j * squareDimension.getWidth();
				final double y = boardFrameSize + i * squareDimension.getHeight();
				g.fill(new Rectangle2D.Double(x, y, squareDimension.getWidth(), squareDimension.getHeight()));
			}
		}
	}
	
	private void drawPieces(Graphics g) {
		final Dimension squareDimension = getSquareDimension();
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				if(pieces[i][j] != "") {
					Image image;
					try {
						image = ImageIO.read(new File(imagesPath + pieces[i][j] + ".png"));
						System.out.printf("%d, %d\n", squareDimension.width, squareDimension.height);
						final double x = boardFrameSize + j * squareDimension.getWidth();
						final double y = boardFrameSize + i * squareDimension.getHeight();
						g.drawImage(image, (int)x + 3, (int)y - 20, null);
					} catch(IOException e) {
						System.out.println(e.getMessage());
						System.exit(1);
					}
				}
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(boardFrameImage, 0, 0, null);
		drawBoard((Graphics2D) g);
		drawPieces((Graphics2D)g);
	}
	
}
