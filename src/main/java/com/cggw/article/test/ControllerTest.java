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
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/addArticle")
                .param("aId","1000")
                .param("aName","纪柳院长：割双眼皮疼不疼？哪种好？")
                .param("aUrl","http://120.79.241.203:8080/GoHospital/article/article1.jsp")
                .param("aTime","2018-04-11 16:34:32")
                .param("aAuthor","帆帆老师")
                .param("img1Url","http://120.79.241.203:8080/GoHospital/img/article1/1.png")
                .param("img2Url","http://120.79.241.203:8080/GoHospital/img/article1/2.png")
                .param("img3Url","http://120.79.241.203:8080/GoHospital/img/article1/3.png"))
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
                .param("keyWords","纪柳院长"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }





}
