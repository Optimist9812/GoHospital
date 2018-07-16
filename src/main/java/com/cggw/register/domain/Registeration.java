package com.cggw.register.domain;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Registeration {
    private Integer rId;

    private Integer uId;

    private Integer dId;

    private String aType;

    private String state;

    public Registeration(Integer rId, Integer uId, Integer dId, String aType, String state) {
        this.rId = rId;
        this.uId = uId;
        this.dId = dId;
        this.aType = aType;
        this.state = state;
    }

    public Registeration() {
    }

    @Override
    public String toString() {
        return "Registeration{" +
                "rId=" + rId +
                ", uId=" + uId +
                ", dId=" + dId +
                ", aType='" + aType + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}