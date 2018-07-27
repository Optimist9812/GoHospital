package com.cggw.forum.controller;

import com.cggw.forum.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by lenovo on 2018/7/18.
 */
@Controller("forumController")
public class ForumController {

    @Autowired
    private ForumService forumService;


    @RequestMapping("getAllForum")
    public void getAllForum(HttpServletResponse response){

    }


}
