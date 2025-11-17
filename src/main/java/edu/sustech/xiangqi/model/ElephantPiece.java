package edu.sustech.xiangqi.model;
/**
 * 象/相
 */

public class ElephantPiece extends AbstractPiece {
    public ElephantPiece(String name, int row, int col, boolean isRed) {
        super(name, row, col, isRed);
    }

    @Override
    public boolean canMoveTo(int targetRow, int targetCol, ChessBoardModel model) {
        int currentRow=getRow();
        int currentCol=getCol();
        if (currentRow == targetRow && currentCol == targetCol) {
            return false;
        }






    }
}
