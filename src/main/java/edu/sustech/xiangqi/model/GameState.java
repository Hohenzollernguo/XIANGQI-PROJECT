package edu.sustech.xiangqi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    private List<AbstractPiece> pieces;
    private boolean isBlackToMove;
    private boolean isGameOver;
    private int redPieceNum;
    private int blackPieceNum;
    private boolean hasMove;
    private  int originalRow;
    private int originalCol;
    private List<Move> moveHistory;

    public GameState(ChessBoardModel model){
        this.pieces = new ArrayList<>(model.getPieces());
        this.isBlackToMove = model.blacksidetomove;
        this.isGameOver = model.isGameOver();
        this.redPieceNum = model.getRedPieceNum();
        this.blackPieceNum = model.getBlackPieceNum();
        this.moveHistory = model.getMoveHistory();
    }

    public List<AbstractPiece> getPieces() {
        return pieces;
    }

    public void setPieces(List<AbstractPiece> pieces) {
        this.pieces = pieces;
    }

    public boolean isBlackToMove() {
        return isBlackToMove;
    }

    public void setBlackToMove(boolean blackToMove) {
        isBlackToMove = blackToMove;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public int getRedPieceNum() {
        return redPieceNum;
    }

    public void setRedPieceNum(int redPieceNum) {
        this.redPieceNum = redPieceNum;
    }

    public int getBlackPieceNum() {
        return blackPieceNum;
    }

    public void setBlackPieceNum(int blackPieceNum) {
        this.blackPieceNum = blackPieceNum;
    }

    public boolean isHasMove() {
        return hasMove;
    }

    public void setHasMove(boolean hasMove) {
        this.hasMove = hasMove;
    }

    public int getOriginalRow() {
        return originalRow;
    }

    public void setOriginalRow(int originalRow) {
        this.originalRow = originalRow;
    }

    public int getOriginalCol() {
        return originalCol;
    }

    public void setOriginalCol(int originalCol) {
        this.originalCol = originalCol;
    }

    public List<Move> getMoveHistory() {
        return moveHistory;
    }

    public void setMoveHistory(List<Move> moveHistory) {
        this.moveHistory = moveHistory;
    }
}
