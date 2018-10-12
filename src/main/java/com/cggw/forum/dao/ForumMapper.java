package com.cggw.forum.dao;

import com.cggw.forum.domain.Forum;
import com.cggw.forum.domain.ForumAndName;
import com.cggw.forum.domain.Reply;
import com.cggw.login.domain.Login;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lenovo on 2018/7/18.
 */
@Repository
public interface ForumMapper {

    List<ForumAndName> getAll(String keyword);
    //创建一个帖子
    boolean insertIntoForum(@Param("forum") Forum forum);
    //删除一个帖子
    boolean  deleteForumBytId(Integer tId);
    //根据tId删除所有评论
    boolean deleteReplyBytId(Integer tId);
    //发评论
    boolean  insertIntoReply(@Param("reply") Reply reply);
    //删评论
    boolean  deleteReply(Integer rId);
    //发子评论
    boolean insertIntoReplyChild(@Param("reply") Reply reply);
    //删子评论
    boolean deleteReplyChild(Integer rId);
    //进入帖子后获取评论
    List<Reply> getReplyByForumId(Integer tId);
    //根据帖子获取帖标题
    String getForumById(Integer tId);
    //根据用户id获取帖子
    List<Forum> getOwnForum(int account);

}
