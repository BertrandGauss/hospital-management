package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Item;
import com.hospital.entity.Record;
import com.hospital.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    // 开具处方
    @RequestMapping(value = "/addRecord", method = {RequestMethod.POST})
    private JSONObject addRecord(@RequestBody Record record){
        Integer did=(Integer) httpServletRequest.getSession().getAttribute("LOGIN_USER");//医生ID
        Integer pid=(Integer) httpServletRequest.getSession().getAttribute("Patient");//患者ID
        record.setDoctorId(did);
        record.setPatientId(pid);
        JSONObject json = recordService.addRecord(record);
        return json;
    }

}
