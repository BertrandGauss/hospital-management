package com.hospital.entity;

// 处方
public class Recipe {
    public Integer recipeId;    // 处方ID
    public Integer doctorId;    // 医生ID
    public Integer itemId;      // 条项ID
    public String dosage;       // 用量
    public String units;        // 单位
    public String frequency;    // 频率
    public String days;         // 天数
    public String skinTestRes;  // 皮试结果

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