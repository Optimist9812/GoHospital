package com.cggw.forum.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by cgw on 2018/8/2.
 */
@Getter @Setter
public class ForumAndName {

    private int tId;

    private int Name;

    private String tContent;

    private String pic;

    private int count;

    public ForumAndName(int tId, int name, String tContent, String pic, int count) {
        this.tId = tId;
        Name = name;
        this.tContent = tContent;
        this.pic = pic;
        this.count = count;
    }

    public ForumAndName() {
    }


    @Override
    public String toString() {
        return "ForumAndName{" +
                "tId=" + tId +
                ", Name=" + Name +
                ", tContent='" + tContent + '\'' +
                ", pic='" + pic + '\'' +
                ", count=" + count +
                '}';
    }
}
