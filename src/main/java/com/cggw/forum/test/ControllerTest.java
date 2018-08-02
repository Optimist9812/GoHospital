package com.cggw.forum.test;

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
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(Context).build();
    }


    /**
     * 首页获取所有内容
     * 测试成功
     * @throws Exception
     */
    @Test
    public void testgetAllForum() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getAllForum"))
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    /**
     * 创建一个帖子
     * 测试成功
     * @throws Exception
     */
   @Test
    public void testinsertIntoForum() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/insertIntoForum")
                .param("tId","101")
                .param("id","1")
                .param("tTitle","快来看看")
                .param("tDate","2018-02-01 10:01:01")
                .param("fContent","来看看你"))
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }


    /**
     * 删除一个帖子
     * @throws Exception
     */
    @Test
    public void testdeleteForum() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/deleteForum")
                .param("tId","101"))
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    /**
     * 获取一个帖子所有评论
     * @throws Exception
     */
    @Test
    public void testqueryAllReply() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/queryAllReply")
                .param("tId","1"))
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }

    /**
     * 添加评论
     * @throws Exception
     */
    //public Reply(Integer rId, Integer tId, Date rTime, Integer id, Integer stId, String rContent) {
        @Test
    public void testaddReply() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/addReply")
                .param("rId","1")
                .param("tId","101")
                .param("rTime","2018-02-01 10:01:01")
                .param("id","2")
                .param("stId","")
                .param("rContent","我来看看"))
                .andDo(MockMvcResultHandlers.print()).andReturn();
    }

    /**
     * 删除一个帖子
     * @throws Exception
     */
    @Test
    public void testdeleteReply() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/deleteForum")
                .param("tId","101"))
                .andDo(MockMvcResultHandlers.print()).andReturn();

    }
}