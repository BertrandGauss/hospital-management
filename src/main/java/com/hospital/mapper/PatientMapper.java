package com.hospital.mapper;


import com.hospital.entity.Patient;

public interface PatientMapper {
    void add(Patient patient);

    Integer selectByIdentificationNum(String pIdentificationNum);

    Integer selectidbyphone(String pPhone);

    String selectpwbyphone(String pPhone);

    Patient selectbyid(Integer patientId);

    void update(Patient patient);
}
