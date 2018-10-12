package com.cggw.login.test;

import com.cggw.login.controller.LoginController;
import com.cggw.login.domain.Login;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by lenovo on 2018/7/12.
 */
@WebAppConfiguration  //@webappconfiguration是一级注释，用于声明一个ApplicationContext集成测试加载
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/spring-servlet.xml"})
public class ControllerTest {

    @Autowired
    WebApplicationContext Context;

    MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc= MockMvcBuilders.webAppContextSetup(Context).build();
    }

    /**
     * 测试成功
     * @throws Exception
     */
    @Test
    public void login() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/login")
                .param("tel","123456789")
                .param("password","12347")
                .param("flag","1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    /**
     * 测试成功
     * @throws Exception
     */
    @Test
    public void register() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/registerAccount")
                .param("account","1001")
                .param("password","123")
                .param("flag","1")
                .param("name","1234"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //忘记密码

    /**
     * int tel,int password
     * @throws Exception
     */
    @Test
    public void testForgetPass() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/forgetPass")
                .param("tel","123456789")
                .param("password","12347"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //注册功能（待测试）

    /**
     * int tel,String name,int password
     * @throws Exception
     */
    @Test
    public void testRegisterAccount() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/registerAccount")
                .param("tel","185236521")
                .param("name","cgw")
                .param("password","12345"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //账号登陆

    /**
     * int tel,int password,int flag
     * @throws Exception
     */
    @Test
    public void testLogin() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("tel","123456789")
                .param("flag","0")
                .param("password","123"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //发送验证码

    /**
     * 报400错误，说明无法发送一个json格式到Controller层
     * 参数类型不匹配  400
     * Integer tel
     * @throws Exception
     */
    @Test
    public void testSendMessage() throws Exception {
       /* String tel = "18262638635";
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson  = ow.writeValueAsString(tel);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/sendMessage")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();*/

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sendMessage")
                .param("tel","18262638635"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

       /* String errorBody = "{tel:18262638635}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/sendMessage")
                .contentType(MediaType.APPLICATION_JSON).content(errorBody)
                .accept(MediaType.APPLICATION_JSON)) //执行请求
            //    .andExpect(status().isBadRequest()) //400错误请求
                .andDo(MockMvcResultHandlers.print())
                .andReturn();*/

    }
}
