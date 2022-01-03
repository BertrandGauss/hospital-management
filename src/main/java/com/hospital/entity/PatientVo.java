package com.hospital.entity;

import java.util.Date;

public class PatientVo {
    private Integer patientId;     // 患者ID
    private String pName;          // 患者姓名
    private String pIdentificationNum; // 患者身份证号
    private Integer state;
    private Integer dosage;      // 用量
    private String medName;     //药品名称
    private Date rDate;         //开单时间

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpIdentificationNum() {
        return pIdentificationNum;
    }

    public void setpIdentificationNum(String pIdentificationNum) {
        this.pIdentificationNum = pIdentificationNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDosage() {
        return dosage;
    }

    public void setDosage(Integer dosage) {
        this.dosage = dosage;
    }

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }
}
