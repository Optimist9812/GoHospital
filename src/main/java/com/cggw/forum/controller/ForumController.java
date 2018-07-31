package com.cggw.forum.controller;

import com.cggw.forum.domain.Forum;
import com.cggw.forum.domain.Reply;
import com.cggw.forum.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by lenovo on 2018/7/18.
 */
@Controller
public class ForumController {

    @Autowired
    private ForumService forumService;


    @RequestMapping("getAllForum")
    @ResponseBody
    //该注解用于将Controller方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后（如：json格式），写入到Response对象的body数据区。
    public ModelAndView getAllForum(Integer id, Integer tId, ModelAndView model){
        //获取所有帖子的消息
        model.addObject("allForums",forumService.getAllForums());
        //获取每个帖子的发帖人的姓名
        model.addObject("name",forumService.getIdName(id));
        //根据帖子id获取评论数
        model.addObject("totalCount",forumService.queryCount(tId));
        return model;
    }

    @ResponseBody
    @RequestMapping("deleteForum")
    public boolean deleteForum(Integer fId){
        //根据forumid删除forum
        return forumService.deleteForumBytId(fId);
    }

    @ResponseBody
    @RequestMapping("insertIntoForum")
    public boolean inseriIntoForum(Forum forum){
        return forumService.insertIntoForum(forum);
    }

    @RequestMapping("queryAllReply/{tId}")
    @ResponseBody
    public String queryAllReply(@PathVariable("tId") Integer tId) throws ParseException {
        //获取该id的发帖内容
        String forumById = forumService.getForumById(tId);
        System.out.println("获取json数据");
        //获取该id所有评论
        forumService.getReplyByForumId(tId);
        return forumById;
    }

    /**
     * 有问题，循环删除所有自评论
     * @param rId
     */
    @ResponseBody
    @RequestMapping("deleteReply")
    public boolean deleteReply(Integer rId){
        boolean isSuccess = false;
        //根据回帖id删除回帖
        isSuccess = forumService.deleteReply(rId);
        //如果评论后还有子评论，自评论也要被删除
        isSuccess = forumService.deleteReplyChild(rId);
        return isSuccess;
    }


    @ResponseBody
    @RequestMapping("addReply")
    public boolean addReply(Reply reply){
        //根据评论贴的添加回帖
        return forumService.insertIntoReply(reply);
    }

}
