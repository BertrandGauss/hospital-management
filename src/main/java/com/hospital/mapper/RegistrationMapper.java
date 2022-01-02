package com.hospital.mapper;

import com.hospital.entity.Registration;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegistrationMapper {
    void add(Registration registration);

    Registration selectById(Integer patientId);

    List<String> TodayRNumz(@Param("department")String department, @Param("doctorId")Integer doctorId);

    List<String> TodayRNum(String department);

    void updateValid(Integer patientId);

    Integer selectByrNum(Registration registration);

    Integer selectPatient(Registration registration);

    String selectPre(Integer patientId);
}
