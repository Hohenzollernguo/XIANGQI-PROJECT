package edu.sustech.xiangqi.user;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final String USER_FILE = "users.ser";
    private List<User> users;

    public UserManager(){
        users = loadUsers();
    }

    // 加载用户列表
    private List<User> loadUsers(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            return (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();   // 文件不存在则返回空列表
        } catch (Exception e) {
            throw new RuntimeException("加载用户数据失败", e);
        }
    }

    //保存用户列表到文件
    private void saveUsers(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
        } catch (Exception e) {
            throw new RuntimeException("保存用户数据失败", e);
        }
    }

    // 验证姓名唯一后，注册新用户
    public boolean register(String username,String password){
        boolean usernameExists = false;
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                usernameExists = true;
                break;
            }
        }
        if (usernameExists) {
            return false;
        }
        users.add(new User(false, password, username));
        saveUsers();
        return true;
    }

    //用户登录：验证姓名和密码对应
    public User login(String username,String password){
        for (User user : users){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
