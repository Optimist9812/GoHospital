package com.cggw.login.test;

import com.cggw.login.controller.LoginController;
import com.cggw.login.dao.LoginMapper;
import com.cggw.login.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lenovo on 2018/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    LoginMapper loginMapper;

    @Test
    public void testCRUD(){
        System.out.println(loginMapper);
      /*  Login login = loginMapper.selectByPrimaryKey(1);
        System.out.println(login);*/

    }
}
