package com.example.haircutbe;

public class Login {
    private String username;
    private String user_pwd;

    public Login(){}
    public Login(String username, String user_pwd){
        this.username = username;
        this.user_pwd = user_pwd;
    }

    public String getUsername() {
        return username;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }
}
