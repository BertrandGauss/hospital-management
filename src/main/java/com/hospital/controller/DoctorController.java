package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Patient;
import com.hospital.entity.PatientVo;
import com.hospital.service.DoctorService;
import com.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    // 医生注册
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    private JSONObject register(@RequestBody Patient patient){
        JSONObject json = new JSONObject();
        json= patientService.register(patient);
        System.out.println(json.get("msg"));
        return json;
    }

}
