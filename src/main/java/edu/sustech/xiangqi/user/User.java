package edu.sustech.xiangqi.user;

import java.io.Serializable;

//用户类
public class User implements Serializable {
    private String username;
    private String password;
    private boolean isGuest;

    public User(boolean isGuest, String password, String username) {
        this.isGuest = isGuest;
        this.password = password;
        this.username = username;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
