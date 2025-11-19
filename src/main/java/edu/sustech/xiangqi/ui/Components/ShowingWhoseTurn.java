package edu.sustech.xiangqi.ui.Components;

import edu.sustech.xiangqi.model.ChessBoardModel;

import javax.swing.*;





public class ShowingWhoseTurn {


    private JLabel turnLabel;

    public  void setIndicationButton(ChessBoardModel chessBoardModel,JFrame jFrame){

            JMenuBar jMenuBar1=new JMenuBar();
         turnLabel=new JLabel("红方回合");
        if (chessBoardModel.isBlacksidetomove()){
            turnLabel.setText("黑方回合");
        }else {
            turnLabel.setText("红方回合");
        }




       jMenuBar1.add(turnLabel);
       jFrame.setJMenuBar(jMenuBar1);
    }




}
