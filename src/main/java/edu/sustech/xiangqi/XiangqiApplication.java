package edu.sustech.xiangqi;
import edu.sustech.xiangqi.model.ChessBoardModel;
import edu.sustech.xiangqi.ui.ChessBoardPanel;
import edu.sustech.xiangqi.ui.Components.RestartButton;
import edu.sustech.xiangqi.ui.Components.ThemeButton;
import edu.sustech.xiangqi.ui.LoginFrame;
import edu.sustech.xiangqi.user.User;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class XiangqiApplication {
    private final User currentUser;

    public XiangqiApplication(User currentUser){
        this.currentUser = currentUser;
        initGame();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);

        /*
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("中国象棋");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ChessBoardModel model = new ChessBoardModel();
            ChessBoardPanel boardPanel = new ChessBoardPanel(model);
            frame.add(boardPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);

            // 设置组件
             RestartButton.RestartGame(frame,model,boardPanel);
             frame.setVisible(true);
        });
              已添加注册登录界面，不再直接进入象棋游戏界面*/

    }

    public void initGame(){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("中国象棋");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ChessBoardModel model = new ChessBoardModel();
            ChessBoardPanel boardPanel = new ChessBoardPanel(model);
            frame.add(boardPanel);

            RestartButton.init(model,boardPanel);

            //生成菜单栏
            JMenuBar menuBar = new JMenuBar();
            JMenu gameMenu = new JMenu("游戏");

            JMenuItem restartItem = new JMenuItem("重新开始");
            restartItem.addActionListener(e -> RestartButton.restartAction());
            gameMenu.add(restartItem);

            JMenuItem saveItem = new JMenuItem("保存游戏");
            saveItem.setEnabled(!currentUser.isGuest());
            saveItem.addActionListener(e -> model.saveGame(currentUser.getUsername()));
            gameMenu.add(saveItem);

            JMenuItem loadItem = new JMenuItem("加载游戏");
            loadItem.setEnabled(!currentUser.isGuest());
            loadItem.addActionListener(e -> model.loadGame(currentUser.getUsername()));
            gameMenu.add(loadItem);

            JMenuItem undoItem = new JMenuItem("悔棋");
            undoItem.setEnabled(!currentUser.isGuest());
            undoItem.addActionListener(e -> model.undoLastMove());
            gameMenu.add(undoItem);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                   if(!currentUser.isGuest()){
                       model.saveGame(currentUser.getUsername());
                   }
                }
            });

            menuBar.add(gameMenu);
            frame.setJMenuBar(menuBar);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            ThemeButton.setTheme(frame,boardPanel);
        });
    }
}


