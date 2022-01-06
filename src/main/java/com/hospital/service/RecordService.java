package com.hospital.service;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.*;
import com.hospital.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class RecordService {
    @Resource
    private RecordMapper recordMapper;
    @Resource
    private MedicineMapper medicineMapper;
    @Resource
    private TraceMapper traceMapper;
    @Resource
    private PatientMapper patientMapper;
    @Resource
    private DoctorMapper doctorMapper;

    // 开处方
    public JSONObject addRecord(Record record) {
        JSONObject json = new JSONObject();
        Integer remains = medicineMapper.selectNumByName(record.getMedName());
        if(remains==null){
            json.put("code",1);
            json.put("msg","药品库中不存在该药");
        }else if(remains<record.getDosage()){
            json.put("code",2);
            json.put("msg","药品库中该药库存不足");
        }else{
            //添加处方记录，并更新药品库存
            recordMapper.add(record);
            medicineMapper.updateRemains(record.getDosage());
            //等待配药
            Trace trace = new Trace();
            trace.setPatientId(record.getPatientId());
            if(traceMapper.selectById(record.getPatientId())==null) {
                traceMapper.addTrace(trace);
                System.out.println("开始追踪");
            }
            else{
                traceMapper.updateTrace(record.getPatientId(),0);
            }
            json.put("code",0);
            json.put("msg","开处方成功");
        }
        return json;

    }

    //显示所有已缴费的处方
    public List<Recipe> showAllRecord(){
        List<Record> records = recordMapper.selectmed();
        List<Recipe> recipes = new LinkedList<>();
        for(int i=0; i<records.size(); i++){
            Recipe recipe = new Recipe();
            String pIdentificationNum = patientMapper.selectbyid(records.get(i).getPatientId()).getpIdentificationNum();
            Doctor doctor = doctorMapper.selectbyid(records.get(i).getDoctorId());
            recipe.setdName(doctor.getdName());
            recipe.setRecipeName(records.get(i).getMedName());
            recipe.setPrice(records.get(i).getMedPrice());
            recipe.setDosage(records.get(i).getDosage());
            recipe.setPatientId(records.get(i).getPatientId());
            recipe.setRdate(records.get(i).getRecordDate());
            recipe.setpIdentificationNum(pIdentificationNum);
            recipe.setState("等待配药");
            recipes.add(recipe);
        }
        return recipes;
    }

    //显示所有已缴费的处方
    public List<Recipe> showAllGETRecord(){
        List<Record> records = recordMapper.selectmedGet();
        List<Recipe> recipes = new LinkedList<>();
        for(int i=0; i<records.size(); i++){
            Recipe recipe = new Recipe();
            String pIdentificationNum = patientMapper.selectbyid(records.get(i).getPatientId()).getpIdentificationNum();
            Doctor doctor = doctorMapper.selectbyid(records.get(i).getDoctorId());
            recipe.setdName(doctor.getdName());
            recipe.setRecipeName(records.get(i).getMedName());
            recipe.setPrice(records.get(i).getMedPrice());
            recipe.setDosage(records.get(i).getDosage());
            recipe.setPatientId(records.get(i).getPatientId());
            recipe.setRdate(records.get(i).getRecordDate());
            recipe.setpIdentificationNum(pIdentificationNum);
            recipe.setState("配药完成");
            recipes.add(recipe);
        }
        return recipes;
    }

    //显示固定患者已缴费的处方
    public List<Recipe> searchrecord(String pIdentificationNum){
        Integer patientId = patientMapper.selectByIdentificationNum(pIdentificationNum);
        List<Record> records = recordMapper.selectmedbypIdentificationNum(patientId);
        List<Recipe> recipes = new LinkedList<>();
        Recipe recipe = new Recipe();
        for(int i=0; i<records.size(); i++){

            Doctor doctor = doctorMapper.selectbyid(records.get(i).getDoctorId());
            recipe.setdName(doctor.getdName());

            recipe.setRecipeName(records.get(i).getMedName());
            recipe.setPrice(records.get(i).getMedPrice());
            recipe.setDosage(records.get(i).getDosage());
            recipe.setPatientId(records.get(i).getPatientId());
            recipe.setRdate(records.get(i).getRecordDate());
            recipe.setpIdentificationNum(pIdentificationNum);
            recipe.setState("等待配药");
            recipes.add(recipe);
        }
        return recipes;
    }

    //设置所有配药
    public void setRecordsHaveDone(SomeRecipe someRecipe) {
        List<String> Names = someRecipe.getRecipeName();
        List<String> pIdentificationNums = someRecipe.getpIdentificationNum();
        for (int i=0;i<Names.size();i++){
            Integer patientId = patientMapper.selectByIdentificationNum(pIdentificationNums.get(i));
            recordMapper.setMedHaveDone(patientId,Names.get(i));
            if(checkRecords(patientId)==1)
                traceMapper.updateTrace(patientId,2);
        }
    }

    //设置某项配药已配
    public void setRecordHaveDone(String recordname, String pIdentificationNum) {
        Integer patientId = patientMapper.selectByIdentificationNum(pIdentificationNum);
        recordMapper.setMedHaveDone(patientId,recordname);
        //所有药品配齐
        if(checkRecords(patientId)==1)
            traceMapper.updateTrace(patientId,2);
    }

    //检查患者是不是所有配药完成
    public Integer checkRecords(Integer patientId){
        List<Integer> haveDone = recordMapper.selectById(patientId);
        for(int i=0;i<haveDone.size();i++){
            if(haveDone.get(i)==0)
                return  0;
        }
        return 1;
    }

    //批量退药审核
    public void setRecordsnotHaveDone(SomeRecipe someRecipe) {
        List<String> Names = someRecipe.getRecipeName();
        List<String> pIdentificationNums = someRecipe.getpIdentificationNum();
        for (int i=0;i<Names.size();i++){
            Integer patientId = patientMapper.selectByIdentificationNum(pIdentificationNums.get(i));
            recordMapper.setMednotHaveDone(patientId,Names.get(i));
            traceMapper.updateTrace(patientId,3);
            Integer doages = recordMapper.getRecord(patientId,Names.get(i)).getDosage();
            medicineMapper.returnRemains(doages);

        }
    }

    //设置某项配药已退
    public void setRecordnotHaveDone(String recordname, String pIdentificationNum) {
        Integer patientId = patientMapper.selectByIdentificationNum(pIdentificationNum);
        traceMapper.updateTrace(patientId,3);
        System.out.println(patientId);
        System.out.println(recordname);
        Integer doages = recordMapper.getRecord(patientId,recordname).getDosage();
        recordMapper.setMednotHaveDone(patientId,recordname);
        medicineMapper.returnRemains(doages);
    }

    //显示固定患者可退药的处方
    public List<Recipe> searchreturnrecord(String pIdentificationNum){
        Integer patientId = patientMapper.selectByIdentificationNum(pIdentificationNum);
        List<Record> records = recordMapper.selectnotmedbypIdentificationNum(patientId);
        List<Recipe> recipes = new LinkedList<>();
        Recipe recipe = new Recipe();
        for(int i=0; i<records.size(); i++){
            Doctor doctor = doctorMapper.selectbyid(records.get(i).getDoctorId());
            recipe.setdName(doctor.getdName());
            recipe.setRecipeName(records.get(i).getMedName());
            recipe.setPrice(records.get(i).getMedPrice());
            recipe.setDosage(records.get(i).getDosage());
            recipe.setPatientId(records.get(i).getPatientId());
            recipe.setRdate(records.get(i).getRecordDate());
            recipe.setpIdentificationNum(pIdentificationNum);
            recipe.setState("等待退费");
            recipes.add(recipe);
        }
        return recipes;
    }

    //显示所有医生开给某个患者的处方
    public List<Record> showAllRecord(Integer patientId,Integer doctorId){
        System.out.println("nihao"+doctorId);
        List<Record> records =recordMapper.selectnotmedbypIdandDid(patientId,doctorId);
        return records;
    }
}
