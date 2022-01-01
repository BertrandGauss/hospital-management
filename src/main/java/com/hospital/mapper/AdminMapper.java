package com.hospital.mapper;

import com.hospital.entity.Doctor;
import java.util.List;

public interface AdminMapper {
    void checkDoctorRegister(Doctor doctor);

    List<Doctor> showDoctorRegister();
}
