package com.cggw.forum.service;

import com.cggw.forum.dao.ForumMapper;
import com.cggw.forum.domain.Forum;
import com.cggw.forum.domain.Reply;
import com.cggw.login.domain.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2018/7/18.
 */
@Service("forumService")
public class ForumServiceImpl implements ForumService {

    @Autowired
    private ForumMapper forumMapper ;


    @Override
    public List<Forum> getAllForums() {
        return null;
    }

    @Override
    public Login getIdName(Integer id) {
        return null;
    }

    @Override
    public int queryCount(Integer tId) {
        return 0;
    }

    @Override
    public boolean insertIntoForum(Forum forum) {
        if(forumMapper.insertIntoForum(forum)){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteForumBytId(Integer tId) {
        if(forumMapper.deleteForumBytId(tId)){
            return true;
        }
        return false;
    }

    @Override
    public boolean insertIntoReply(Reply reply) {
        if(forumMapper.insertIntoReply(reply)){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReply(Integer rId) {
        if(forumMapper.deleteReply(rId)){
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteReplyChild(Integer rId) {
        if(forumMapper.deleteReplyChild(rId)){
            return true;
        }
        return false;
    }

    @Override
    public List<Reply> getReplyByForumId(Integer tId) {
       return null;
    }

    @Override
    public String getForumById(Integer fId) {
        System.out.println(fId);
        System.out.println(forumMapper);
        String s = forumMapper.getForumById(fId);
        return s;
    }
}
