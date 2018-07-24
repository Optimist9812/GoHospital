package com.cggw.register.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Doctor {
    private Integer dId;

    private String dName;

    private String dSex;

    private String dTel;

    private String hId;

    private String hType;

    private String hDept;

    private String hRoom;

    private Double dScore;

    private String dPic;

    private String dIntroduction;

    public Doctor(Integer dId, String dName, String dSex, String dTel, String hId, String hType, String hDept, String hRoom, Double dScore, String dPic, String dIntroduction) {
        this.dId = dId;
        this.dName = dName;
        this.dSex = dSex;
        this.dTel = dTel;
        this.hId = hId;
        this.hType = hType;
        this.hDept = hDept;
        this.hRoom = hRoom;
        this.dScore = dScore;
        this.dPic = dPic;
        this.dIntroduction = dIntroduction;
    }

    public Doctor(Integer dId) {
        this.dId = dId;
    }

    public Doctor() {
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "dId=" + dId +
                ", dName='" + dName + '\'' +
                ", dSex='" + dSex + '\'' +
                ", dTel='" + dTel + '\'' +
                ", hId='" + hId + '\'' +
                ", hType='" + hType + '\'' +
                ", hDept='" + hDept + '\'' +
                ", hRoom='" + hRoom + '\'' +
                ", dScore=" + dScore +
                ", dPic='" + dPic + '\'' +
                ", dIntroduction='" + dIntroduction + '\'' +
                '}';
    }
}