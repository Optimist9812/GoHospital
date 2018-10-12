package com.cggw.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.cggw.login.domain.Login;
import com.cggw.login.service.LoginService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/7/12.
 */
@Controller
public class LoginController {

    private static int ACCOUNT =15203211;

    @Resource
    private LoginService loginService;

    public void setEmployeeService(LoginService employeeService) {
        this.loginService = employeeService;
    }

    public LoginController() {
    }

    /**
     *  发送短信验证码（待测试）
     *  通过tel进行生成验证码，将验证码传到前端
     *  isSuccess 是否成功
     *  code  验证码
     *
     *  value = map.get(key)  //根据key获取value
     */
    @ResponseBody
    @RequestMapping(value="sendMessage")
    public ModelAndView sendMessage(String tel,ModelAndView model) throws IOException {
        System.out.println(tel);
        Map<Integer,Object> map  = loginService.sendMessage(tel);
        model.addObject("isSuccess",map.get(1));
        model.addObject("code",map.get(2));
        return model;
    }

    /**
     * 账号登陆（测试成功）
     * @param model
     * @return
     * @throws IOException
     * 通过tel进行登陆，可以是验证码登陆，也可以是密码登陆（flag为1是密码，0是验证码）
     * 1.tel查找是user还是doctor
     * 2.验证码登陆到后端，说明已经验证成功，此时只需要提供token
     * 3.密码登陆需要通过tel查找id的密码进行匹配登陆是否成功，成功则传递token和id
     *
     * 1.getIdByTel  根据tel查找是user还是doctor
     * 2.selectByPrimaryKey  根据account获取login
     */
    @ResponseBody
    @RequestMapping(value = "login")
    public ModelAndView login(int tel,int password,int flag,ModelAndView model) throws IOException {
        boolean isSuccess = false;
        int account = loginService.getIdBytel(tel);//获取account
        Login login = new Login(account);
        String token = null;
        if(flag == 0){
            isSuccess = true;
            token = getToken(login);
            model.addObject("isSuccess",isSuccess);
        }else if(flag == 1){
            System.out.println(loginService.getPassByAccount(account));
            if(loginService.getPassByAccount(account) == password){
                isSuccess = true;
                token = getToken(login);
            }
            model.addObject("isSuccess",isSuccess);
        }else{
            model.addObject("isSuccess",isSuccess);
        }
        model.addObject("token",token);
        return model;
    }

    /**
     *    生成token（已完成）
     */
    private String getToken(Login login){
        return JwtHelper.createJWT(login.getAccount());
    }


    /**
     * 注册功能（测试成功）
     * @param tel  电话
     * @param name  昵称
     * @param password  密码
     * @param model
     * @return
     * @throws IOException
     * user表插入一部分用户信息，电话以及随机生成id，然后将id和密码，昵称插入login表中。
     * 1.随机生成id函数
     * 2.将id 和 电话插入到user表中
     * 3.将id 和name 插入到login表中
     * 4.根据id生成token传送给前端
     *
     */
    //@ResponseBody 加上这个注解就可以返回数据到页面
    @ResponseBody
    @RequestMapping("registerAccount")
    public ModelAndView registerAccount(int tel,String name,int password,ModelAndView model) throws IOException {
        boolean isSuccess = false;
        String token ;
        Login login = new Login(ACCOUNT, String.valueOf(password), null, name);
        if(loginService.insertIntoUser2(String.valueOf(tel),ACCOUNT)&&loginService.insertIntoLogin(login)){
            isSuccess = true;
            token = getToken(login);
            model.addObject("token",token);
        }
        model.addObject("isSuccess",isSuccess);
        ACCOUNT++;
        return model;
    }


    /**
     * 用于测试是否成功连接
     * @return
     */
    @RequestMapping("test")
    public String test(){
        return "1234";
    }

    /**
     * 忘记密码(已测试)
     * @param tel
     * @param password
     * @return
     * 进入此方法说明已经能够进行密码修改了，只需要做两步
     * 1.利用tel将id找出来
     * 2.在login表中将password修改成新的密码
     */
    @ResponseBody
    @RequestMapping("forgetPass")
    public ModelAndView forgetPass(int tel,int password,ModelAndView model){
        int account = loginService.getIdBytel(tel);
        String token = getToken(new Login(account));
        Boolean isSuccess = loginService.updatePass(account,password);
        model.addObject("token",token);
        model.addObject("isSuccess",isSuccess);
        return model;
    }



}