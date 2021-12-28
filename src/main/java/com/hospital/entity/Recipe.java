package com.hospital.entity;

// 处方
public class Recipe {
    private Integer recipeId;    // 处方ID
    private Integer doctorId;    // 医生ID
    private Integer itemId;      // 条项ID
    private String dosage;       // 用量
    private String units;        // 单位
    private String frequency;    // 频率
    private String days;         // 天数
    private String skinTestRes;  // 皮试结果

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getSkinTestRes() {
        return skinTestRes;
    }

    public void setSkinTestRes(String skinTestRes) {
        this.skinTestRes = skinTestRes;
    }
}