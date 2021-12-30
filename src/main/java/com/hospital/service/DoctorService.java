package com.hospital.service;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Doctor;
import com.hospital.mapper.DoctorMapper;
import com.hospital.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DoctorService {
    @Resource
    private DoctorMapper doctorMapper;

    public  Integer didisregister(String dIdentificationNum){
        Integer id = doctorMapper.selectByIdentificationNum(dIdentificationNum);
        System.out.println(id);
        return id;

    }

    // 注册
    public JSONObject register(Doctor doctor){
        // TODO: 管理员核验医生身份
        doctor.setdPassword(MD5Util.md5(doctor.getdPassword()));   //给医生密码加密
        doctorMapper.add(doctor);         //注册验证成功，将医生插入到数据库中
        JSONObject json = new JSONObject();

        json.put("msg","注册成功");
        json.put("code",0);
        return json;

    }

    // 登录
    public JSONObject login(Doctor doctor){
        String did =  doctor.getdIdentificationNum();
        String pw = MD5Util.md5(doctor.getdPassword());
        String passw = doctorMapper.selectpwbydid(did);

        System.out.println("pw"+pw);
        System.out.println("paw"+passw);

        if(passw==null){
            System.out.println("paw"+passw);
            JSONObject json = new JSONObject();
            json.put("msg","该用户不存在");
            json.put("code",1);
            return json;
        }
        else if(!pw.equals(passw)){
            JSONObject json = new JSONObject();
            json.put("msg","密码错误");
            json.put("code",2);
            return json;
        }
        JSONObject json = new JSONObject();

        json.put("msg","登录成功");
        json.put("code",0);
        return json;
    }

    // 显示医生信息
    public Doctor showdoctorinfo(Integer doctorId){
        Doctor doctor = doctorMapper.selectbyid(doctorId);
        return doctor;
    }

    // 更新医生信息
    public void updatedoctorinfo(Doctor doctor){
        doctorMapper.update(doctor);
    }

    public JSONObject updatedoctorpasswd(Integer id,String old_pw,String new_pw){
        JSONObject json = new JSONObject();
        String origin_pw=doctorMapper.selectpwbyid(id);
        String pw=MD5Util.md5(old_pw);

        if(!origin_pw.equals(pw)){
            json.put("code",1);System.out.println("旧密码错误");
            json.put("msg","旧密码输入错误");
        }
        else{
            doctorMapper.updatepw(id,MD5Util.md5(new_pw));
            json.put("code",0);
            json.put("msg","成功修改密码");
        }
        return json;
    }
}
