package com.hospital.entity;

// 医生
public class Doctor {
    private Integer doctorId;    // 医生ID
    private String dUsername;    // 医生登录用户名
    private String dPassword;    // 医生登录密码
    private Integer isValid;     // 判断医生账号是否有效
    private String dName;        // 医生姓名
    private String dIdentificationNum;  // 医生身份证号
    private String dPhone;       //医生手机号
    private String dOffice;      // 医生所在科室
    private String dTitle;       // 医生职称（只能为专家或普通）
    private String dSkill ;      // 医生的擅长简介

    private Integer patientId;      // 正在给谁看病

    public String getdIdentificationNum() {
        return dIdentificationNum;
    }

    public void setdIdentificationNum(String dIdentificationNum) {
        this.dIdentificationNum = dIdentificationNum;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getdUsername() {
        return dUsername;
    }

    public void setdUsername(String dUsername) {
        this.dUsername = dUsername;
    }

    public String getdPassword() {
        return dPassword;
    }

    public void setdPassword(String dPassword) {
        this.dPassword = dPassword;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }


    public String getdPhone() {
        return dPhone;
    }

    public void setdPhone(String dPhone) {
        this.dPhone = dPhone;
    }

    public String getdOffice() {
        return dOffice;
    }

    public void setdOffice(String dOffice) {
        this.dOffice = dOffice;
    }

    public String getdTitle() {
        return dTitle;
    }

    public void setdTitle(String dTitle) {
        this.dTitle = dTitle;
    }

    public String getdSkill() {
        return dSkill;
    }

    public void setdSkill(String dSkill) {
        this.dSkill = dSkill;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}