package com.cggw.register.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
public class Registeration {
    private Integer rId;

    private Integer uId;

    private Integer dId;

    private String aType;

    private String state;

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date apTime;


    public Registeration() {
    }

    public Registeration(Integer rId, Integer uId, Integer dId, String aType, String state, Date apTime) {
        this.rId = rId;
        this.uId = uId;
        this.dId = dId;
        this.aType = aType;
        this.state = state;
        this.apTime = apTime;
    }

    @Override
    public String toString() {
        return "Registeration{" +
                "rId=" + rId +
                ", uId=" + uId +
                ", dId=" + dId +
                ", aType='" + aType + '\'' +
                ", state='" + state + '\'' +
                ", apTime=" + apTime +
                '}';
    }

}