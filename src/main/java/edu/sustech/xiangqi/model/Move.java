package edu.sustech.xiangqi.model;

import java.io.Serializable;

public class Move implements Serializable {
    private AbstractPiece movePiece;
    private int originalRow;
    private int originalCol;
    private int targetRow;
    private int targetCol;
    private AbstractPiece capturedPiece;

    public Move(AbstractPiece movePiece, int originalRow, int originalCol, int targetRow, int targetCol, AbstractPiece capturedPiece) {
        this.movePiece = movePiece;
        this.originalRow = originalRow;
        this.originalCol = originalCol;
        this.targetRow = targetRow;
        this.targetCol = targetCol;
        this.capturedPiece = capturedPiece;
    }

    public AbstractPiece getMovePiece() {
        return movePiece;
    }

    public void setMovePiece(AbstractPiece movePiece) {
        this.movePiece = movePiece;
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

    public int getTargetRow() {
        return targetRow;
    }

    public void setTargetRow(int targetRow) {
        this.targetRow = targetRow;
    }

    public int getTargetCol() {
        return targetCol;
    }

    public void setTargetCol(int targetCol) {
        this.targetCol = targetCol;
    }

    public AbstractPiece getCapturedPiece() {
        return capturedPiece;
    }

    public void setCapturedPiece(AbstractPiece capturedPiece) {
        this.capturedPiece = capturedPiece;
    }
}


