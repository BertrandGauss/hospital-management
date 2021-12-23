package com.example.demo.entity;

// 预约
public class Order {
    public Integer orderId;     // 预约ID
    public Integer patientId;   // 患者ID
    public String oType;        // 预约类型（只能为专家预约或科室预约）
    public String oTime;        // 预约时段
    public Integer oNum;        // 预约号（例如1,2…）
    public Integer isValid;     // 预约是否有效（0代表无效，1代表有效）

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getoType() {
        return oType;
    }

    public void setoType(String oType) {
        this.oType = oType;
    }

    public String getoTime() {
        return oTime;
    }

    public void setoTime(String oTime) {
        this.oTime = oTime;
    }

    public Integer getoNum() {
        return oNum;
    }

    public void setoNum(Integer oNum) {
        this.oNum = oNum;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
