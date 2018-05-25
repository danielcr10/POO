package model;

import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends Piece {
	
	public Pawn(Color color) {
		super(color);
	}
	
	public Point[] movePossibilities(Board domain, Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		if(from.y < Board.dimension - 1) {
			Point p = (Point)from.clone();
			p.y = from.y + 1;
			if(domain.squareIsVacant(p)) {
				possibilitiesList.add((Point)p.clone());
			}
			
			p.x -= 1;
			if(p.x >= 0 && !domain.squareIsVacant(p)) {
				final Piece piece = domain.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
			}
			
			p.x += 2;
			if(p.x < Board.dimension && !domain.squareIsVacant(p)) {
				final Piece piece = domain.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
			}
		}
		
		final Point[] possibilitiesArray = (Point[]) possibilitiesList.toArray();
		
		return possibilitiesArray;
	}
	
}
