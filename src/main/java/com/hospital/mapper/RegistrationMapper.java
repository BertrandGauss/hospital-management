package com.hospital.mapper;

import com.hospital.entity.Registration;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegistrationMapper {
    void add(Registration registration);

    Registration selectById(Integer patientId);

    List<String> TodayRNum(@Param("department")String department, @Param("doctorId")Integer doctorId);

    void updateValid(Integer patientId);

    Integer selectByrNum(Registration registration);

    Integer selectPatient(Registration registration);
}
