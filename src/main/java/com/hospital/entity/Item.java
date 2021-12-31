package com.hospital.entity;

import java.util.Date;

// 医学检查
public class Item {
    private Integer itemId;          // 医学检查项目ID
    private String illnessSummary;   // 病情摘要
    private String department;       // 执行科室
    private String checkArea;        // 检查部位
    private String notice;           // 注意事项
    private Date checkTime;          // 检查时间
    private String checkRes;         // 检查结果
    private String opinion;          // 总结意见

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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
}
