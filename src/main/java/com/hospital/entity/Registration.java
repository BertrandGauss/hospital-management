package com.example.demo.entity;

// 挂号
public class Registration {
    public Integer registrationId;  // 挂号ID
    public Integer patientId;       // 患者ID
    public Integer doctorId;        // 医生ID
    public String rNum;             // 排队号（例如1,v1...）

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
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

    public String getrNum() {
        return rNum;
    }

    public void setrNum(String rNum) {
        this.rNum = rNum;
    }
}
