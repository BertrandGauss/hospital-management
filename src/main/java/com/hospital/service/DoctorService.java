package com.hospital.service;

import com.hospital.entity.PatientVo;
import com.hospital.mapper.DoctorMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DoctorService {
    @Resource
    private DoctorMapper doctorMapper;

    // 显示科室
    public String showOffice(Integer doctorId){
        return doctorMapper.showOffice(doctorId);
    }

    // 显示病人基本信息
    public PatientVo showPatientInfo(String pIdentificationNum){
        return doctorMapper.showPatientInfo(pIdentificationNum);
    }

    // 编辑病历
    public void editCaseHistory1(PatientVo patientVo, Integer historyId, String clinicType, String diseaseDate,
                                 String drugAllergyHis, String chiefComplaint, String presentIllness){
        doctorMapper.editCaseHistory1(patientVo);
        doctorMapper.editClinicType(clinicType, historyId);
        doctorMapper.editDiseaseDate(diseaseDate, historyId);
        doctorMapper.editChiefComplaint(diseaseDate, historyId);
        doctorMapper.editDrugAllergyHis(drugAllergyHis, historyId);
        doctorMapper.editChiefComplaint(chiefComplaint, historyId);
        doctorMapper.editPresentIllness(presentIllness, historyId);
    }
}
