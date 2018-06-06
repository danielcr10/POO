package view;

import java.util.ArrayList;
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

import controller.ChessController;


@SuppressWarnings("serial")
public class BoardView extends JPanel implements PropertyChangeListener {

	private static final String imagesPath = "images";
	private static final String piecesImagesPath = "pieces";
	private static final double boardFrameSize = 71;
	private static final int dimension = 8;
	
	Image boardFrameImage;
	
	String[][] pieces;
	
	HashMap<String, Image> piecesImages;
	
	Point clickedSquare;

	ChessController controller;

	ArrayList<Point> targetPositions;

	public BoardView(ChessController controller) {
		try {
			boardFrameImage = ImageIO.read(new File(imagesPath + File.separator + "board.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		setSize(boardFrameImage.getWidth(null), boardFrameImage.getHeight(null));
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Point clickedPoint = e.getPoint();
				if(boardContainsPoint(clickedPoint)) {
					Dimension squareDimension = getSquareDimension();
					int i = (int)((clickedPoint.getY() - boardFrameSize) / squareDimension.getHeight());
					int j = (int)((clickedPoint.getX() - boardFrameSize) / squareDimension.getWidth());
					clickedSquare = new Point(j, i);
					if(positionHasPiece(clickedSquare)) {
						targetPositions = controller.getMovePossibilities(clickedSquare);
						for(Point p : targetPositions) {
							System.out.println(p);
						}
					}

				}
				repaint();
			}
		});
		piecesImages = readPiecesImages();
		this.controller = controller;
		this.pieces = controller.getBoard();
	}

	private boolean positionHasPiece(Point p) {
		final String noPiece = "";
		return pieces[p.y][p.x] != noPiece;
	}

	private boolean boardContainsPoint(Point p) {
		return p.getX() > boardFrameSize && p.getX() < getWidth() - boardFrameSize
			&& p.getY() > boardFrameSize && p.getY() < getHeight() - boardFrameSize;
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
		final double x = boardFrameSize + clickedSquare.getX() * squareDimension.getWidth();
		final double y = boardFrameSize + clickedSquare.getY() * squareDimension.getHeight();
		g.draw(new Rectangle2D.Double(x, y, squareDimension.getWidth(), squareDimension.getHeight()));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(boardFrameImage, 0, 0, null);
		drawBoard((Graphics2D) g);

		if(clickedSquare != null) {
			drawSelectionRedSquare((Graphics2D) g);
		}

		drawPieces(g);
	}

	public void propertyChange(PropertyChangeEvent e) {
		System.out.println("property changed!");
	}
	
}
