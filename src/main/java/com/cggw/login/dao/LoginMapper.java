package com.cggw.login.dao;

import com.cggw.login.domain.Login;
import com.cggw.login.domain.LoginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {

    Login selectByPrimaryKey(Integer account);

    boolean insertIntoLogin(@Param("login") Login login);

    boolean insertIntoUser(Login login);

    int getAccout(int number);

    //将id 和 电话插入到user表中
    boolean insertIntoUser2(@Param("tel") String tel,@Param("id") int id);

    //根据tel获取id（user表中）
    int getIdBytel(int tel);

    //根据account 获取password进行比较
    int getPassByAccount(int account);

    //更新密码   因为有多个参数，配置文件中可能不会辨别，因此使用@Param（）进行区分，并使得配置文件能够识别
    Boolean  updatePass(@Param("account")int account,@Param("pass")int pass);
}