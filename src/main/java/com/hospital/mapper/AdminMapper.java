package com.hospital.mapper;

import com.hospital.entity.Doctor;
import com.hospital.entity.Item;
import com.hospital.entity.Patient;
import com.hospital.entity.Record;

import java.util.List;

public interface AdminMapper {
    void checkDoctorRegister(Doctor doctor);

    List<Doctor> showDoctorRegister();

    List<Item> selectitemsbypId(Integer patientId);

    List<Record> selectmedbypId(Integer patientId);

    Integer getPatientIdByPid(String pIdentificationNum);




}
