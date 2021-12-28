package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hospital.entity.Patient;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    //注册
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    private JSONObject register(@RequestBody Patient patient){
        JSONObject json = new JSONObject();
        json= patientService.register(patient);
        System.out.println(json.get("msg"));
        return json;
    }

    //登陆
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    private JSONObject login(@RequestBody Patient patient){
        JSONObject json = new JSONObject();
        json= patientService.login(patient);
        System.out.println(json.get("msg"));
        //将登录凭证加入到用户登录成功的Session类
        this.httpServletRequest.getSession().setAttribute("LOGIN",true);
        //保存用户id在session
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", patientService.phoneisregister(patient.getpPhone()));
//        Integer id=(int)httpServletRequest.getSession().getAttribute("LOGIN_USER");
//
//        System.out.println("get"+id);
//        System.out.println("sessionId"+httpServletRequest.getSession().getId());
        return json;
    }
}
