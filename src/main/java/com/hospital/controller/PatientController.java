package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.service.PatientService;
import com.hospital.entity.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

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

    //显示用户信息
    @RequestMapping(value = "/showpatientinfo",method = {RequestMethod.GET})
    private JSONObject showPatientInfo(){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        Patient patientinfo = patientService.showpatientinfo(id);

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("data",patientinfo);
        json.put("msg","返回用户信息成功");
        return json;
    }

    //更新用户信息
    @RequestMapping(value = "/updatepatientinfo",method = {RequestMethod.POST})
    private JSONObject updatePatientInfo(@RequestBody Patient patient){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        patient.setPatientId(id);
        patientService.updatepatientinfo(patient);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","更新用户信息成功");
        return json;
    }

    //修改用户密码
    @RequestMapping(value = "/updatepatientpw",method = {RequestMethod.POST})
    private JSONObject updatepassword(@RequestParam("old_password") String old_password, @Param("new_passward") String new_password){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        JSONObject  json=patientService.updatepatientpasswd(id, old_password, new_password);
        return json;
    }

    //显示取消次数
    @RequestMapping(value = "/showcancleorder",method = {RequestMethod.GET})
    private JSONObject showPatientdanger(){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        Integer cancleorder = patientService.showdanger(id);

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("data",cancleorder);
        json.put("msg","返回取消次数成功");
        return json;
    }
    //显示进度
    @RequestMapping(value = "/showTrce",method = {RequestMethod.GET})
    private JSONObject showTrace(){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        String trace = patientService.showTrace(id);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("data",trace);
        json.put("msg","返回进度");
        return json;
    }

}
