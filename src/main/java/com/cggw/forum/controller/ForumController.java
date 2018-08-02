package com.cggw.forum.controller;

import com.cggw.forum.domain.Forum;
import com.cggw.forum.domain.ForumAndName;
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


    /**
     * 首页获取所有内容（测试成功）
     * @return
     */
    @RequestMapping("getAllForum")
    @ResponseBody
    //该注解用于将Controller方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后（如：json格式），写入到Response对象的body数据区。
    public  List<ForumAndName> getAllForum(){
        List<ForumAndName> list = forumService.getAll();
        return list;
    }

    /**
     * 删除一个帖子，包括forum，reply中的内容（测试成功）
     * @param tId
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteForum")
    public boolean deleteForum(Integer tId){
        //根据forumid删除forum
        forumService.deleteReplyBytId(tId);
        return forumService.deleteForumBytId(tId);
    }

    /**
     *创建一个帖子(测试成功)
     * @param forum
     * @return
     */
    @ResponseBody
    @RequestMapping("insertIntoForum")
    public boolean inserIntoForum(Forum forum){
        System.out.println("进入insertIntoForum");
        return forumService.insertIntoForum(forum);
    }

    /**
     * 点击帖子显示帖子所有评论(测试成功)
     * @param tId
     * @return
     * @throws ParseException
     */
    @RequestMapping("queryAllReply")
    @ResponseBody
    public ModelAndView queryAllReply(Integer tId,ModelAndView model) throws ParseException {
        //获取该id的发帖内容
        String tContent = forumService.getForumById(tId);
        System.out.println("获取json数据");
        //获取该id所有评论
        List<Reply> list = forumService.getReplyByForumId(tId);
        model.addObject("tContent",tContent);
        model.addObject("queryAllReply",list);
        return model;
    }


    /**
     * 删除所有自评论（待测试）
     * @param rId
     */
    @ResponseBody
    @RequestMapping("deleteReply")
    public boolean deleteReply(Integer rId){
        boolean isSuccess = false;
        //根据回帖id删除回帖
        isSuccess = forumService.deleteReply(rId);
        return isSuccess;
    }


    /**
     * 添加评论（测试成功）
     * @param reply
     * @return
     */
    @ResponseBody
    @RequestMapping("addReply")
    public boolean addReply(Reply reply){
        //根据评论贴的添加回帖
        return forumService.insertIntoReply(reply);
    }

    //根据帖子获取所有评论
}
