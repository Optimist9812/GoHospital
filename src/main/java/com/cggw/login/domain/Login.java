package com.cggw.login.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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

    public Login() {
    }
}