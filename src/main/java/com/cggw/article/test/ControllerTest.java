package com.cggw.article.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 * Created by cgw on 2018/8/1.
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

    @Test
    public void testAddArticle() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/addArticle")
                .param("aId","10000")
                .param("aTitle","西红柿首富")
                .param("tTime","2018-08-01")
     //           .param("aTag","搞笑")
                .param("aTag","经典")
                .param("aAddress","localhost"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    public void testdeleteArticle() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/deleteArticle")
                .param("aId","10000"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testselectArticle() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/selectArticle")
                .param("keyWords","无敌"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }





}
