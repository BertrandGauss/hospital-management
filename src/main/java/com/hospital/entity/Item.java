package com.hospital.entity;

import java.util.Date;

// 医学检查
public class Item {
    private Integer itemId;          // 医学检查项目ID
    private Integer patientId;       // 患者ID
    private Integer doctorId;        // 医生ID
    private String itemName;         // 医学检查项目名称
    private Double itemPrice;        // 单价
    private String skinTestRes;      // 皮试结果
    private Integer itemHaveDone;    // 是否进行过医学检查（0代表否，1代表是）
    private Integer havePay;         // 是否对该项目缴过费（0代表否，1代表是）
    private String illnessSummary;   // 病情摘要
    private String department;       // 执行科室
    private String checkArea;        // 检查部位
    private String notice;           // 注意事项
    private Date checkTime;          // 检查时间
    private String checkRes;         // 检查结果
    private String opinion;          // 总结意见
    private Date itemDate;           // 开检查单时间

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getSkinTestRes() {
        return skinTestRes;
    }

    public void setSkinTestRes(String skinTestRes) {
        this.skinTestRes = skinTestRes;
    }

    public Integer getItemHaveDone() {
        return itemHaveDone;
    }

    public void setItemHaveDone(Integer itemHaveDone) {
        this.itemHaveDone = itemHaveDone;
    }

    public Integer getHavePay() {
        return havePay;
    }

    public void setHavePay(Integer havePay) {
        this.havePay = havePay;
    }

    public String getIllnessSummary() {
        return illnessSummary;
    }

    public void setIllnessSummary(String illnessSummary) {
        this.illnessSummary = illnessSummary;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckArea() {
        return checkArea;
    }

    public void setCheckArea(String checkArea) {
        this.checkArea = checkArea;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getCheckRes() {
        return checkRes;
    }

    public void setCheckRes(String checkRes) {
        this.checkRes = checkRes;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setItemDate(Date itemDate) {
        this.itemDate = itemDate;
    }

    public Date getItemDate() {
        return itemDate;
    }
}
