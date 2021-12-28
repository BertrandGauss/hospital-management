package com.hospital.entity;

// 病人
public class Patient {
    private Integer patientId;     // 患者ID
    private String pUsername;      // 患者登录用户名
    private String pPassword;      // 患者登录密码
    private String pIdentificationNum; // 患者身份证号
    private String pName;          // 患者姓名
    private String pGender;        // 患者性别（F代表女性，M代表男性）
    private Integer pAge;          // 患者年龄
    private String pOccupation;    // 患者职业
    private String pPhone;         // 患者联系电话
    private String pAddress;       // 患者家庭住址
    private Integer isOrder;       // 是否预约（0代表未预约，1代表已预约）
    private Integer orderTimes;    // 取消预约次数
    private Integer isInBlacklist; // 是否在黑名单（0代表不在，1代表在）

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getpUsername() {
        return pUsername;
    }

    public void setpUsername(String pUsername) {
        this.pUsername = pUsername;
    }

    public String getpPassword() {
        return pPassword;
    }

    public void setpPassword(String pPassword) {
        this.pPassword = pPassword;
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

    public Integer getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(Integer isOrder) {
        this.isOrder = isOrder;
    }

    public Integer getOrderTimes() {
        return orderTimes;
    }

    public void setOrderTimes(Integer orderTimes) {
        this.orderTimes = orderTimes;
    }

    public Integer getIsInBlacklist() {
        return isInBlacklist;
    }

    public void setIsInBlacklist(Integer isInBlacklist) {
        this.isInBlacklist = isInBlacklist;
    }
}
