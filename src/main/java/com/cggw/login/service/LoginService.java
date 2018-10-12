package com.cggw.login.service;

import com.cggw.login.domain.Login;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.Map;

/**
 * Created by lenovo on 2018/7/13.
 */
public interface LoginService {


    boolean getLoginByMessage(Login login);

    boolean getLoginByPass(Login login);

    boolean insertIntoLogin(Login login);

    boolean insertIntoUser(Login login);

    Map<Integer,Object> sendMessage(String number) throws IOException;

    String getRandom();

    int getAccount(int number);//根据电话获取account

    //将id 和 电话插入到user表中
    boolean insertIntoUser2(String tel,int id);

    //根据tel获取id（user表中）（待测试）
    int getIdBytel(int tel);

    //根据account 获取password进行比较（待测试）
    int getPassByAccount(int account);

    //更新密码
    Boolean  updatePass(int account,int pass);
}
