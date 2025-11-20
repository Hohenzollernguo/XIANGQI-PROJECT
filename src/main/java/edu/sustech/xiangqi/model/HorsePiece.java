package edu.sustech.xiangqi.model;
//马
public class HorsePiece extends AbstractPiece{
    public HorsePiece(String name, int row, int col, boolean isRed) {
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
        // 马的移动规则：
        // 1. 只走“日”字
        // 2. 蹩马腿：若马前进方向的直走 1 格位置有棋子，则该方向的 “日” 字移动被禁止。

        AbstractPiece targetPiece = model.getPieceAt(targetRow,targetCol);
        if(targetPiece != null && targetPiece.isRed() == this.isRed()){
            return false;
        } // 验证落子处没有己方棋子

        if(!((rowDiff==1 && colDiff ==2) ||(rowDiff ==2 && colDiff ==1))){
            return false;
        } // 验证走的必须是“日”字

        if(!(targetRow >= 0 && targetRow <= 9 && targetCol >= 0 && targetRow <=8)){
            return false;
        } //验证落子在棋盘范围内

        int legRow = currentRow;
        int legCol = currentCol;
        if(rowDiff == 2){
            if(targetRow > currentRow){
                legRow = currentRow +1;
            }else {
                legRow = currentRow -1;
            }
        }else if(colDiff == 2){
            if(targetCol > currentCol){
                legCol = currentCol + 1;
            }else {
                legCol = currentCol - 1;
            }
        }
        if(model.getPieceAt(legRow,legCol)!=null){
            return false;
        } // 验证鳖马脚位置上没有棋子

        return true; //所有可能排除外，可以走



    }

}
