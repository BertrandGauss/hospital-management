package com.hospital.mapper;

import com.hospital.entity.*;
import org.apache.ibatis.annotations.Param;

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

    void updateMedIsInPatient(@Param("patientId") Integer patientId,@Param("medHaveDone") Integer medHaveDone);

    void updateMedRemainsPut(PatientVo patientVo);

    void updateMedRemainsGet(PatientVo patientVo);
}
