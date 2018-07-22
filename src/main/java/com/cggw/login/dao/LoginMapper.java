package com.cggw.login.dao;

import com.cggw.login.domain.Login;
import com.cggw.login.domain.LoginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {

    Login selectByPrimaryKey(Integer account);

    boolean insertIntoLogin(Login login);

    boolean insertIntoUser(Login login);
}