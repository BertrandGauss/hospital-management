package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.*;
import com.hospital.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //登陆
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    private JSONObject login(@RequestParam("username") String username, @Param("password") String password){
        JSONObject json = new JSONObject();
        json= adminService.login(username, password);
        System.out.println(json.get("msg"));
        //将登录凭证加入到管理员登录成功的Session类
        this.httpServletRequest.getSession().setAttribute("LOGIN",true);

        return json;
    }

    // 显示所有未审核的医生注册信息
    @RequestMapping(value = "/showdoctorregister",method = {RequestMethod.GET})
    private JSONObject showDoctorRegister(){
        List<Doctor> doctors = adminService.showDoctorRegister();
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("data", doctors);
        json.put("msg","未审核医生注册信息显示完成");
        return json;
    }

    // 审核医生注册
    @RequestMapping(value = "/checkdoctorregister",method = {RequestMethod.POST})
    private JSONObject checkDoctorRegister(@RequestBody Doctor doctor){
        adminService.checkDoctorRegister(doctor);

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","医生注册审核通过");
        return json;
    }

    // 显示患者所有已缴费的项目和药品清单（根据患者身份证号）
    @RequestMapping(value = "/showpayedrecipe",method = {RequestMethod.POST})
    private JSONObject showPayedRecipe(@RequestParam("pIdentificationNum") String pIdentificationNum){
        Integer patientId = adminService.getPatientIdByPid(pIdentificationNum);
        List<Recipe> recipes = adminService.showPayedRecipe(patientId);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("data", recipes);
        json.put("msg","患者所有已缴费的项目和药品清单显示完成");
        return json;
    }

    // 对患者已缴费的项目确认进行完成
    @RequestMapping(value = "/sethavedone",method = {RequestMethod.POST})
    private JSONObject setHaveDone(@RequestBody SomeRecipe someRecipe){
        Integer patientId = adminService.getPatientIdByPid(someRecipe.getpIdentificationNum());
        adminService.setHaveDone(someRecipe);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","确认完成");
        return json;
    }

    // 获取需要已开处方需要配药的用户
    @RequestMapping(value = "/showpatienttrac",method = {RequestMethod.GET})
    private JSONObject showPatientTrac(){
        List<PatientVo> patientVos = adminService.showPatientTrac();

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("data", patientVos);
        json.put("msg","成功获取需要已开处方需要配药的用户");
        return json;
    }

    // 配药状态修改
    @RequestMapping(value = "/changetracestate",method = {RequestMethod.POST})
    private JSONObject changeTraceState(@RequestBody PatientVo patientVo){
        adminService.changeTraceState(patientVo);
        Integer patientId = patientVo.getPatientId();
        // 发药
        if(patientVo.getState() == 3){
            adminService.updateMedIsInPatient(patientId, 1);
            adminService.updateMedRemainsPut(patientVo);
        }

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","配药状态修改成功");
        return json;
    }

    // 退药
    @RequestMapping(value = "/withdrawmed",method = {RequestMethod.POST})
    private JSONObject withdrawMed(@RequestBody PatientVo patientVo){
        Integer patientId = patientVo.getPatientId();
        adminService.updateMedIsInPatient(patientId, 0);
        adminService.updateMedRemainsGet(patientVo);

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","退药审核成功");
        return json;
    }
}
