package com.hospital.entity;

import java.util.Date;

// 病历
public class History {
    private Integer historyId;      // 病历ID
    private Integer patientId;      // 患者ID
    private String pIdentificationNum;  // 患者身份证号
    private String clinicType;      // 就诊类型
    private Date diseaseDate;       // 发病日期，格式形如XXXX-XX-XX
    private String diagnosis;       // 诊断
    private String drugAllergyHis;  // 药敏史
    private String chiefComplaint;  // 主诉
    private String presentIllness;  // 现病史

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

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

    public String getClinicType() {
        return clinicType;
    }

    public void setClinicType(String clinicType) {
        this.clinicType = clinicType;
    }

    public Date getDiseaseDate() {
        return diseaseDate;
    }

    public void setDiseaseDate(Date diseaseDate) {
        this.diseaseDate = diseaseDate;
    }

    public String getDrugAllergyHis() {
        return drugAllergyHis;
    }

    public void setDrugAllergyHis(String drugAllergyHis) {
        this.drugAllergyHis = drugAllergyHis;
    }

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }

    public String getPresentIllness() {
        return presentIllness;
    }

    public void setPresentIllness(String presentIllness) {
        this.presentIllness = presentIllness;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
