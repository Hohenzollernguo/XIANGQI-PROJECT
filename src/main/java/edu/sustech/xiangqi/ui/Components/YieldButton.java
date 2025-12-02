package edu.sustech.xiangqi.ui.Components;

import edu.sustech.xiangqi.model.ChessBoardModel;

import javax.swing.*;

public class YieldButton {


    public static void yield(ChessBoardModel chessBoardModel){
      String winnerSide=null;
        if (chessBoardModel.isBlacksidetomove()){
             winnerSide="红方";

        }else {
             winnerSide="黑方";
        }
        chessBoardModel.setGameOver(true);
        JOptionPane.showMessageDialog(
                null,
                winnerSide + "胜利！\n你已投降！",
                "游戏结束",
                JOptionPane.INFORMATION_MESSAGE
        );
    }



}
