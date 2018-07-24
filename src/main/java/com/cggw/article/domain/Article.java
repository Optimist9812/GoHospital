package com.cggw.article.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Article {
    private Integer aId;

    private String aTitle;

    private Date tTime;

    private String aTag;
}