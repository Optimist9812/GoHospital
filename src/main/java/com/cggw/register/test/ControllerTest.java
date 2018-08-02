package com.cggw.register.test;

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

/**
 * Created by cgw on 2018/7/29.
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
     * 测试成功，但有待进行分页测试，有待进行添加地理位置测试
     * @throws Exception
     */
    //根据地理位置获取推荐医院
    @Test
    public void testgetRecommendHosp() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getRecommendHosp"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 测试成功
     * @throws Exception
     */
    @Test
    public void testgetHospdept() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getHospdept")
                .param("hId","1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    /**
     * 测试成功
     * @throws Exception
     */
    @Test
    public void testgetHospdeptRoom() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getHospdeptRoom")
                .param("hId","1")
                .param("hDept","1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 测试成功
     * @throws Exception
     */
    @Test
    public void testgetDocByDept() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getDocByDept")
                .param("hId","1")
                .param("hDept","1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 测试成功
     * @throws Exception
     */
    @Test
    public void testqueryDoc() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/queryDoc")
                .param("dId","1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 测试成功
     * @throws Exception
     */
    @Test
    public void testqueryDept() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/queryDept")
                .param("hDept","儿科")
                .param("hId","1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 测试成功
     * 未能成功进入主要是因为date类型数据不匹配，在domain的register中进行设置dateformat即可。
     * @throws Exception
     */
   @Test
    public void testregister() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/register")
                .param("rId","107")
                .param("uId","2")
                .param("dId","2")
                .param("aType","线上")
                .param("state","no")
                .param("apTime","2018-01-01 0:0:0"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 测试完成
     *取消线上预约
     */
    @Test
    public void testcancelRegisteration() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/cancelRegisteration")
                .param("rId","107")
                .param("uId","2")
                .param("dId","2")
                .param("aType","线上")
                .param("state","no")
                .param("apTime","2018-01-01 0:0:0"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 测试完成
     * @throws Exception
     */
    @Test
    public void testchangeState() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/changeState")
                .param("id","102"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    /**
     * 测试成功
     * @throws Exception
     */
    @Test
    public void testshowRegisteration() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/showRegisteration")
                .param("uId","1"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }







}
