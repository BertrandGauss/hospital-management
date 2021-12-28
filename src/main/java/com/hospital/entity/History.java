package com.hospital.entity;

import java.util.Date;

// 病历
public class History {
    public Integer historyId;      // 病历ID
    public Integer patientId;      // 患者ID
    public String clinicType;      // 就诊类型
    public Date diseaseDate;       // 发病日期，格式形如XXXX-XX-XX
    public String diagnosis;       // 诊断
    public String drugAllergyHis;  // 药敏史
    public String chiefComplaint;  // 主诉
    public String presentIllness;  // 现病史

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
