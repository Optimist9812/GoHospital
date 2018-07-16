package com.cggw.login.service;

import com.cggw.login.dao.LoginMapper;
import com.cggw.login.domain.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2018/7/12.
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    private LoginMapper loginMapper;

    private String message="";

    public LoginServiceImpl() {
        System.out.println("loginservice***");
    }

    /**
     * 用验证码进行验证(未完成)
     */
    @Override
    public boolean getLoginByMessage(Login login) {
        if(message.equals("")) return false;
        if(message.equals(login.getPassword())) {
            return true;
        }
        return false;
    }

    /**
     * 用密码进行验证（补充数据库）
     */
    @Override
    public boolean getLoginByPass(Login login) {
        Login login2 = loginMapper.selectByPrimaryKey(login.getAccount());
        //从数据库中获取密码
        if(login.getPassword().equals(login2.getPassword())){
            return true;
        }
        return false;
    }

    /**
     * 验证短信（未完成）
    */
    @Override
    public String sendMessage(){
        message = "1234";
        return message;
    }

    /**
     * 前台传入信息后，会在login和user表中分别传入记录
     * @param login
     */
    @Override
    public boolean insertIntoLogin(Login login) {
        return  loginMapper.insertIntoLogin(login);
    }

    @Override
    public boolean insertIntoUser(Login login) {
        return loginMapper.insertIntoUser(login);
    }

}
