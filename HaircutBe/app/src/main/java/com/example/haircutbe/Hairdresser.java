package com.example.haircutbe;

public class Hairdresser {
    private String login;
    private String password;
    private String name;
    private String email;
    private String sex;
    private String phone_number;

    public Hairdresser(String name){
        this.name = name;
    }
    public Hairdresser(String login, String password, String name, String email, String sex, String phone_number) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.phone_number = phone_number;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
