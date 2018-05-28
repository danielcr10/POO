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
			for(int i=1;p.y<Board.dimension - 1 && p.x<Board.dimension - 1;i++) {
				p.x = from.x + i;
				p.y = from.y + i;
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
			
			for(int k=1;p.y<Board.dimension - 1 && p.x>=0;k++) {
				p.x = from.x - k;
				p.y = from.y + k;
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
			
			for(int j=1;p.y>=0 && p.x<Board.dimension - 1;j++) {
				p.x = from.x + j;
				p.y = from.y - j;
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
			
			for(int l=1;p.y>=0 && p.x>=0;l++) {
				p.x = from.x - l;
				p.y = from.y - l;
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
