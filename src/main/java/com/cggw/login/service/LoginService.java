package com.cggw.login.service;

import com.cggw.login.domain.Login;

import java.io.IOException;

/**
 * Created by lenovo on 2018/7/13.
 */
public interface LoginService {

    boolean getLoginByMessage(Login login);

    boolean getLoginByPass(Login login);

    boolean insertIntoLogin(Login login);

    boolean insertIntoUser(Login login);

    boolean sendMessage(Integer number) throws IOException;

    String getRandom();
}
