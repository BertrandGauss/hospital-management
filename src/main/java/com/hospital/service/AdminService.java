package com.hospital.service;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.*;
import com.hospital.mapper.AdminMapper;
import com.hospital.mapper.ItemMapper;
import com.hospital.mapper.PatientMapper;
import com.hospital.mapper.RecordMapper;
import com.hospital.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private PatientMapper patientMapper;
    @Resource
    private ItemMapper itemMapper;
    @Resource
    private RecordMapper recordMapper;

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

    public List<Doctor> showDoctorRegister() {
        List<Doctor> doctors = adminMapper.showDoctorRegister();
        return doctors;
    }

    // 显示患者所有已缴费的项目和药品清单（根据患者身份证号）
    public List<Recipe> showPayedRecipe(Integer patientId) {
        List<Item> items = adminMapper.selectitemsbypId(patientId);
        List<Record> med = adminMapper.selectmedbypId(patientId);
        List<Recipe> recipes = null;
        for(int i=0; i<items.size(); i++){
            Recipe recipe = new Recipe();
            recipe.setRecipeName(items.get(i).getItemName());
            recipe.setPrice(items.get(i).getItemPrice());
            recipes.add(recipe);
        }

        for(int i=0; i<med.size(); i++){
            Recipe recipe = new Recipe();
            recipe.setRecipeName(med.get(i).getMedName());
            recipe.setPrice(med.get(i).getMedPrice());
            recipe.setDosage(med.get(i).getDosage());
            recipes.add(recipe);
        }
        return recipes;
    }

    public void setHaveDone(SomeRecipe someRecipe) {
        List<String> Names = someRecipe.getRecipeName();
        Integer patientId = patientMapper.selectByIdentificationNum(someRecipe.getpIdentificationNum());
        for (int i=0;i<Names.size();i++){
            itemMapper.setItemsHaveDone(patientId,Names.get(i));
            recordMapper.setMedHaveDone(patientId,Names.get(i));
        }
    }


    public Integer getPatientIdByPid(String pIdentificationNum){
        Integer patientId = adminMapper.getPatientIdByPid(pIdentificationNum);
        return patientId;
    }
}
