package com.hospital.mapper;

import com.hospital.entity.*;

import java.util.List;

public interface AdminMapper {
    void checkDoctorRegister(Doctor doctor);

    List<Doctor> showDoctorRegister();

    List<Item> selectitemsbypId(Integer patientId);

    List<Record> selectmedbypId(Integer patientId);

    Integer getPatientIdByPid(String pIdentificationNum);

    Integer patientIsOrdered(Patient patient);

    List<PatientVo> showPatientTrac();

    void updateTrace(PatientVo patientVo);

    void updateMedIsInPatient(Integer patientId, Integer medHaveDone);

    void updateMedRemainsPut(PatientVo patientVo);

    void updateMedRemainsGet(PatientVo patientVo);
}
