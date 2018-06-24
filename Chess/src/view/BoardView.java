package view;

import java.util.ArrayList;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.ChessController;


@SuppressWarnings("serial")
public class BoardView extends JPanel implements PropertyChangeListener {

	private static final String imagesPath = "images";
	private static final String piecesImagesPath = "pieces";
	private static final double boardFrameSize = 61;
	private static final int dimension = 8;
	
	private static Image boardFrameImage;
	
	String[][] pieces;
	
	HashMap<String, Image> piecesImages;
	
	Point clickedSquare;

	ChessController controller;

	ArrayList<Point> targetPositions;

	PromotionChooser chooser;

	Point promotionPosition;

	Point kingInCheckPosition;

	static {
		try {
			boardFrameImage = ImageIO.read(new File(imagesPath + File.separator + "board.png"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public BoardView(ChessController controller) {
		setSize(boardFrameImage.getWidth(null), boardFrameImage.getHeight(null));
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				final Point clickedPoint = e.getPoint();
				if(boardContainsPoint(clickedPoint)) {
					if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
						JPopupMenu popupMenu = new JPopupMenu();
						saveOption(popupMenu);
						openMenu(clickedPoint, popupMenu);
					}
					Dimension squareDimension = getSquareDimension();
					int i = (int)((clickedPoint.getY() - boardFrameSize) / squareDimension.getHeight());
					int j = (int)((clickedPoint.getX() - boardFrameSize) / squareDimension.getWidth());
					Point p = new Point(j, i);
					if(promotionPosition == null && p.equals(clickedSquare)) {
						return;
					}

					if(promotionPosition != null) {
						if(p.equals(promotionPosition)) {
							clickedSquare = promotionPosition;
							targetPositions = null;
							repaint();
							chooser.openMenu(clickedPoint);
						}
					}
					else if(targetPositions != null && targetPositions.contains(p) && promotionPosition == null) {
						controller.requestPieceMove(clickedSquare, p);
						if(pieces[i][j].contains("Pawn") && (i == 0 || i == dimension - 1)) {
							promotionPosition = new Point(j, i);
							clickedSquare = promotionPosition;
							chooser.setPawnPosition(promotionPosition);
							chooser.openMenu(clickedPoint);
						}
						else {
							clickedSquare = null;
						}
						targetPositions = null;
					}
					else if(positionHasPiece(p)) {
						if(controller.playerHasPermission(p)) {
							clickedSquare = p;
							targetPositions = controller.getMovePossibilities(p);
							repaint();
						}
					}

				}
			}
		});
		piecesImages = readPiecesImages();
		this.controller = controller;
		this.pieces = controller.getBoard();
		chooser = new PromotionChooser(this, controller);
	}

	public void refreshBoard() {
		this.pieces = controller.getBoard();
	}

	private void saveOption(JPopupMenu popupMenu) {
		JMenuItem item = new JMenuItem("Save");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveGame();
			}
		});
		popupMenu.add(item);
	}

	private void openMenu(Point where, JPopupMenu popupMenu) {
		popupMenu.show(this, where.x, where.y);
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
	
	private void drawMovesPossibilities(Graphics2D g) {
		final Dimension squareDimension = getSquareDimension();
		g.setPaint(new Color(255,0,0,130)); //R,G,B,Alpha 0-255
		g.setStroke(new BasicStroke(3));
		for(Point p : targetPositions) {
			final double x = boardFrameSize + p.getX() * squareDimension.getWidth();
			final double y = boardFrameSize + p.getY() * squareDimension.getHeight();
			g.fill(new Rectangle2D.Double(x, y, squareDimension.getWidth(), squareDimension.getHeight()));
		}
	}
	
	private void drawKingInCheck(Graphics2D g) {
		final Dimension squareDimension = getSquareDimension();
		g.setPaint(new Color(255,0,0,255)); //R,G,B,Alpha 0-255
		g.setStroke(new BasicStroke(3));
		final double x = boardFrameSize + kingInCheckPosition.getX() * squareDimension.getWidth();
		final double y = boardFrameSize + kingInCheckPosition.getY() * squareDimension.getHeight();
		g.fill(new Rectangle2D.Double(x, y, squareDimension.getWidth(), squareDimension.getHeight()));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(boardFrameImage, 0, 0, null);
		drawBoard((Graphics2D) g);

		if(clickedSquare != null) {
			drawSelectionRedSquare((Graphics2D) g);
		}
		
		if(targetPositions != null) {
			drawMovesPossibilities((Graphics2D) g);
		}

		if(kingInCheckPosition != null) {
			drawKingInCheck((Graphics2D) g);
		}

		drawPieces(g);
	}

	public void propertyChange(PropertyChangeEvent e) {
		if(e.getPropertyName() == "board") {
			pieces = (String[][])e.getNewValue();
			final String status = controller.requestMatchStatus();
			if(status != "PLAYING") {
				if(status == "CHECK") {
					kingInCheckPosition = controller.requestCurrentPlayerKingPosition();
				}
				else if(status == "CHECKMATE") {
					kingInCheckPosition = controller.requestCurrentPlayerKingPosition();
					JOptionPane.showMessageDialog(this, "Xeque-mate!");
				}
				else if(status == "STALEMATE") {
					JOptionPane.showMessageDialog(this, "Empate!");
				}
			}
			else {
				kingInCheckPosition = null;
			}
			repaint();
		}
	}
}
