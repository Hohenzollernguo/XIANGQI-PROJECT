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
        int rowDiff = Math.abs(targetRow - currentRow);
        int colDiff = Math.abs(targetCol - currentCol);

        //象的移动规则：
        //1.走田字（斜线两格）；2.不能过河；3.堵象眼不可走
        if (rowDiff!=2||colDiff!=2){
            return false;
        }

        else if (isRed()){
             if (targetRow<5){
                 return false;
             }
        }else {
            if (targetRow>4){
                return false;
            }

        }

       return true;

    }
}
