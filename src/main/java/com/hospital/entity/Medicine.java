package com.hospital.entity;

public class Medicine {
    private String medName;     //药品名称
    private Integer remains;    //药品库存量

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public Integer getRemains() {
        return remains;
    }

    public void setRemains(Integer remains) {
        this.remains = remains;
    }
}
