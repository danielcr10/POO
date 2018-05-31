package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class BoardView extends JPanel implements PropertyChangeListener {

	private static final String imagesPath = "images";
	private static final String piecesImagesPath = "pieces";
	private static final double boardFrameSize = 71;
	private static final int dimension = 8;
	
	Image boardFrameImage;
	
	String[][] pieces;
	
	HashMap<String, Image> piecesImages;
	
	Point clickedPoint;

	public BoardView(String[][] pieces) {
		try {
			boardFrameImage = ImageIO.read(new File(imagesPath + File.separator + "board.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		setSize(boardFrameImage.getWidth(null), boardFrameImage.getHeight(null));
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clickedPoint = e.getPoint();
				repaint();
			}
		});
		piecesImages = readPiecesImages();
		this.pieces = pieces;
	}
	
	private HashMap<String, Image> readPiecesImages() {
		HashMap<String, Image> imagesMap = new HashMap<>();
		try {
			final File directory = new File(imagesPath + File.separator + piecesImagesPath);
			for(File file : directory.listFiles()) {
				final String filename = file.getName();
				final String filenameWithoutExtension = filename.substring(0, filename.length() - 4);
				final Image image = ImageIO.read(file);
				imagesMap.put(filenameWithoutExtension, image);
			}
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

		return imagesMap;
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
		
		double x;
		double y;
		for(int i = 0; i < dimension; i++) {
			for(int j = (i + 1) % 2; j < dimension; j += 2) {
				x = boardFrameSize + j * squareDimension.getWidth();
				y = boardFrameSize + i * squareDimension.getHeight();
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

	private void drawSelectionRedSquare(Graphics2D g) {
		final Dimension squareDimension = getSquareDimension();
		g.setPaint(Color.RED);
		g.setStroke(new BasicStroke(3));
		final double x = clickedPoint.getX() - (clickedPoint.getX() - boardFrameSize) % squareDimension.getWidth();
		final double y = clickedPoint.getY() - (clickedPoint.getY() - boardFrameSize) % squareDimension.getHeight();
		g.draw(new Rectangle2D.Double(x, y, squareDimension.getWidth(), squareDimension.getHeight()));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(boardFrameImage, 0, 0, null);
		drawBoard((Graphics2D) g);

		if(clickedPoint != null) {
			drawSelectionRedSquare((Graphics2D) g);
		}

		drawPieces(g);
	}

	public void propertyChange(PropertyChangeEvent e) {
		System.out.println("property changed!");
	}
	
}
