package edu.sustech.xiangqi.model;

import edu.sustech.xiangqi.ui.ChessBoardPanel;
import edu.sustech.xiangqi.ui.Components.SoundPlayer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardModel implements Serializable {





//construct 棋子数量
    int redPieceNum;
    int blackPieceNum;
//

    boolean blacksidetomove=false;
    boolean firsttiemtoplay=true;
    private final PropertyChangeSupport support;

    public boolean isBlacksidetomove() {
        return blacksidetomove;
    }

    // 储存棋盘上所有的棋子，要实现吃子的话，直接通过pieces.remove(被吃掉的棋子)删除就可以
    private final List<AbstractPiece> pieces;
    private static final int ROWS = 10;
    private static final int COLS = 9;

    //设置游戏是否结束的判断
    private boolean isGameOver = false;

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }


    public ChessBoardModel() {
        pieces = new ArrayList<>();
        this.support=new PropertyChangeSupport(this);
        initializePieces();
    }

    public void initializePieces() {
        pieces.clear();


        //为两方棋子计数
        redPieceNum=16;
        blackPieceNum=16;


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

    public void initializeside(){
        blacksidetomove=false;

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

    public void addPropertyChangeListener(String propertyname,PropertyChangeListener listener){
        support.addPropertyChangeListener(propertyname, (PropertyChangeListener) listener);
    }

    public PropertyChangeSupport getSupport() {
        return support;
    }

    int originalRow;
    int originalCol;
    boolean hasmove=false;

    public boolean isHasmove() {
        return hasmove;
    }


    public void setHasmove(boolean hasmove) {
        this.hasmove = hasmove;
    }

    public int getOriginalRow() {
        return originalRow;
    }

    public int getOriginalCol() {
        return originalCol;
    }

    public boolean movePiece(AbstractPiece piece, int newRow, int newCol) {
        //判断游戏是否结束
        if(isGameOver){
            JOptionPane.showMessageDialog(null, "游戏已结束，请点击「重新开始」！", "提示", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

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




       //检查走完后是否会导致己方被将军，如果是则不允许移动
         originalRow = piece.getRow();
         originalCol = piece.getCol();
        AbstractPiece targetPiece = getPieceAt(newRow,newCol);
        boolean isCaptured = false;
        if(targetPiece != null){
            isCaptured = true;
        }
        if(isCaptured){

            if (targetPiece.isRed()){
                SoundPlayer.soundplay("项羽吃子.wav");
                redPieceNum--;
            }else {
                SoundPlayer.soundplay("刘邦吃子.wav");
                blackPieceNum--;
            }
            pieces.remove(targetPiece);
        }






            hasmove=true;
            piece.moveTo(newRow,newCol);
        if(isInCheck(piece.isRed())){
                JOptionPane.showMessageDialog(
                        null,
                        (piece.isRed()? "红方" : "黑方") + "走棋后自身被将军！请重新走棋！",
                        "违规提示",
                        JOptionPane.ERROR_MESSAGE
                );
                piece.moveTo(originalRow,originalCol);
                if(isCaptured){
                    pieces.add(targetPiece);
                }
                hasmove = false;
                return false;
            }
        if(isGeneralFacing()){
            JOptionPane.showMessageDialog(
                    null,
                    "将帅不碰面,请重新走棋！",
                    "违规提示",
                    JOptionPane.ERROR_MESSAGE
            );
            piece.moveTo(originalRow, originalCol);
            if (isCaptured) {
                pieces.add(targetPiece);
            }
            hasmove = false;
            return false;
        } //验证将帅不碰面，否则给出提示
        piece.moveTo(originalRow,originalCol);
        if(isCaptured){
            pieces.add(targetPiece);
        }





       //检验是否游戏结束
        if (targetPiece != null) {
            boolean isOpponentGeneral = false;
            if((targetPiece.isRed() != piece.isRed()) && ((targetPiece.getName().equals("帅")) || (targetPiece.getName().equals("將") ))){
                isOpponentGeneral = true;
            }
            pieces.remove(targetPiece);
            if(isOpponentGeneral){
                SwingUtilities.invokeLater(() -> {
                    String winnerMsg = piece.isRed() ? "红方胜利！" : "黑方胜利！";
                    JOptionPane.showMessageDialog(null, winnerMsg + "\n对方将帅已被吃掉！", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
                    setGameOver(true);
                });
                return true;
            }
        }

        piece.moveTo(newRow, newCol);
        boolean oldValue=isBlacksidetomove();

        /**
         * 监测下一步该哪边移动
         */
        blacksidetomove=!isBlacksidetomove();


        boolean opponentIsRed = !piece.isRed();

        if(isCheckmated(opponentIsRed)){
            SwingUtilities.invokeLater(() -> {
                String winnerSide = piece.isRed() ? "红方" : "黑方";
                JOptionPane.showMessageDialog(
                        null,
                        winnerSide + "胜利！\n对方已被将死！",
                        "游戏结束",
                        JOptionPane.INFORMATION_MESSAGE
                );
                setGameOver(true); // 标记游戏结束
            });
            return false; //对手被将死，己方胜利
        }else if(isStalemated(opponentIsRed)){
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(
                        null,
                        "平局！",
                        "游戏结束",
                        JOptionPane.INFORMATION_MESSAGE
                );
                setGameOver(true); // 标记游戏结束
            });
            return false; //对手僵局，平局，游戏应该结束
        }
        else if (isInCheck(opponentIsRed)) {
            SwingUtilities.invokeLater(() -> {
                String checkedSide = opponentIsRed ? "红方" : "黑方";

                /**
                 * 垓下
                 */

                if (!opponentIsRed&&firsttiemtoplay&&redPieceNum>blackPieceNum){
                    SoundPlayer.soundplay("垓下.wav");
                    firsttiemtoplay=false;
                }

                JOptionPane optionPane = new JOptionPane(
                        checkedSide + "被将军！",
                        JOptionPane.WARNING_MESSAGE
                );
                JDialog dialog = optionPane.createDialog("将军提示");
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setVisible(true);

                // 3秒后自动关闭弹窗
                new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                }).start();
            });
            //对方被将军，应该给出提示
        }
        support.firePropertyChange("blacksidetomove",oldValue,this.isBlacksidetomove());

        return true;
    }

    private AbstractPiece getGeneral(boolean isRed){
        for(AbstractPiece piece: pieces){
            if(piece.isRed() == isRed){
                if(isRed){
                    if(piece.getName().equals("帅")){
                        return piece;
                    }
                }else{if(piece.getName().equals("將"))
                    return piece;
                }
            }
        }
        return null;
    } //获取将或者帅

    public boolean isInCheck(boolean isRed){
        AbstractPiece general = getGeneral(isRed);
        if(general == null){
            return false;
        }
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
        AbstractPiece general = getGeneral(isRed);
        if (general == null){
            return false;
        }
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
        AbstractPiece general = getGeneral(isRed);
        if (general == null){
            return false;
        }
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

    public boolean isGeneralFacing(){
        AbstractPiece redGeneral = getGeneral(true);
        AbstractPiece blackGeneral = getGeneral(false);
        if(redGeneral == null || blackGeneral == null){
            return false;
        }
        if(redGeneral.getCol() != blackGeneral.getCol()){
            return false;
        }
        int minRow = Math.min(redGeneral.getRow(), blackGeneral.getRow());
        int maxRow = Math.max(redGeneral.getRow(), blackGeneral.getRow());
        for (int row = minRow + 1; row < maxRow; row++) {
            if (getPieceAt(row, redGeneral.getCol()) != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * 保存游戏进度
     */
    public void saveGame(String username){
        String filePath = username + "_progress.ser";
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
            oos.writeObject(this);
            JOptionPane.showMessageDialog(null,"游戏保存成功");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "保存失败" + e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *加载游戏进度
     */
    public void loadGame (String username){
        String filePath = username + "_progress.ser";
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))){
            ChessBoardModel loadModel = (ChessBoardModel) ois.readObject();
            this.pieces.clear();
            this.pieces.addAll(loadModel.pieces);
            this.blacksidetomove = loadModel.blacksidetomove;
            this.isGameOver = loadModel.isGameOver;
            this.hasmove = loadModel.hasmove;
            this.originalRow = loadModel.originalRow;
            this.originalCol = loadModel.originalCol;
            JOptionPane.showMessageDialog(null,"游戏加载成功");


        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"未找到保存的游戏进度","提示",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"加载失败" + e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
        }
    }





    public static int getRows() {
        return ROWS;
    }

    public static int getCols() {
        return COLS;
    }
}
