package com.hospital.entity;

// 处方
public class Record {
    private Integer recordId;    // 开药记录ID
    private String medName;      // 药品名称
    private Integer doctorId;    // 医生ID
    private Integer patientId;   // 患者ID
    private Integer dosage;      // 用量
    private String units;        // 单位
    private String frequency;    // 频率
    private Integer days;        // 天数
    private String usage;        // 用法
    private Double medPrice;     // 单价

    private Integer medHaveDone; // 是否做过医学检查/使用过药品（0代表否，1代表是）
    private Integer havePay;     // 是否缴费（0代表否，1代表是）


    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

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


    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }


    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getDosage() {
        return dosage;
    }

    public void setDosage(Integer dosage) {
        this.dosage = dosage;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getMedPrice() {
        return medPrice;
    }

    public void setMedPrice(Double medPrice) {
        this.medPrice = medPrice;
    }

    public Integer getMedHaveDone() {
        return medHaveDone;
    }

    public void setMedHaveDone(Integer medHaveDone) {
        this.medHaveDone = medHaveDone;
    }

    public Integer getHavePay() {
        return havePay;
    }

    public void setHavePay(Integer havePay) {
        this.havePay = havePay;
    }
}