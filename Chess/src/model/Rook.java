package model;

import java.awt.Point;
import java.util.ArrayList;

public class Rook extends Piece {

	public Rook(Color color) {
		super(color);
	}
	
	public ArrayList<Point> movePossibilities(Board domain, Point from) {
		final ArrayList<Point> possibilitiesList = new ArrayList<>(); 
		Point p = (Point)from.clone();
		
		//casas para baixo
		for(int i=1;p.y<Board.dimension - 1;i++) {
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
		
		//casas para cima
		p.y = from.y;
		for(int k=1;p.y>0;k++) {
			p.y = from.y - k;
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
		
		p.y = from.y;
		//casas para direita
		p.x = from.x;
		for(int j=1;p.x<Board.dimension - 1;j++) {
			p.x = from.x + j;
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
		
		//casas para esquerda
		p.x = from.x;
		for(int l=1;p.x>0;l++) {
			p.x = from.x - l;
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
		
		return possibilitiesList;
	}
	
}
