package com.hospital.mapper;

public interface MedicineMapper {
    Integer selectNumByName(String medName);

    void updateRemains(Integer dosage);
}
