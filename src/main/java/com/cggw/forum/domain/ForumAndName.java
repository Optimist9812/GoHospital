package com.cggw.forum.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by cgw on 2018/8/2.
 */
@Getter @Setter
public class ForumAndName {

    //帖子id
    private int tId;
    //帖子名称
    private int tTitle;
    //帖子内容
    private String tContent;
    //发帖日期
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date tDate;
    //用户url
    private String pic;
    //用户评论数
    private int count;
    //用户昵称
    private int uName;

    public ForumAndName(int tId, int tTitle, String tContent, Date tDate, String pic, int count, int uName) {
        this.tId = tId;
        this.tTitle = tTitle;
        this.tContent = tContent;
        this.tDate = tDate;
        this.pic = pic;
        this.count = count;
        this.uName = uName;
    }

    public ForumAndName() {
    }

    @Override
    public String toString() {
        return "ForumAndName{" +
                "tId=" + tId +
                ", tTitle=" + tTitle +
                ", tContent='" + tContent + '\'' +
                ", tDate=" + tDate +
                ", pic='" + pic + '\'' +
                ", count=" + count +
                ", uName=" + uName +
                '}';
    }
}
