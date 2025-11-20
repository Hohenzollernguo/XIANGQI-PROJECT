package edu.sustech.xiangqi.model;
// 相 / 士

public class AdvisorPiece extends AbstractPiece{
    public AdvisorPiece(String name, int row, int col, boolean isRed) {
        super(name, row, col, isRed);
    }

    @Override
    public boolean canMoveTo(int targetRow, int targetCol, ChessBoardModel model) {
        if (!super.canMoveTo(targetRow,targetCol,model)){
            return false;
        }
        int currentRow = getRow();
        int currentCol = getCol();
        int rowDiff = Math.abs(targetRow- currentRow) ;
        int colDiff = Math.abs(targetCol - currentCol);
        // 相/士的移动规则：
        // 1. 只走对角线
        // 2. 只能在王宫里移动

        if(rowDiff != 1 || colDiff != 1){
            return false;
        } // 验证移动只走对角线

        if(isRed()){
            if (targetRow < 7 || targetRow > 9 || targetCol < 3 || targetCol > 5) {
                return false;
            }
        }else{
            if (targetRow < 0 || targetRow > 2 || targetCol < 3 || targetCol > 5) {
                return false;
            }
        } // 验证移动在王宫范围内

        AbstractPiece targetPiece = model.getPieceAt(targetRow,targetCol);
        if(targetPiece != null && targetPiece.isRed() == this.isRed()){
            return false;
        } // 验证落子处没有己方棋子

        return true;//所有不满足条件已排除
    }
}
