package com.hospital.entity;


//用来接收挂号信息
public class RegistrationVo {
    private String pIdentificationNum; // 患者身份证号
    private String department;         // 科室
    private String dName;              // 医生姓名

    public String getpIdentificationNum() {
        return pIdentificationNum;
    }

    public void setpIdentificationNum(String pIdentificationNum) {
        this.pIdentificationNum = pIdentificationNum;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }
}
