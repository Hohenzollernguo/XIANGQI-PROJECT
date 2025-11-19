package edu.sustech.xiangqi.model;
// 炮

public class CannonPiece extends AbstractPiece{
    public CannonPiece(String name, int row, int col, boolean isRed) {
        super(name, row, col, isRed);
    }

    @Override
    public boolean canMoveTo(int targetRow, int targetCol, ChessBoardModel model) {
        int currentRow = getRow();
        int currentCol = getCol();
        int rowDiff = Math.abs(targetRow- currentRow) ;
        int colDiff = Math.abs(targetCol - currentCol);
        // 炮的移动规则：
        // 1.平直走
        // 2.吃字需要相隔一个子

        if(model.getPieceAt(targetRow,targetCol)!=null){
            AbstractPiece targetPiece = model.getPieceAt(targetRow,targetCol);
            if(targetPiece.isRed() == this.isRed()){
                return false;
            } else if (countPieces(currentRow,currentCol,targetRow,targetCol,model) == 1) {
                return true;
            }
        } // 吃子情况 需要相隔一个棋子
        else if(((currentCol == targetCol && currentRow != targetRow) || (currentCol != targetCol && currentRow == targetRow)) && (countPieces(currentRow,currentCol,targetRow,targetCol,model) == 0)){
            return true;
        } //不吃子情况，正常平直走且相隔零个棋子
        return false;

        }

       private int countPieces(int currentRow, int currentCol, int targetRow, int targetCol, ChessBoardModel model) {
        int count = 0;
           if (currentRow == targetRow) {
               int minCol = Math.min(currentCol, targetCol);
               int maxCol = Math.max(currentCol, targetCol);
               for (int col = minCol + 1; col < maxCol; col++) {
                   if (model.getPieceAt(currentRow, col) != null) {
                       count++;
                   }
               }
           }
           else if (currentCol == targetCol) {
               int minRow = Math.min(currentRow, targetRow);
               int maxRow = Math.max(currentRow, targetRow);
               for (int row = minRow + 1; row < maxRow; row++) {
                   if (model.getPieceAt(row, currentCol) != null) {
                       count++;
                   }
               }
           }
           return count;
    } // 计算与目标点之间相隔有几个棋子
}
