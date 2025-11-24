package edu.sustech.xiangqi.ui.Components;

import edu.sustech.xiangqi.model.ChessBoardModel;
import edu.sustech.xiangqi.ui.ChessBoardPanel;

import javax.swing.*;

public class RestartButton {

private static ChessBoardModel chessBoardModel1;

private static ChessBoardPanel chessBoardPanel1;



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



    public static void restartAction(){
        chessBoardModel1.initializePieces();
        chessBoardModel1.initializeside();
        chessBoardPanel1.initializebrightness();
        chessBoardPanel1.repaint();
        chessBoardModel1.setGameOver(false);
    }
}
