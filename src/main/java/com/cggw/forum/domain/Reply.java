package com.cggw.forum.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Reply {

    private Integer rId;

    private Integer tId;

    private Date rTime;

    private Integer id;

    private Integer stId;

    private String rContent;

    public Reply(Integer rId, Integer tId, Date rTime, Integer id, Integer stId, String rContent) {
        this.rId = rId;
        this.tId = tId;
        this.rTime = rTime;
        this.id = id;
        this.stId = stId;
        this.rContent = rContent;
    }

    public Reply() {
    }

    @Override
    public String toString() {
        return "Reply{" +
                "rId=" + rId +
                ", tId=" + tId +
                ", rTime=" + rTime +
                ", id=" + id +
                ", stId=" + stId +
                ", rContent='" + rContent + '\'' +
                '}';
    }
}