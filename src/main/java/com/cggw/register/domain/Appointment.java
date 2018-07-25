package com.cggw.register.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Appointment{
    private Integer dId;

    private Date apTime;

    private String apType;

    private Integer apMax;

    private Integer apRemain;

    public Appointment(Integer dId, Date apTime, String apType, Integer apMax, Integer apRemain) {
        this.dId = dId;
        this.apTime = apTime;
        this.apType = apType;
        this.apMax = apMax;
        this.apRemain = apRemain;
    }

    public Appointment() {
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "dId=" + dId +
                ", apTime=" + apTime +
                ", apType='" + apType + '\'' +
                ", apMax=" + apMax +
                ", apRemain=" + apRemain +
                '}';
    }

}