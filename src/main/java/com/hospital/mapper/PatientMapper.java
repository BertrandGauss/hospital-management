package com.hospital.mapper;


import com.hospital.entity.Patient;
import org.apache.ibatis.annotations.Param;

public interface PatientMapper {
    void add(Patient patient);

    Integer selectByIdentificationNum(String pIdentificationNum);

    Integer selectidbyphone(String pPhone);

    String selectpwbyphone(String pPhone);

    Patient selectbyid(Integer patientId);

    void update(Patient patient);

    String selectPasswdById(Integer patientId);

    void updatepw(@Param("patientId") Integer patientId, @Param("pPassword") String pPassword);

    Integer showcancleorder(Integer patientId);

    Integer selectBlacklist(Integer patientId);

    void updateorderTimes(Integer patientId);

    void  updateBlacklist(Integer patientId);


}
