package com.hospital.mapper;

import com.hospital.entity.*;

public interface DoctorMapper {
    // 显示科室
    String showOffice(Integer doctorId);

    // 查询患者主要信息
    PatientVo showPatientInfo(String pIdentificationNum);

    // 编辑病历
    void editCaseHistory1(PatientVo patientVo);
    void editClinicType(String clinicType, Integer historyId);
    void editDiseaseDate(String diseaseDate, Integer historyId);
    void editDrugAllergyHis(String drugAllergyHis, Integer historyId);
    void editChiefComplaint(String chiefComplaint, Integer historyId);
    void editPresentIllness(String presentIllness, Integer historyId);

    // 开处方
    void getRecipe (RecipeVo recipeVo);

    // 开检查检验单
    void produceCheck (Item item);

    // 根据病人身份证号，查看病历
    History viewCaseHistory(String pIdentificationNum);

}
