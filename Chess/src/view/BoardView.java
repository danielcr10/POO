package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BoardView extends JPanel {

	private static final String imagesPath = "images";
	private static final String piecesImagesPath = "pieces";
	private static final double boardFrameSize = 71;
	private static final int dimension = 8;
	
	Image boardFrameImage;
	
	String[][] pieces;
	
	HashMap<String, Image> piecesImages;
	
	public BoardView(String[][] pieces) {
		try {
			boardFrameImage = ImageIO.read(new File(imagesPath + File.separator + "board.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		setSize(boardFrameImage.getWidth(null), boardFrameImage.getHeight(null));
		readPiecesImages();
		this.pieces = pieces;
	}
	
	private void readPiecesImages() {
		piecesImages = new HashMap<>();
		try {
			final File directory = new File(imagesPath + File.separator + piecesImagesPath);
			for(File file : directory.listFiles()) {
				final String filename = file.getName();
				final String filenameWithoutExtension = filename.substring(0, filename.length() - 4);
				final Image image = ImageIO.read(file);
				piecesImages.put(filenameWithoutExtension, image);
			}
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
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
			for(int j = (i + 1) % 2; j < dimension; j += 2) {
				final double x = boardFrameSize + j * squareDimension.getWidth();
				final double y = boardFrameSize + i * squareDimension.getHeight();
				g.fill(new Rectangle2D.Double(x, y, squareDimension.getWidth(), squareDimension.getHeight()));
			}
		}
	}
	
	private void drawPieces(Graphics g) {
		final Dimension squareDimension = getSquareDimension();
		final int xMargin = 3;
		final int yMargin = -10;
		for(int i = 0; i < dimension; i++) {
			for(int j = 0; j < dimension; j++) {
				final String pieceType = pieces[i][j];
				if(pieceType != "") {
					final Image image = piecesImages.get(pieceType);
					final double x = boardFrameSize + j * squareDimension.getWidth();
					final double y = boardFrameSize + i * squareDimension.getHeight() - (image.getHeight(null) - squareDimension.getHeight());
					g.drawImage(image, (int)x + xMargin, (int)y + yMargin, null);
				}
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(boardFrameImage, 0, 0, null);
		drawBoard((Graphics2D) g);
		drawPieces(g);
	}
	
}
