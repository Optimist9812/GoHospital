package com.cggw.article.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Getter @Setter
public class Article {

    private Integer aId;

    private String aName;

    private String aUrl;

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date aTime;

    private String aAuthor;

    private String img1Url;

    private String img2Url;

    private String img3Url;

    public Article(Integer aId, String aName, String aUrl, Date aTime, String aAuthor, String img1Url, String img2Url, String img3Url) {
        this.aId = aId;
        this.aName = aName;
        this.aUrl = aUrl;
        this.aTime = aTime;
        this.aAuthor = aAuthor;
        this.img1Url = img1Url;
        this.img2Url = img2Url;
        this.img3Url = img3Url;
    }

    public Article() {
    }

    @Override
    public String toString() {
        return "Article{" +
                "aId=" + aId +
                ", aName='" + aName + '\'' +
                ", aUrl='" + aUrl + '\'' +
                ", aTime=" + aTime +
                ", aAuthor='" + aAuthor + '\'' +
                ", img1Url='" + img1Url + '\'' +
                ", img2Url='" + img2Url + '\'' +
                ", img3Url='" + img3Url + '\'' +
                '}';
    }
}