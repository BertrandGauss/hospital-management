package com.hospital.service;

import com.alibaba.fastjson.JSONObject;
import com.hospital.mapper.PatientMapper;
import com.hospital.mapper.TraceMapper;
import org.springframework.stereotype.Service;
import com.hospital.entity.Patient;
import com.hospital.utils.MD5Util;

import javax.annotation.Resource;

@Service
public class PatientService {
    @Resource
    private PatientMapper patientMapper;
    @Resource
    private TraceMapper traceMapper;

    public Integer idnumberisregister(String pIdentificationNum){
        Integer id = patientMapper.selectByIdentificationNum(pIdentificationNum);
        System.out.println(id);
        return id;

    }

    public Integer phoneisregister(String pPhone){
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
        JSONObject json = new JSONObject();
        if(passw==null){
            json.put("msg","该用户不存在");
            json.put("code",1);

            return json;
        }else if(!pw.equals(passw)){
            json.put("msg","密码错误");
            json.put("code",2);

            return json;
        }

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
            json.put("code",1);
            json.put("msg","旧密码输入错误");
        }
        else{
            patientMapper.updatepw(id,MD5Util.md5(new_pw));
            json.put("code",0);
            json.put("msg","成功修改密码");
        }
        return json;
    }

    //显示取消次数
    public Integer showdanger(Integer patientId ){
       return  patientMapper.showcancleorder(patientId);
    }

    //显示进度
    public String showTrace(Integer patientId){
        Integer state = traceMapper.selectById(patientId);
        String trace;
        if (state == 0){
            trace = "等待配药";
        }else if(state == 1){
            trace = "正在配药";
        }else if(state == 3){
            trace = "等待发药";
        }else {
            trace = "正在发药";
        }
        return trace;
    }

}
