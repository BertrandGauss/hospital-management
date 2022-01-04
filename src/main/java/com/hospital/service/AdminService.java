package com.hospital.service;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.*;
import com.hospital.mapper.*;
import com.hospital.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
    @Resource
    private TraceMapper traceMapper;
    @Resource
    private DoctorMapper doctorMapper;

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

    // 审核不通过医生账号
    public void deleteDoctorRegister(Doctor doctor) {
        doctorMapper.deleteDoctorRegister(doctor.getdIdentificationNum());
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
            recipe.setPatientId(patientId);
            recipe.setRecipeName(items.get(i).getItemName());
            recipe.setPrice(items.get(i).getItemPrice());
            recipes.add(recipe);
        }

        for(int i=0; i<med.size(); i++){
            Recipe recipe = new Recipe();
            recipe.setPatientId(patientId);
            recipe.setRecipeName(med.get(i).getMedName());
            recipe.setPrice(med.get(i).getMedPrice()*med.get(i).getDosage());
            recipe.setDosage(med.get(i).getDosage());
            recipes.add(recipe);
        }
        return recipes;
    }

    //显示所有已缴费的项目和药品清单
    public List<Recipe> showALLPayedRecipe(){
        List<Item> items = itemMapper.selectitems();
        List<Record> records = recordMapper.selectmed();
        List<Recipe> recipes = null;
        for(int i=0; i<items.size(); i++){
            Recipe recipe = new Recipe();
            recipe.setRecipeName(items.get(i).getItemName());
            recipe.setPrice(items.get(i).getItemPrice());
            recipe.setPatientId(items.get(i).getPatientId());
            recipe.setRdate(items.get(i).getItemDate());
            recipes.add(recipe);
        }

        for(int i=0; i<records.size(); i++){
            Recipe recipe = new Recipe();
            recipe.setRecipeName(records.get(i).getMedName());
            recipe.setPrice(records.get(i).getMedPrice());
            recipe.setDosage(records.get(i).getDosage());
            recipe.setPatientId(records.get(i).getPatientId());
            recipe.setRdate(records.get(i).getRecordDate());
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

    public Integer patientIsOrdered(Patient patient){
        return adminMapper.patientIsOrdered(patient);
    }

    public void changeTraceState(PatientVo patientVo) {
        adminMapper.updateTrace(patientVo);
    }

    public List<PatientVo> showPatientTrac() {
        List<PatientVo> patientVos = adminMapper.showPatientTrac();
        return patientVos;
    }

    public void updateMedIsInPatient(Integer patientId, Integer medHaveDone){
        adminMapper.updateMedIsInPatient(patientId, medHaveDone);
    }

    public void updateMedRemainsPut(PatientVo patientVo){
        adminMapper.updateMedRemainsPut(patientVo);
    }

    public void updateMedRemainsGet(PatientVo patientVo){
        adminMapper.updateMedRemainsGet(patientVo);
    }

    //修改配药状态，包括退药和发药
    public JSONObject updateState(PatientVo patientVo){
        JSONObject jsonObject = new JSONObject();
        //表示发药
        if(patientVo.getState()==2){
            updateMedIsInPatient(patientVo.getPatientId(), 1);
            updateMedRemainsPut(patientVo);
            traceMapper.updateTrace(patientVo.getPatientId(),2);
        }
        //退药
        else if(patientVo.getState()==3){
            Date rDate = recordMapper.getRdate(patientVo.getPatientId(), patientVo.getMedName());
            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime = localDateTime.plusHours(12);//12小时
            Date date =Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
            if(rDate.after(date)){
                jsonObject.put("code",1);
                jsonObject.put("msg","退药时间超过了缴费时间的12小时");
                return jsonObject;
            }
            updateMedIsInPatient(patientVo.getPatientId(), 0);
            updateMedRemainsGet(patientVo);
            traceMapper.updateTrace(patientVo.getPatientId(),3);
        }
        jsonObject.put("code",0);
        jsonObject.put("msg","修改配药状态成功");
        return  jsonObject;

    }
}
