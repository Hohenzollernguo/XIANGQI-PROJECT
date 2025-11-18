package edu.sustech.xiangqi.model;

/**
 * 帅/将
 */
public class GeneralPiece extends AbstractPiece {

    public GeneralPiece(String name, int row, int col, boolean isRed) {
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
        //王的移动规则：九宫内走一格直线；不得对面见将
        if((rowDiff!=1&&colDiff!=1)||(rowDiff==1&&colDiff==1)){
            return false;
        }
        if (isRed()){
            if (targetRow<7||targetCol<3||targetCol>5){
                return false;
            }
            for (int i = targetRow; i >=0 ; i--) {
                if (model.getPieceAt(i,targetCol)!=null){
                    if (model.getPieceAt(i,targetCol).getName().equals("将")){
                        return false;
                    }
                }
            }
        }else {if (targetRow>2||targetCol<3||targetCol>5){
            return false;
        }
            for (int i = targetRow; i <=9 ; i++) {
                if (model.getPieceAt(i,targetCol)!=null){
                    if (model.getPieceAt(i,targetCol).getName().equals("帅")){
                        return false;
                    }
                }
            }
        }
        if (model.getPieceAt(targetRow,targetCol)!=null){
            if (model.getPieceAt(targetRow,targetCol).isRed()){
                return false;
            }
        }
        return true;
    }
}
