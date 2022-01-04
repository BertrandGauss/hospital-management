package com.hospital.entity;


import java.util.Date;

//处方，包括检查和药品,用来接收前端开的药品，和方便缴费退费显示,缴费页面显示的处方单就是这样的
public class Recipe {
    private Integer patientId;      //患者ID
    private String pIdentificationNum; //患者的身份证号
    private String Type;           //类别
    private String recipeName;   // 检查或药品的名字
    private Double price;        // 金额
    private String dName;        // 医生名字
    private Date oDate;          // 开单时间
    private String state;        // 当前状态
    private Integer dosage;      // 用量

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public void setpIdentificationNum(String pIdentificationNum) {
        this.pIdentificationNum = pIdentificationNum;
    }

    public String getpIdentificationNum() {
        return pIdentificationNum;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getdName() {
        return dName;
    }

    public Date getRdate() {
        return oDate;
    }

    public void setRdate(Date oDate) {
        this.oDate = oDate;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getDosage() {
        return dosage;
    }

    public void setDosage(Integer dosage) {
        this.dosage = dosage;
    }
}
