package com.hospital.entity;

// 处方
public class Recipe {
    private Integer recipeId;    // 处方ID
    private Integer doctorId;    // 医生ID
    private Integer itemId;      // 医学检查项目ID
    private String recipeName;    // 药品/医学检查（例如核磁共振，CT等）名称
    private String dosage;       // 用量
    private String units;        // 单位
    private String frequency;    // 频率
    private String days;         // 天数
    private String usage;        // 用法
    private String price;        // 单价
    private String totalPrice;   // 总金额
    private String department;   // 执行科室
    private String skinTestRes;  // 皮试结果

    private Integer remains;    // 药品库存量/医学检查剩余可做次数
    private Integer haveDone;   // 是否做过医学检查/使用过药品（0代表否，1代表是）

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Integer getRemains() {
        return remains;
    }

    public void setRemains(Integer remains) {
        this.remains = remains;
    }

    public Integer getHaveDone() {
        return haveDone;
    }

    public void setHaveDone(Integer haveDone) {
        this.haveDone = haveDone;
    }

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}