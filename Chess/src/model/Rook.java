package model;

import java.awt.Point;
import java.util.ArrayList;

class Rook extends KingdomProtector {

	private static final long serialVersionUID = 1L;

	private boolean moved = false;
	
	public void setMoved(boolean moved) {
		this.moved = moved;
	}
	
	public boolean getMoved() {
		return moved;
	}

	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	public ArrayList<Point> movePossibilities() {
		final ArrayList<Point> possibilitiesList = new ArrayList<>(); 
		Point p = (Point)currentPosition.clone();
		
		//casas para baixo
		for(int i=1;p.y<Board.dimension - 1;i++) {
			p.y = currentPosition.y + i;
			if(pieceBoard.squareIsVacant(p)) {
				possibilitiesList.add((Point)p.clone());
			}
			else {
				final Piece piece = pieceBoard.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
				break;
			}
		}
		
		//casas para cima
		p.y = currentPosition.y;
		for(int k=1;p.y>0;k++) {
			p.y = currentPosition.y - k;
			if(pieceBoard.squareIsVacant(p)) {
				possibilitiesList.add((Point)p.clone());
			}
			else {
				final Piece piece = pieceBoard.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
				break;
			}
		}
		
		p.y = currentPosition.y;
		//casas para direita
		p.x = currentPosition.x;
		for(int j=1;p.x<Board.dimension - 1;j++) {
			p.x = currentPosition.x + j;
			if(pieceBoard.squareIsVacant(p)) {
				possibilitiesList.add((Point)p.clone());
			}
			else {
				final Piece piece = pieceBoard.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
				break;
			}
		}
		
		//casas para esquerda
		p.x = currentPosition.x;
		for(int l=1;p.x>0;l++) {
			p.x = currentPosition.x - l;
			if(pieceBoard.squareIsVacant(p)) {
				possibilitiesList.add((Point)p.clone());
			}
			else {
				final Piece piece = pieceBoard.getPieceAt(p);
				if(piece.getColor() != pieceColor) {
					possibilitiesList.add((Point)p.clone());
				}
				break;
			}
		}
		
		return possibilitiesList;
	}
	
	public void doCastle() {
		Point to = (Point)this.getCurrentPosition().clone();
		if(this.getCurrentPosition().x == 0) {
			to.x = 3;
		}
		else {
			to.x = 5;
		}
		move(to);
	}

	public void die() {
		final PieceSet set = pieceBoard.getPieceSet(pieceColor);
		set.removeRook(this);
	}
	
	public void move(Point to) {
		super.move(to);
		moved = true;
	}
	
}
