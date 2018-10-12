package com.cggw.forum.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class Forum {

    private Integer tId;

    private Integer id;

    //帖子标题
    private String tTitle;

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date tDate;

    //描述
    private String fContent;

    private String imgurl1;

    private String imgurl2;

    private String imgurl3;

    @Override
    public String toString() {
        return "Forum{" +
                "tId=" + tId +
                ", id=" + id +
                ", tTitle='" + tTitle + '\'' +
                ", tDate=" + tDate +
                ", fContent='" + fContent + '\'' +
                ", imgurl1='" + imgurl1 + '\'' +
                ", imgurl2='" + imgurl2 + '\'' +
                ", imgurl3='" + imgurl3 + '\'' +
                '}';
    }

    public Forum(Integer tId, Integer id, String tTitle, Date tDate, String fContent) {
        this.tId = tId;
        this.id = id;
        this.tTitle = tTitle;
        this.tDate = tDate;
        this.fContent = fContent;
    }

    public Forum() {
    }

    public Forum(Integer tId, Integer id, String tTitle, Date tDate, String fContent, String imgurl1, String imgurl2, String imgurl3) {
        this.tId = tId;
        this.id = id;
        this.tTitle = tTitle;
        this.tDate = tDate;
        this.fContent = fContent;
        this.imgurl1 = imgurl1;
        this.imgurl2 = imgurl2;
        this.imgurl3 = imgurl3;
    }
}