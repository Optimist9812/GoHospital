package com.cggw.forum.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Forum {

    private Integer tId;

    private Integer id;

    private String tTitle;

    private Date tDate;

    private String fContent;

    @Override
    public String toString() {
        return "Forum{" +
                "tId=" + tId +
                ", id=" + id +
                ", tTitle='" + tTitle + '\'' +
                ", tDate=" + tDate +
                ", fContent='" + fContent + '\'' +
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
}