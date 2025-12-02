package edu.sustech.xiangqi.ui.Components;

import edu.sustech.xiangqi.ui.ChessBoardPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class ThemeButton {

    private static ChessBoardPanel chessBoardPanel1;


    public static void setTheme(JFrame jFrame, ChessBoardPanel chessBoardPanel){
        JMenuBar jMenuBar=jFrame.getJMenuBar();
        JMenu theme=new JMenu("主题皮肤");
        JMenuItem themeItemChunFen=new JMenuItem("春分");
        JMenuItem themeItemXiazhi=new JMenuItem("夏至");
        JMenuItem themeItemLiQiu=new JMenuItem("立秋");
        JMenuItem themeItemXiaoXue=new JMenuItem("小雪");

        themeItemChunFen.addActionListener(e -> themeChange("春分.jpg"));
        themeItemXiaoXue.addActionListener(e -> themeChange("小雪.jpg"));
        themeItemLiQiu.addActionListener(e -> themeChange("立秋.jpg"));
        themeItemXiazhi.addActionListener(e -> themeChange("夏至.jpg"));

        theme.add(themeItemChunFen);
        theme.add(themeItemXiazhi);
        theme.add(themeItemLiQiu);
        theme.add(themeItemXiaoXue);
        jMenuBar.add(theme);
        jFrame.setJMenuBar(jMenuBar);

        chessBoardPanel1=chessBoardPanel;
    }

static BufferedImage bufferedImage;


    public static void themeChange(String name){

    try {
        InputStream inputStream= ThemeButton.class.getClassLoader().getResourceAsStream(name);


         bufferedImage = ImageIO.read(inputStream);
         chessBoardPanel1.setBufferedImage(bufferedImage);
    } catch (IOException e) {
        throw new RuntimeException(e);

    }
chessBoardPanel1.repaint();
}

}
