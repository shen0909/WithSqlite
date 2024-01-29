package com.example.withsqlite;

public class UserBean {
    private String name,passWord;
    private String  phone;

    public UserBean(String name, String passWord, String phone) {
        this.name = name;
        this.passWord = passWord;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
