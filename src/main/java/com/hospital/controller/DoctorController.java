package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Doctor;
import com.hospital.service.DoctorService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
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
    private JSONObject register(@RequestBody Doctor doctor){
        JSONObject json = new JSONObject();
        json= doctorService.register(doctor);
        System.out.println(json.get("msg"));
        return json;
    }

    // 医生登陆【根据手机号和密码登录】
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    private JSONObject login(@RequestBody Doctor doctor){
        JSONObject json = new JSONObject();
        json= doctorService.login(doctor);
        System.out.println(doctorService.selectId(doctor.getdPhone()));
        //将登录凭证加入到医生登录成功的Session类
        this.httpServletRequest.getSession().setAttribute("LOGIN",true);
        // 保存用户id在session
        this.httpServletRequest.getSession().setAttribute("USER", doctorService.selectId(doctor.getdPhone()));
        System.out.println("登陆"+httpServletRequest.getSession().getId());
        return json;
    }

    //显示医生信息
    @RequestMapping(value = "/showdoctorinfo",method = {RequestMethod.GET})
    private JSONObject showDoctorInfo(){
        Integer id = (Integer) httpServletRequest.getSession().getAttribute("USER");
        Doctor doctorinfo = doctorService.showdoctorinfo(id);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("data",doctorinfo);
        json.put("msg","返回信息成功");
        return json;
    }

    //更新医生信息
    @RequestMapping(value = "/updatedoctorinfo",method = {RequestMethod.POST})
    private JSONObject updateDoctorInfo(@RequestBody Doctor doctor){
        Integer id = (Integer) httpServletRequest.getSession().getAttribute("USER");
        doctor.setDoctorId(id);
        doctorService.updatedoctorinfo(doctor);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","更新信息成功");
        return json;
    }

    // 修改医生密码
    @RequestMapping(value = "/updatedoctorpw",method = {RequestMethod.POST})
    private JSONObject updatepassword(@RequestParam("old_password") String old_password, @Param("new_passward") String new_password){
        Integer id = (Integer) httpServletRequest.getSession().getAttribute("USER");
        JSONObject  json=doctorService.updatedoctorpasswd(id, old_password, new_password);
        return json;
    }

    //显示医生用户名
    @RequestMapping(value = "/showDoctorName",method = {RequestMethod.GET})
    private JSONObject showDoctorName(){
        Integer id = (Integer) httpServletRequest.getSession().getAttribute("USER");
        String Name = doctorService.showName(id);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("data",Name);
        json.put("msg","返回医生成功");
        return json;
    }

}
