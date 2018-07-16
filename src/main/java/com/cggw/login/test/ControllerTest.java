package com.cggw.login.test;

import com.cggw.login.controller.LoginController;
import com.cggw.login.domain.Login;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@WebAppConfiguration
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
     * 测试Controller  login()
     * @throws Exception
     */
    @Test
    public void login() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/login")
                .param("account","3")
                .param("password","123")
                .param("flag","1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void register() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/register")
                .param("account","")
                .param("password","123")
                .param("flag","1")
                .param("name","1234"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
