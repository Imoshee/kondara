package com.example.myapplication;

public class User {
    private String name;
    private String phone;
    private String mail;
    private String userName;

    public User(){}

    public User(String name, String phone, String mail, String userName) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
