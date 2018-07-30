package com.cggw.forum.test;


import com.cggw.forum.dao.ForumMapper;
import com.cggw.forum.domain.Forum;
import com.cggw.forum.domain.Reply;
import com.cggw.login.domain.Login;
import com.cggw.register.dao.AppointmentMapper;
import com.cggw.register.dao.RegisterationAndUnderlineMapper;
import com.cggw.register.domain.Appointment;
import com.cggw.register.domain.Registeration;
import com.cggw.register.domain.Underline;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lenovo on 2018/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    ForumMapper forumMapper;

    //获取所有帖子的消息
    //List<Forum> getAllForum();
    @Test
    public void testGetAllForum(){
        List<Forum> list = forumMapper.getAllForums();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //创建一个帖子
    //boolean insertIntoForum(@Param("forum") Forum forum);
    @Test
    public void testInsertIntoForum() throws ParseException {
        Forum forum = new Forum(10,1,"111",new SimpleDateFormat("yyyyMMdd").parse("20180101"),"aaaa");
        System.out.println(forumMapper.insertIntoForum(forum));

    }
    //删除一个帖子
    //boolean  deleteForumBytId(Integer tId);
    @Test
    public void testDeleteForumBytId(){
        System.out.println(forumMapper.deleteForumBytId(10));
    }
    //发评论
    //boolean  insertIntoReply(Reply reply);
   // public Reply(Integer rId, Integer tId, Date rTime, Integer id, Integer stId, String rContent) {

    @Test
    public void testInsertIntoReply(){
        Reply reply = new Reply(1,2,null,1,null,"aa");
        forumMapper.insertIntoReply(reply);
    }
    //删评论
    //boolean  deleteReply(Integer rId);
    @Test
    public void testDeleteReply(){
        System.out.println(forumMapper.deleteReply(1));
    }

    //进入帖子后获取评论
    //List<Reply> getReplyByForumId(Integer tId);
    @Test
    public void testGetReplyByForumId(){
        List<Reply> list = forumMapper.getReplyByForumId(1);
        Iterator<Reply> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //搜索发帖人的姓名,图像  (图片如何进行传输？？)
    //Login getIdName(Integer id);
    @Test
    public void testGetIdName(){

    }

    //搜索发帖人的评论数
    //int queryCount(Integer tId);
    @Test
    public void testqueryCount(){
        System.out.println(forumMapper.queryCount(1));
    }

    //获取发帖内容
    //String getForumById(Integer fid);
    @Test
    public void testGetForumById(){
        System.out.println(forumMapper.getForumById(10));
    }

}
