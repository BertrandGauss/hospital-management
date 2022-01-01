package com.hospital.mapper;

import com.hospital.entity.Trace;
import org.apache.ibatis.annotations.Param;

public interface TraceMapper {
    void addTrace(Trace trace);

    void updateTrace(@Param("patientId")Integer patientId,@Param("state") Integer state);

    Integer selectById(Integer patientId);
}
