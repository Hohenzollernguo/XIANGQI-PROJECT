package edu.sustech.xiangqi.ui;

import edu.sustech.xiangqi.XiangqiApplication;
import edu.sustech.xiangqi.user.User;
import edu.sustech.xiangqi.user.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private final UserManager userManager;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame(){
        userManager =new UserManager();
        initUI();
    }

    //创建登录页面
    public void initUI(){
        setTitle("用户登录");
        setSize(600,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(4,2,20,20));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("用户名"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add((new JLabel("密码")));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginBtn = new JButton("登录");
        loginBtn.addActionListener(this::handleLogin);
        panel.add(loginBtn);

        JButton registerBtn = new JButton("注册");
        registerBtn.addActionListener(e -> new RegisterFrame(userManager));
        panel.add(registerBtn);

        JButton guestBtn = new JButton("访客登录");
        guestBtn.addActionListener(e -> startGame(new User(true, "", "访客")));
        panel.add(guestBtn);

        add(panel);
        setVisible(true);
    }

    //进行登录判断
    private void handleLogin(ActionEvent e){
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        User user = userManager.login(username,password);
        if(user != null){
            JOptionPane.showMessageDialog(this,"登陆成功");
            startGame(user);
        } else{
            JOptionPane.showMessageDialog(this,"用户名或密码错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    //启动游戏主界面
    private void startGame(User currentUser) {
        dispose();
        SwingUtilities.invokeLater(() -> new XiangqiApplication(currentUser));
    }

}
