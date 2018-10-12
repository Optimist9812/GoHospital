package com.cggw.login.domain;



public class Login {
    private Integer account;

    private String password;

    private String flag;

    private String name;

    @Override
    public String toString() {
        return "Login{" +
                "account=" + account +
                ", password='" + password + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }

    public Login(Integer account, String password, String flag, String name) {
        this.account = account;
        this.password = password;
        this.flag = flag;
        this.name = name;
    }

    public Login(Integer account) {
        this.account = account;
    }

    public Login() {
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}