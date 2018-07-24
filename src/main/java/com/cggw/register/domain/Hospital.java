package com.cggw.register.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Hospital {
    private Integer hId;

    private String hName;

    private String hType;

    private String hAddr;

    private String hPic;

    private Double hScore;

    private String hAccess;

    @Override
    public String toString() {
        return "Hospital{" +
                "hId=" + hId +
                ", hName='" + hName + '\'' +
                ", hType='" + hType + '\'' +
                ", hAddr='" + hAddr + '\'' +
                ", hPic='" + hPic + '\'' +
                ", hScore=" + hScore +
                ", hAccess='" + hAccess + '\'' +
                '}';
    }

    public Hospital() {
    }

    public Hospital(Integer hId, String hName, String hType, String hAddr, String hPic, Double hScore, String hAccess) {
        this.hId = hId;
        this.hName = hName;
        this.hType = hType;
        this.hAddr = hAddr;
        this.hPic = hPic;
        this.hScore = hScore;
        this.hAccess = hAccess;
    }

    public Hospital(Integer hId) {
        this.hId = hId;
    }
}