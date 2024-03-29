package com.hospital.entity;

import java.sql.Time;
import java.util.Date;

// 预约
public class Order {
    private Integer orderId;     // 预约ID
    private Integer patientId;   // 患者ID
    private Integer doctorId;    // 医生ID
    private String dName;        // 医生名字
    private String department;   // 预约科室
    private String oType;        // 预约类型（只能为专家预约或科室预约）
    private Date oDate;          //预约日期
    private Time oTime;          // 预约时段
    private Integer isValid;     // 预约是否有效（0代表无效，1代表有效）

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

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId){
        this.doctorId = doctorId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getoType() {
        return oType;
    }

    public void setoType(String oType) {
        this.oType = oType;
    }

    public Date getoDate() {
        return oDate;
    }

    public void setoDate(Date oDate) {
        this.oDate = oDate;
    }

    public Time getoTime() {
        return oTime;
    }

    public void setoTime(Time oTime) {
        this.oTime = oTime;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
