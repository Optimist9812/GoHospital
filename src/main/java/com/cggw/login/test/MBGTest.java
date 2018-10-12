package com.cggw.login.test;

import com.cggw.login.controller.JwtHelper;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/7/12.
 */
public class MBGTest {


    public static void main(String[] args) throws Exception {
        System.out.println(new JwtHelper().createJWT(1234));
        String str = new JwtHelper().createJWT(1234);
        System.out.println( new JwtHelper().parseJWT(str).get("account"));
    }
}
