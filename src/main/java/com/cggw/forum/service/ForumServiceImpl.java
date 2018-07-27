package com.cggw.forum.service;

import com.cggw.forum.dao.ForumMapper;
import com.cggw.forum.domain.Forum;
import com.cggw.forum.domain.Reply;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2018/7/18.
 */
@Service("forumService")
public class ForumServiceImpl implements ForumService {

    private ForumMapper forumMapper;

    @Override
    public List<Forum> getAllForum() {
        return forumMapper.getAllForum();
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
    public boolean insertIntoReplyChild(Reply reply) {
        if(forumMapper.insertIntoReplyChild(reply)){
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
    public boolean getReplyByForumId(Integer tId) {
        if(forumMapper.getReplyByForumId(tId)){
            return true;
        }
        return false;
    }
}
