package com.hospital.mapper;

import com.hospital.entity.History;
import com.hospital.entity.Patient;

import java.util.List;

public interface HistoryMapper {
    String showdept(Integer doctorId);  // 显示病历所属科室

    Integer getidBypid(String pIdentificationNum); // 根据患者身份证号获取ID

    Patient getPatientinfo(Integer patientId);


    void edit(History history); // 编辑病历

    List<History> showAllHisByPid(String pIdentificationNum); // 查看历史病历

    List<History> showAllHis();

    History selectById(Integer historyId);
}
