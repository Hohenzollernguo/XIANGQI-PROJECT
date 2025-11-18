package edu.sustech.xiangqi.model;

public class RookPiece extends AbstractPiece{
    public RookPiece(String name, int row, int col, boolean isRed) {
        super(name, row, col, isRed);
    }

    public boolean canMoveTo(int targetRow, int targetCol, ChessBoardModel model) {
        int currentRow = getRow();
        int currentCol = getCol();
        if (currentRow == targetRow && currentCol == targetCol) {
            return false;
        }
        int rowDiff = targetRow - currentRow;
        int colDiff = targetCol - currentCol;

        //车的移动规则：
        // 直线任意步；无障碍可行




        return true;
    }
}
