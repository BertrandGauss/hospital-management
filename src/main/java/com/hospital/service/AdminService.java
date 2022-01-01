package com.hospital.service;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Doctor;
import com.hospital.mapper.AdminMapper;
import com.hospital.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;
    //登陆
    public JSONObject login(String username, String password){
        String pw = MD5Util.md5(password);
        String defaultuser = "admin";
        String defaultpass = "123456";
        JSONObject json = new JSONObject();
        if(!username.equals(defaultuser)){
            json.put("msg","用户名错误");
            json.put("code",1);

            return json;
        }else if(!password.equals(defaultpass)) {
            json.put("msg","密码错误");
            json.put("code",2);

            return json;
        }
        json.put("msg","登录成功");
        json.put("code",0);
        return json;

    }

    // 审核医生账号
    public void checkDoctorRegister(Doctor doctor) {
        adminMapper.checkDoctorRegister(doctor);
    }
}
