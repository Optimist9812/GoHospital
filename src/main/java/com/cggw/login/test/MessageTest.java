package com.cggw.login.test;



import org.apache.commons.httpclient.Header;
import org.junit.Test;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.Random;

/**
 * Created by lenovo on 2018/7/17.
 */
public class MessageTest {


    @Test
    public void testMessage() throws IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
        NameValuePair[] data = { new NameValuePair("Uid", "cggw"), // 注册的用户名
                new NameValuePair("Key", "d41d8cd98f00b204e980"), // 注册成功后,登录网站使用的密钥
                new NameValuePair("smsMob", "18262638635"), // 手机号码
                new NameValuePair("smsText",  "验证码:1234【oppo公司】") };//设置短信内容

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
    }

    @Test
    public void random(){
        String str = "a b c d e f g h i j k l m n o p q r s t u v w x y z 0 1 2 3 4 5 6 7 8 9";
        String[] objects = str.split(" ");
        for(int i=0;i<objects.length;i++){
            System.out.print(objects[i]);
        }
        String code = "验证码为:";
        Random random = new Random();
        for(int i = 0;i < 4;i++){
            String node = objects[random.nextInt(36)];
            String newcode = code.concat(node);//code.concat()之后是一个新的字符串
            code = newcode;
        }
        System.out.println(code);
    }
}

