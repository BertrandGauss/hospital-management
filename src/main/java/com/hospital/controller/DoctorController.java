package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Patient;
import com.hospital.entity.PatientVo;
import com.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

}
