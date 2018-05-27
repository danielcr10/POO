package model;

import java.awt.Point;
import java.util.ArrayList;

public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color);
	}
	
	Point[] movePossibilities(Board domain, Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>();
		if(from.y < Board.dimension - 1) {
			Point p = (Point)from.clone();
			p.x = from.x;
			p.y = from.y;
			for(int i=1;p.y<=Board.dimension - 1 && p.x<=Board.dimension - 1;i++) {
				p.x = p.x + i;
				p.y = p.y + i;
				if(domain.squareIsVacant(p)) {
					possibilitiesList.add((Point)p.clone());
				}
				else {
					final Piece piece = domain.getPieceAt(p);
					if(piece.getColor() != pieceColor) {
						possibilitiesList.add((Point)p.clone());
					}
					break;
				}
			}
			
			p.x = from.x;
			p.y = from.y;
			for(int k=1;p.y<=Board.dimension - 1 && p.x>=0;k++) {
				p.x = p.x - k;
				p.y = p.y + k;
				if(domain.squareIsVacant(p)) {
					possibilitiesList.add((Point)p.clone());
				}
				else {
					final Piece piece = domain.getPieceAt(p);
					if(piece.getColor() != pieceColor) {
						possibilitiesList.add((Point)p.clone());
					}
					break;
				}
			}
			
			p.x = from.x;
			p.y = from.y;
			for(int j=1;p.y>=0 && p.x<=Board.dimension - 1;j++) {
				p.x = p.x + j;
				p.y = p.y - j;
				if(domain.squareIsVacant(p)) {
					possibilitiesList.add((Point)p.clone());
				}
				else {
					final Piece piece = domain.getPieceAt(p);
					if(piece.getColor() != pieceColor) {
						possibilitiesList.add((Point)p.clone());
					}
					break;
				}
			}
			
			p.x = from.x;
			p.y = from.y;
			for(int l=1;p.y>=0 && p.x>=0;l++) {
				p.x = p.x - l;
				p.y = p.y - l;
				if(domain.squareIsVacant(p)) {
					possibilitiesList.add((Point)p.clone());
				}
				else {
					final Piece piece = domain.getPieceAt(p);
					if(piece.getColor() != pieceColor) {
						possibilitiesList.add((Point)p.clone());
					}
					break;
				}
			}
		}
		final Point[] possibilitiesArray = (Point[]) possibilitiesList.toArray();
		
		return possibilitiesArray;
	}
	
}
