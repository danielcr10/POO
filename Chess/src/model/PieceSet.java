package model;

import java.util.ArrayList;

public class PieceSet {

	private ArrayList<Piece> allPiecesList;

	private King king;

	private ArrayList<Queen> queenList = new ArrayList<>();

	private ArrayList<Bishop> bishopLists = new ArrayList<>();

	private ArrayList<Knight> knightList = new ArrayList<>();

	private ArrayList<Rook> rookList = new ArrayList<>();

	private ArrayList<Pawn> pawnList = new ArrayList<>();

	public PieceSet(Board board, Color setColor) {
		final int bishopCount = 2;
		final int knightCount = 2;
		final int rookCount = 2;
		final int pawnCount = 8;

		king = new King(board, setColor);
		queenList.add(new Queen(board, setColor));

		for(int i = 0; i < bishopCount; i++) {
			bishopLists.add(new Bishop(board, setColor));
		}

		for(int i = 0; i < knightCount; i++) {
			knightList.add(new Knight(board, setColor));
		}

		for(int i = 0; i < rookCount; i++) {
			rookList.add(new Rook(board, setColor));
		}

		for(int i = 0; i < pawnCount; i++) {
			pawnList.add(new Pawn(board, setColor));
		}

		allPiecesList.add(king);
		allPiecesList.addAll(queenList);
		allPiecesList.addAll(bishopLists);
		allPiecesList.addAll(knightList);
		allPiecesList.addAll(rookList);
		allPiecesList.addAll(pawnList);
	}

	public King getKing() {
		return king;
	}

	public ArrayList<Queen> getQueens() {
		return queenList;
	}

	public ArrayList<Bishop> getBishops() {
		return bishopLists;
	}

	public ArrayList<Knight> getKnights() {
		return knightList;
	}

	public ArrayList<Rook> getRooks() {
		return rookList;
	}

	public ArrayList<Pawn> getPawns() {
		return pawnList;
	}

	public void addQueen(Queen queen) {
		queenList.add(queen);
	}

	public void addBishop(Bishop bishopList) {
		bishopLists.add(bishopList);
	}

	public void addKnight(Knight knight) {
		knightList.add(knight);
	}

	public void addRook(Rook rook) {
		rookList.add(rook);
	}

	public void removeQueen(Queen queen) {
		queenList.remove(queen);
	}

	public void removeBishop(Bishop bishopList) {
		bishopLists.remove(bishopList);
	}

	public void removeKnight(Knight knight) {
		knightList.remove(knight);
	}

	public void removeRook(Rook rook) {
		rookList.remove(rook);
	}

	public void removePawn(Pawn pawn) {
		pawnList.remove(pawn);
	}

}
