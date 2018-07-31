package com.cggw.article.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Date;

@Getter @Setter
public class Article {
    private Integer aId;

    private String aTitle;

    private Date tTime;

    private String[] aTag;

    private String aAddress;

    public Article(Integer aId, String aTitle, Date tTime, String[] aTag, String aAddress) {
        this.aId = aId;
        this.aTitle = aTitle;
        this.tTime = tTime;
        this.aTag = aTag;
        this.aAddress = aAddress;
    }

    public Article() {
    }

    @Override
    public String toString() {
        return "Article{" +
                "aId=" + aId +
                ", aTitle='" + aTitle + '\'' +
                ", tTime=" + tTime +
                ", aTag=" + Arrays.toString(aTag) +
                ", aAddress='" + aAddress + '\'' +
                '}';
    }
}