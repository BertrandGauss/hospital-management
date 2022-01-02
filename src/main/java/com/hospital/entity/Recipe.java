package com.hospital.entity;


//处方，包括检查和药品,用来接收前端开的药品，和方便缴费退费显示,缴费页面显示的处方单就是这样的
public class Recipe {
    private Integer patientId;    // 患者ID
    private String recipeName;   // 检查或药品的名字
    private Integer dosage;      // 用量
    private Double price;        // 单价

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Integer getDosage() {
        return dosage;
    }

    public void setDosage(Integer dosage) {
        this.dosage = dosage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
