package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Doctor;
import com.hospital.entity.Patient;
import com.hospital.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    // 审核医生注册
    @RequestMapping(value = "/checkdoctorregister",method = {RequestMethod.POST})
    private JSONObject checkDoctorRegister(@RequestBody Doctor doctor){
        adminService.checkDoctorRegister(doctor);

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","审核通过");
        return json;
    }
}
