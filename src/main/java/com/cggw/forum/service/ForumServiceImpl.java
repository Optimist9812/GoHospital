package com.cggw.forum.service;

import com.cggw.forum.dao.ForumMapper;
import com.cggw.forum.domain.Forum;
import com.cggw.forum.domain.ForumAndName;
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
    public List<ForumAndName> getAll() {
        return forumMapper.getAll();
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
    public boolean deleteReplyBytId(Integer tId) {
        return forumMapper.deleteReplyBytId(tId);
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
        return forumMapper.getReplyByForumId(tId);
    }

    @Override
    public String getForumById(Integer tId) {
        return forumMapper.getForumById(tId);
    }


}
