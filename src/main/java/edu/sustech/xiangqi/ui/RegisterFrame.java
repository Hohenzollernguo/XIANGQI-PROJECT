package edu.sustech.xiangqi.ui;

import edu.sustech.xiangqi.user.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterFrame  extends JFrame {
    private final UserManager userManager;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegisterFrame(UserManager userManager){
        this.userManager = userManager;
        initUI();
        setVisible(true);
    }

    private void initUI(){
        setTitle("用户注册");
        setSize(300,200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("用户名"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("密码"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton registerBtn = new JButton("注册");
        registerBtn.addActionListener(this::handleRegister);
        panel.add(registerBtn);

        JButton cancelBtn = new JButton("取消");
        cancelBtn.addActionListener(e -> dispose());
        panel.add(cancelBtn);

        add(panel);
    }

    private void handleRegister(ActionEvent e){
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        if(username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,"用户名和密码不能为空！","错误",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (userManager.register(username,password)){
            JOptionPane.showMessageDialog(this,"注册成功");
            dispose();
        }else {
            JOptionPane.showMessageDialog(this,"用户名已经存在！请直接登录","错误",JOptionPane.ERROR_MESSAGE);
        }
    }
}
