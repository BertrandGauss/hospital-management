package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Record;
import com.hospital.entity.RegistrationVo;
import com.hospital.service.RecordService;
import com.hospital.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    // 挂号
    @RequestMapping(value = "/addregistration", method = {RequestMethod.POST})
    private JSONObject addRegistration(@RequestBody RegistrationVo registrationVo){
        JSONObject json = registrationService.Registration(registrationVo);
        return json;
    }
}
