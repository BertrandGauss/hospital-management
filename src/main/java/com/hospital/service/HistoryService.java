package com.hospital.service;

import com.hospital.entity.History;
import com.hospital.entity.Patient;
import com.hospital.mapper.HistoryMapper;
import com.hospital.mapper.PatientMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HistoryService {
    @Resource
    private HistoryMapper historyMapper;
    @Resource
    private PatientMapper patientMapper;

    // 显示病历所属科室
    public String showdepartmentofhis(Integer doctorId) {
        return historyMapper.showdept(doctorId);
    }

    // 根据患者身份证号初始化病历, 并返回患者相关信息
    public Patient originatebypid(String pIdentificationNum){
        Integer id = historyMapper.getidBypid(pIdentificationNum);
        Patient pinfo = historyMapper.getPatientinfo(id);      // 获得患者其他相关信息
        return pinfo;
    }

    // 编辑病历
    public void updatecasehis(History history) {
        historyMapper.edit(history);
    }

    // 查看历史病历
    public List<History> showAllHisByPid(String pIdentificationNum) {
        List<History> history = historyMapper.showAllHisByPid(pIdentificationNum);
        return history;
    }

}
