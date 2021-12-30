package com.hospital.service;

import com.alibaba.fastjson.JSONObject;
import com.hospital.mapper.PatientMapper;
import org.springframework.stereotype.Service;
import com.hospital.entity.Patient;
import com.hospital.utils.MD5Util;

import javax.annotation.Resource;

@Service
public class PatientService {
    @Resource
    private PatientMapper patientMapper;

    public  Integer idnumberisregister(String pIdentificationNum){
        Integer id = patientMapper.selectByIdentificationNum(pIdentificationNum);
        System.out.println(id);
        return id;

    }

    public  Integer phoneisregister(String pPhone){
        Integer id = patientMapper.selectidbyphone(pPhone);
        System.out.println(id);
        return id;

    }

    //注册
    public JSONObject register(Patient patient){
        Integer re=idnumberisregister(patient.getpIdentificationNum());
        Integer res=phoneisregister(patient.getpPhone());

        //开始校验
        if(re!=null){
            JSONObject json = new JSONObject();
            json.put("msg","该身份证号码被注册过");
            json.put("code",1);
            return json;
        }
        if(res!=null){
            JSONObject json = new JSONObject();
            json.put("msg","该手机号被注册过");
            json.put("code",2);
            return json;
        }
        patient.setpPassword(MD5Util.md5(patient.getpPassword()));//给用户密码加密
        patientMapper.add(patient);//注册验证成功，将用户插入到数据库中
        JSONObject json = new JSONObject();

        json.put("msg","注册成功");
        json.put("code",0);
        return json;

    }

    //登陆
    public JSONObject login(Patient patient){
        String telephone=patient.getpPhone();

        String pw=MD5Util.md5(patient.getpPassword());
        String passw=patientMapper.selectpwbyphone(telephone);

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

    //显示用户信息
    public Patient showpatientinfo(Integer patiendId){
        Patient patient = patientMapper.selectbyid(patiendId);
        return patient;
    }

    //更新用户信息
    public void updatepatientinfo(Patient patient){
        patientMapper.update(patient);
    }

    public JSONObject updatepatientpasswd(Integer id,String old_pw,String new_pw){
        JSONObject json = new JSONObject();
        String origin_pw=patientMapper.selectPasswdById(id);
        String pw=MD5Util.md5(old_pw);

        if(!origin_pw.equals(pw)){
            json.put("code",1);System.out.println("旧密码错误");
            json.put("msg","旧密码输入错误");
        }
        else{
            patientMapper.updatepw(id,MD5Util.md5(new_pw));
            json.put("code",0);
            json.put("msg","成功修改密码");
        }
        return json;
    }

    //显示
    public Integer showdanger(Integer patientId ){
       return  patientMapper.showcancleorder(patientId);
    }

}