package com.cggw.forum.dao;

import com.cggw.forum.domain.Forum;
import com.cggw.forum.domain.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lenovo on 2018/7/18.
 */
public interface ForumMapper {
    //获取所有帖子的消息
    List<Forum> getAllForum();
    //创建一个帖子
    boolean insertIntoForum(@Param("forum") Forum forum);
    //删除一个帖子
    boolean  deleteForumBytId(Integer tId);
    //发评论
    boolean  insertIntoReply(Reply reply);
    //删评论
    boolean  deleteReply(Integer rId);
    //发子评论
    boolean insertIntoReplyChild(Reply reply);
    //删子评论
    boolean deleteReplyChild(Integer rId);
    //进入帖子后获取评论
    boolean getReplyByForumId(Integer tId);

}
