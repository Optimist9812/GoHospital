package com.cggw.login.controller;

import com.alibaba.fastjson.JSONObject;

import com.cggw.login.domain.Login;
import com.cggw.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lenovo on 2018/7/12.
 */
@Controller
public class LoginController {

    @Resource
    private LoginService loginService;

    public void setEmployeeService(LoginService employeeService) {
        this.loginService = employeeService;
    }

    public LoginController() {
        System.out.println("*1**");
    }

    /**
     *  发送短信验证码（已完成）
     */
    @RequestMapping("sendMessage")
    public void sendMessage() {
        loginService.sendMessage();
    }

    /**
     *  根据前端传入的Login对用户登录进行检验(已完成)
     */
    @RequestMapping(value = "login")
    public void login(Login login, HttpServletResponse response) throws IOException {
        boolean isSuccess = false;
        PrintWriter out = null;
        String token = getToken();
        switch (login.getFlag()) {
            case "0":isSuccess=loginService.getLoginByMessage(login);break;
            case "1":isSuccess=loginService.getLoginByPass(login);
        }
        System.out.println("*****");
        JSONObject json = new JSONObject();
        out = response.getWriter();
        json.put("isSuccess",isSuccess);
        json.put("token",token);
        out.print(json.toString());
    }

    /**
     *    生成token（待完成）
     */
    @RequestMapping("test")
    public String getToken(){
        System.out.println("1234");
        return "a";
    }

    @RequestMapping("register")
    public void register(Login login,HttpServletResponse response) throws IOException {
        boolean isSuccess = false;
        PrintWriter out = null;
        String token = getToken();
        if(loginService.insertIntoLogin(login)&&loginService.insertIntoUser(login)){
            isSuccess = true;
        }
        JSONObject json = new JSONObject();
        out = response.getWriter();
        json.put("isSuccess",isSuccess);
        json.put("token",token);
        out.print(json.toString());
    }
}