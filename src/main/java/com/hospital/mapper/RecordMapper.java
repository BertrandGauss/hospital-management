package com.hospital.mapper;

import com.hospital.entity.Record;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RecordMapper {
    void add (Record record);

    void setMedHaveDone(@Param("patientId") Integer patientId, @Param("medName") String medName );

    List<Record> selectmed();

    List<Record> selectmedGet();

    List<Record> selectmedbypIdentificationNum(String pIdentificationNum);

    List<Integer> selectById(Integer patientId);

    Date getRdate(@Param("patientId") Integer patientId, @Param("medName") String medName );
}
