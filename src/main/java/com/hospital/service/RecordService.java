package com.hospital.service;

import com.hospital.entity.Recipe;
import com.hospital.entity.Record;
import com.hospital.mapper.RecordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RecordService {
    @Resource
    private RecordMapper recordMapper;

    // 开处方
    public void addRecord(Record record) {
        recordMapper.add(record);
    }
}
