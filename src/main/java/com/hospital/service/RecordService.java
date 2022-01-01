package com.hospital.service;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Recipe;
import com.hospital.entity.Record;
import com.hospital.entity.Trace;
import com.hospital.mapper.MedicineMapper;
import com.hospital.mapper.RecordMapper;
import com.hospital.mapper.TraceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RecordService {
    @Resource
    private RecordMapper recordMapper;
    @Resource
    private MedicineMapper medicineMapper;
    @Resource
    private TraceMapper traceMapper;

    // 开处方
    public JSONObject addRecord(Record record) {
        JSONObject json = new JSONObject();
        Integer remains = medicineMapper.selectNumByName(record.getMedName());
        if(remains==null){
            json.put("code",1);
            json.put("msg","药品库中不存在该药");
        }else if(remains<record.getDosage()){
            json.put("code",2);
            json.put("msg","药品库中不存在该药");
        }else{
            //添加处方记录，并更新药品库存
            recordMapper.add(record);
            medicineMapper.updateRemains(record.getDosage());
            //等待配药
            Trace trace = new Trace();
            trace.setPatientId(record.getPatientId());
            traceMapper.addTrace(trace);
            json.put("code",0);
            json.put("msg","开处方成功");
        }
        return json;

    }
}
