package com.cggw.login.service;

import com.cggw.login.dao.LoginMapper;
import com.cggw.login.domain.Login;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Random;

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
     * 获取验证短信（已完成）
    */
    @Override
    public boolean sendMessage(Integer number) throws IOException {
        //随机生成验证码
        String code = getRandom();

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = { new NameValuePair("Uid", "cggw"), // 注册的用户名
                new NameValuePair("Key", "d41d8cd98f00b204e980"), // 注册成功后,登录网站使用的密钥
                new NameValuePair("smsMob", number+""), // 手机号码
                new NameValuePair("smsText",  code+"【oppo公司】") };//设置短信内容
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes(
                "gbk"));
        System.out.println(result);
        post.releaseConnection();
        if(result.equals("1")) return true;
        return false;
    }

    /**
     * 随机生成短信验证码
     * @return
     */
    @Override
    public String getRandom() {
        String str = "a b c d e f g h i j k l m n o p q r s t u v w x y z 0 1 2 3 4 5 6 7 8 9";
        String[] objects = str.split(" ");
        for(int i=0;i<objects.length;i++){
            System.out.print(objects[i]);
        }
        String code = "验证码为:";
        String node = "";
        Random random = new Random();
        for(int i = 0;i < 4;i++){
            node= objects[random.nextInt(36)];
            code=code + node;
        }
        System.out.println(code);
        return code;
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
