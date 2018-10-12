package com.cggw.login.test;

import com.cggw.login.dao.LoginMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lenovo on 2018/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class MapperTest {


    @Autowired
    LoginMapper loginMapper;

    @Test
    public void testCRUD(){
        System.out.println(loginMapper);
    }

    //将id 和 电话插入到user表中（待测试）
    @Test
    public void testInsertIntoUser2(){
        loginMapper.insertIntoUser2("123456789",30256);
    }

    //根据tel获取id（user表中）（待测试）
    @Test
    public void testGetIdBytel(){
        System.out.println(loginMapper.getIdBytel(123456789));
    }

    //根据account 获取password进行比较（待测试）
    @Test
    public void testGetPassByAccount(){
        System.out.println(loginMapper.getPassByAccount(123));
    }

    //更新密码
    //Boolean  updatePass(int account,int pass);
    @Test
    public void testUpdatePass(){
        System.out.println(loginMapper.updatePass(123,120));
    }
}
