package com.cggw.register.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
public class Underline {
    private Integer rId;

    private Integer uId;

    private Integer dId;

    private String aType;

    private Integer hId;

    private String hDept;

    private String hRoom;

    private String state;

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date apTime;

    public Underline(Integer rId, Integer uId, Integer dId, String aType, Integer hId, String hDept, String hRoom, String state, Date apTime) {
        this.rId = rId;
        this.uId = uId;
        this.dId = dId;
        this.aType = aType;
        this.hId = hId;
        this.hDept = hDept;
        this.hRoom = hRoom;
        this.state = state;
        this.apTime = apTime;
    }

    public Underline() {
    }

    @Override
    public String toString() {
        return "Underline{" +
                "rId=" + rId +
                ", uId=" + uId +
                ", dId=" + dId +
                ", aType='" + aType + '\'' +
                ", hId=" + hId +
                ", hDept='" + hDept + '\'' +
                ", hRoom='" + hRoom + '\'' +
                ", state='" + state + '\'' +
                ", apTime=" + apTime +
                '}';
    }
}