package com.cggw.register.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Hospdept {
    private Integer hId;

    private String hDept;

    private String hRoom;

    public Hospdept() {
    }

    public Hospdept(Integer hId, String hDept, String hRoom) {
        this.hId = hId;
        this.hDept = hDept;
        this.hRoom = hRoom;
    }

    @Override
    public String toString() {
        return "Hospdept{" +
                "hId=" + hId +
                ", hDept='" + hDept + '\'' +
                ", hRoom='" + hRoom + '\'' +
                '}';
    }
}