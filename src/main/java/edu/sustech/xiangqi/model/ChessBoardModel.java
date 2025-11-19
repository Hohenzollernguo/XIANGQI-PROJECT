package edu.sustech.xiangqi.model;

import edu.sustech.xiangqi.XiangqiApplication;
import edu.sustech.xiangqi.ui.Components.ShowingWhoseTurn;

import javax.swing.*;
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


        return true;
    }

    public static int getRows() {
        return ROWS;
    }

    public static int getCols() {
        return COLS;
    }
}
