package com.cggw.forum.dao;

import com.cggw.forum.domain.Forum;
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

    //获取所有帖子的消息
    List<Forum> getAllForums();
    //搜索发帖人的姓名,图像  (图片如何进行传输？？)
    Login getIdName(Integer id);
    //搜索发帖人的评论数
    int queryCount(Integer tId);
    //获取发帖内容
    String getForumById(Integer tId);
    //创建一个帖子
    boolean insertIntoForum(@Param("forum") Forum forum);
    //删除一个帖子
    boolean  deleteForumBytId(Integer tId);
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

}
