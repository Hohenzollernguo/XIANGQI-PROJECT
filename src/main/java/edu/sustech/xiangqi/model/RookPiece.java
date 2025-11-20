package edu.sustech.xiangqi.model;

public class RookPiece extends AbstractPiece {
    public RookPiece(String name, int row, int col, boolean isRed) {
        super(name, row, col, isRed);
    }

    public boolean canMoveTo(int targetRow, int targetCol, ChessBoardModel model) {
        if (!super.canMoveTo(targetRow,targetCol,model)){
            return false;
        }
        int currentRow = getRow();
        int currentCol = getCol();
        if (currentRow == targetRow && currentCol == targetCol) {
            return false;
        }
        int rowDiff = targetRow - currentRow;
        int colDiff = targetCol - currentCol;
        //车的移动规则：
        // 直线任意步；无障碍可行
        if (colDiff != 0 && rowDiff != 0) {
            return false;
        }
        if (colDiff == 0) {
            if (targetRow > getRow()) {
                for (int i = getRow() + 1; i < targetRow; i++) {
                    if (model.getPieceAt(i, getCol()) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = getRow() - 1; i > targetRow; i--) {
                    if (model.getPieceAt(i, getCol()) != null) {
                        return false;
                    }
                }

            }
        } else {
            if (targetCol > getCol()) {
                for (int i = getCol() + 1; i < targetCol; i++) {
                    if (model.getPieceAt(getRow(), i) != null) {
                        return false;
                    }
                }
            } else {
                for (int i = getCol() - 1; i > targetCol; i--) {
                    if (model.getPieceAt(getRow(), i) != null) {
                        return false;
                    }
                }
            }
        }
        if (isRed()) {
            if (model.getPieceAt(targetRow, targetCol) != null) {
                if (model.getPieceAt(targetRow, targetCol).isRed()) {
                    return false;
                }
            }
        } else {
            if (model.getPieceAt(targetRow, targetCol) != null) {
                if (!model.getPieceAt(targetRow, targetCol).isRed()) {
                    return false;
                }
            }
        }
        return true;
    }
}
