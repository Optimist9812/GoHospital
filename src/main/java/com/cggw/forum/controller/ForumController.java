package com.cggw.forum.controller;

import com.cggw.forum.domain.Forum;
import com.cggw.forum.domain.Reply;
import com.cggw.forum.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by lenovo on 2018/7/18.
 */
@Controller
public class ForumController {

    @Autowired
    private ForumService forumService;


    @RequestMapping("getAllForum")
    public void getAllForum(Integer id,Integer tId){
        //获取所有帖子的消息
        forumService.getAllForums();
        //获取每个帖子的发帖人的姓名
        forumService.getIdName(id);
        //根据帖子id获取评论数
        forumService.queryCount(tId);
    }

    @RequestMapping("deleteForum")
    public void deleteForum(Integer fId){
        //根据forumid删除forum
        forumService.deleteForumBytId(fId);
    }

    @RequestMapping("insertIntoForum")
    public void inseriIntoForum(Forum forum){
        forumService.insertIntoForum(forum);
    }

    @RequestMapping("queryAllReply")
    public void queryAllReply(Integer tId){
        //获取该id的发帖内容
        forumService.getForumById(tId);
        //获取该id所有评论
        forumService.getReplyByForumId(tId);
    }


    @RequestMapping("deleteReply")
    public void deleteReply(Integer rId){
        //根据回帖id删除回帖
        forumService.deleteReply(rId);
        //如果评论后还有子评论，自评论也要被删除
        forumService.deleteReplyChild(rId);
    }

    @RequestMapping("addReply")
    public void addReply(Reply reply){
        //根据评论贴的添加回帖
        forumService.insertIntoReply(reply);
    }

}
