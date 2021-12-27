package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hospital.entity.Patient;

@RestController
@RequestMapping("/patient")
public class UserController {

    @Autowired
    private PatientService patientService;

    //注册
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    private JSONObject register(@RequestBody Patient patient){
        JSONObject json = new JSONObject();
        json= patientService.register(patient);
        System.out.println(json.get("msg"));
        return json;
    }
}
