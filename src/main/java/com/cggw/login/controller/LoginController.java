package com.cggw.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.cggw.login.domain.Login;
import com.cggw.login.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    }

    /**
     *  发送短信验证码（已完成）
     */
    @ResponseBody
    @RequestMapping("sendMessage")
    public boolean sendMessage(Integer number) throws IOException {
        return  loginService.sendMessage(number);
       /* System.out.println("****");
        PrintWriter out = null;
        JSONObject json = new JSONObject();
        out = response.getWriter();
        json.put("isSuccess",isSuccess);
        out.print(json.toString());*/
    }

    /**
     *  根据前端传入的Login对用户登录进行检验(已完成)
     */
    @ResponseBody
    @RequestMapping(value = "login")
    public ModelAndView login(Login login, ModelAndView model) throws IOException {
        boolean isSuccess = false;
        String token = getToken();
        switch (login.getFlag()) {
            case "0":isSuccess=loginService.getLoginByMessage(login);break;
            case "1":isSuccess=loginService.getLoginByPass(login);
        }
        model.addObject("token",token);
        model.addObject("isSuccess",isSuccess);
        return model;
       /* JSONObject json = new JSONObject();
        out = response.getWriter();
        json.put("isSuccess",isSuccess);
        json.put("token",token);
        out.print(json.toString());*/
    }

    /**
     *    生成token（待完成）
     */
    @ResponseBody
    @RequestMapping("/test")
    public String getToken(){
        System.out.println("login中test方法测试完成。");
        return "a";
    }

    //@ResponseBody 加上这个注解就可以返回数据到页面
    @ResponseBody
    @RequestMapping("registerAccount")
    public ModelAndView registerAccount(Login login,ModelAndView model) throws IOException {
        boolean isSuccess = false;
        PrintWriter out = null;
        String token = getToken();
        if(loginService.insertIntoLogin(login)&&loginService.insertIntoUser(login)){
            isSuccess = true;
        }
        model.addObject("token",token);
        model.addObject("isSuccess",isSuccess);
        return model;
       /* JSONObject json = new JSONObject();
        out = response.getWriter();
        json.put("isSuccess",isSuccess);
        json.put("token",token);
        out.print(json.toString());*/
    }
}