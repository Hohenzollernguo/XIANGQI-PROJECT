package edu.sustech.xiangqi.model;

import edu.sustech.xiangqi.ui.ChessBoardPanel;

import java.util.ArrayList;
import java.util.List;





public class ChessBoardModel {



    boolean blacksidetomove=false;

    public boolean isBlacksidetomove() {
        return blacksidetomove;
    }

    // 储存棋盘上所有的棋子，要实现吃子的话，直接通过pieces.remove(被吃掉的棋子)删除就可以
    private final List<AbstractPiece> pieces;
    private static final int ROWS = 10;
    private static final int COLS = 9;

    public ChessBoardModel() {
        pieces = new ArrayList<>();
        initializePieces();
    }

    public void initializePieces() {
        pieces.clear();
        // 黑方棋子
        pieces.add(new GeneralPiece("將", 0, 4, false));
        pieces.add(new SoldierPiece("卒", 3, 0, false));
        pieces.add(new SoldierPiece("卒", 3, 2, false));
        pieces.add(new SoldierPiece("卒", 3, 4, false));
        pieces.add(new SoldierPiece("卒", 3, 6, false));
        pieces.add(new SoldierPiece("卒", 3, 8, false));
        pieces.add(new ElephantPiece("象",0,2,false));
        pieces.add(new ElephantPiece("象",0,6,false));
        pieces.add(new RookPiece("车",0,0,false));
        pieces.add(new RookPiece("车",0,8,false));
        pieces.add(new HorsePiece("马", 0, 1, false));
        pieces.add(new HorsePiece("马", 0, 7, false));
        pieces.add(new AdvisorPiece("士", 0, 3, false));
        pieces.add(new AdvisorPiece("士", 0, 5, false));
        pieces.add(new CannonPiece("炮", 2, 1, false));
        pieces.add(new CannonPiece("炮", 2, 7, false));

        // 红方棋子
        pieces.add(new GeneralPiece("帅", 9, 4, true));
        pieces.add(new SoldierPiece("兵", 6, 0, true));
        pieces.add(new SoldierPiece("兵", 6, 2, true));
        pieces.add(new SoldierPiece("兵", 6, 4, true));
        pieces.add(new SoldierPiece("兵", 6, 6, true));
        pieces.add(new SoldierPiece("兵", 6, 8, true));
        pieces.add(new ElephantPiece("相",9,2,true));
        pieces.add(new ElephantPiece("相",9,6,true));
        pieces.add(new RookPiece("车",9,0,true));
        pieces.add(new RookPiece("车",9,8,true));
        pieces.add(new HorsePiece("马", 9, 1, true));
        pieces.add(new HorsePiece("马", 9, 7, true));
        pieces.add(new AdvisorPiece("仕", 9, 3, true));
        pieces.add(new AdvisorPiece("仕", 9, 5, true));
        pieces.add(new CannonPiece("炮", 7, 1, true));
        pieces.add(new CannonPiece("炮", 7, 7, true));
    }

    public List<AbstractPiece> getPieces() {
        return pieces;
    }

    public AbstractPiece getPieceAt(int row, int col) {
        for (AbstractPiece piece : pieces) {
            if (piece.getRow() == row && piece.getCol() == col) {
                return piece;
            }
        }
        return null;
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }

    public boolean movePiece(AbstractPiece piece, int newRow, int newCol) {
        if (!isValidPosition(newRow, newCol)) {
            return false;
        }

        if (!piece.canMoveTo(newRow, newCol, this)) {
            return false;
        }


        /**
         * 检验回合是否正确
         */
       if (piece.isRed()&&isBlacksidetomove()||(!piece.isRed()&&(!isBlacksidetomove()))){
          return false;
       }

       AbstractPiece targetPiece = getPieceAt(newRow, newCol);
        if (targetPiece != null) {
            pieces.remove(targetPiece);
        }

        piece.moveTo(newRow, newCol);
        /**
         * 监测下一步该哪边移动
         */
        blacksidetomove=!isBlacksidetomove();

        boolean opponentIsRed = !piece.isRed();
        if(isCheckmated(opponentIsRed)){
            return false; //对手被将死，己方胜利，游戏应该结束（结束操作还未做）
        }else if(isStalemated(opponentIsRed)){
            return false; //对手僵局，平局，游戏应该结束（结束操作还未做）
        } else if (isInCheck(opponentIsRed)) {
            //对方被将军，应该给出提示（具体还未做）
        }
        return true;
    }

    private AbstractPiece getGeneral(boolean isRed){
        for(AbstractPiece piece: pieces){
            if(piece.isRed() == isRed){
                if(isRed){
                    if(piece.getName().equals("帅")){
                        return piece;
                    }
                }else{if(piece.getName().equals("将"))
                    return piece;
                }
            }
        }
        return null;
    } //获取将或者帅

    public boolean isInCheck(boolean isRed){
        AbstractPiece general = getGeneral(isRed);
        int genRow = general.getRow();
        int genCol = general.getCol();
        for(AbstractPiece opponent : pieces){
            if(opponent.isRed() !=isRed){
                if (opponent.canMoveTo(genRow,genCol,this)){
                    return true;
                }
            }

        }
        return false;
    } //判断当前是否被将军

    public boolean isCheckmated(boolean isRed){
        if(!isInCheck(isRed)){
            return false;
        } //先判断是否被将军
        for(AbstractPiece piece : pieces){
            if(piece.isRed() == isRed){
                int originalRow = piece.getRow();
                int originalCol = piece.getCol();
                for (int targetRow = 0; targetRow < ROWS; targetRow++) {
                    for (int targetCol = 0; targetCol < COLS; targetCol++) {
                        if(!piece.canMoveTo(targetRow,targetCol,this)){
                            continue;
                        }
                        AbstractPiece capturedPiece = getPieceAt(targetRow,targetCol);
                        if(capturedPiece !=null){
                            pieces.remove(capturedPiece);
                        }
                        piece.moveTo(targetRow,targetCol);
                        boolean secondCheck = isInCheck(isRed);
                        piece.moveTo(originalRow,originalCol);
                        if(capturedPiece !=null){
                            pieces.add(capturedPiece);
                        }
                        if(!secondCheck){
                            return false;
                        }
                    }
                }

            }
        }
        return true;
    } //判断是否被将死

    public boolean isStalemated(boolean isRed){
        if(isInCheck(isRed)){
            return false;
        }
        for (AbstractPiece piece : pieces){
            if(piece.isRed() == isRed){
                int originalRow = piece.getRow();
                int originalCol = piece.getCol();
                for (int targetRow = 0; targetRow < ROWS; targetRow++) {
                    for (int targetCol = 0; targetCol < COLS; targetCol++) {
                        if(!piece.canMoveTo(targetRow,targetCol,this)){
                            continue;
                        }
                        AbstractPiece capturedPiece = getPieceAt(targetRow,targetCol);
                        if(capturedPiece !=null){
                            pieces.remove(capturedPiece);
                        }
                        piece.moveTo(targetRow,targetCol);
                        boolean secondCheck = isInCheck(isRed);
                        piece.moveTo(originalRow,originalCol);
                        if(capturedPiece !=null){
                            pieces.add(capturedPiece);
                        }
                        if(!secondCheck){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    } //判断是否僵局






    public static int getRows() {
        return ROWS;
    }

    public static int getCols() {
        return COLS;
    }
}
