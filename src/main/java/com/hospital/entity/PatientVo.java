package com.hospital.entity;

// 病人
public class PatientVo {
    public Integer patientId;     // 患者ID
    public String pIdentificationNum; // 患者身份证号
    public String pName;          // 患者姓名
    public String pGender;        // 患者性别（F代表女性，M代表男性）
    public Integer pAge;          // 患者年龄
    public String pOccupation;    // 患者职业
    public String pPhone;         // 患者联系电话
    public String pAddress;       // 患者家庭住址

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getpIdentificationNum() {
        return pIdentificationNum;
    }

    public void setpIdentificationNum(String pIdentificationNum) {
        this.pIdentificationNum = pIdentificationNum;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpGender() {
        return pGender;
    }

    public void setpGender(String pGender) {
        this.pGender = pGender;
    }

    public Integer getpAge() {
        return pAge;
    }

    public void setpAge(Integer pAge) {
        this.pAge = pAge;
    }

    public String getpOccupation() {
        return pOccupation;
    }

    public void setpOccupation(String pOccupation) {
        this.pOccupation = pOccupation;
    }

    public String getpPhone() {
        return pPhone;
    }

    public void setpPhone(String pPhone) {
        this.pPhone = pPhone;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

}
