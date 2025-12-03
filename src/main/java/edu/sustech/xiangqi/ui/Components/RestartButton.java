package edu.sustech.xiangqi.ui.Components;

import edu.sustech.xiangqi.model.ChessBoardModel;
import edu.sustech.xiangqi.ui.ChessBoardPanel;

import javax.swing.*;

public class RestartButton {

private static ChessBoardModel chessBoardModel1;

private static ChessBoardPanel chessBoardPanel1;

public static void init(ChessBoardModel chessBoardModel,ChessBoardPanel chessBoardPanel){
    chessBoardModel1 = chessBoardModel;
    chessBoardPanel1 = chessBoardPanel;
}


     /*将创建菜单方法移去，仅保留负责初始化变量：方法init
    public static void RestartGame(JFrame jFrame, ChessBoardModel chessBoardModel, ChessBoardPanel chessBoardPanel) {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu restartMenu=new JMenu("重新开始");
        JMenuItem restartItem=new JMenuItem("重新开始");
        restartItem.addActionListener(e -> restartAction());

        restartMenu.add(restartItem);
        jMenuBar.add(restartMenu);
        jFrame.setJMenuBar(jMenuBar);

        chessBoardPanel1=chessBoardPanel;
        chessBoardModel1=chessBoardModel;
    }
    */


    public static void restartAction(){
        SoundPlayer.StopPlaying();
        chessBoardModel1.initializePieces();
        chessBoardModel1.initializeside();
        chessBoardPanel1.initializebrightnessandmove();
        chessBoardPanel1.repaint();
        chessBoardModel1.setGameOver(false);

    }
}
