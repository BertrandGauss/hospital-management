package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.*;
import com.hospital.service.RecordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    // 开具处方
    @RequestMapping(value = "/addRecord", method = {RequestMethod.POST})
    private JSONObject addRecord(@RequestBody Record record){
        Integer did=(Integer) httpServletRequest.getSession().getAttribute("USER");//医生ID
        Integer pid=(Integer) httpServletRequest.getSession().getAttribute("Patient");//患者ID
        Date date = new Date();
        record.setRecordDate(date);
        record.setDoctorId(did);
        record.setPatientId(pid);
        System.out.println(pid);
        JSONObject json = recordService.addRecord(record);
        return json;
    }

    //显示所有已缴费的处方
    @RequestMapping(value = "/showRecord", method = {RequestMethod.GET})
    private JSONObject showRecord(){
        List<Recipe> records = recordService.showAllRecord();
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","显示成功");
        json.put("data",records);
        json.put("count",records.size());
        return json;
    }

    //显示所有已交付并且拿走的药品
    @RequestMapping(value = "/showGetRecord", method = {RequestMethod.GET})
    private JSONObject showGetRecord(){
        List<Recipe> records = recordService.showAllGETRecord();
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","显示成功");
        json.put("data",records);
        json.put("count",records.size());
        return json;
    }
    // 对患者已缴费的药品确认进行完成配药
    @RequestMapping(value = "/setRecordshavedone",method = {RequestMethod.POST})
    private JSONObject setRecordshavedone(@RequestBody SomeRecipe someRecipe){
        recordService.setRecordsHaveDone(someRecipe);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","确认完成");
        return json;
    }

    //对患者某个药品进行确认
    @RequestMapping(value = "/setRecordhavedone",method = {RequestMethod.POST})
    private JSONObject setRecordhavedone(@RequestParam("recipeName") String recipeName, @RequestParam("pIdentificationNum")String pIdentificationNum){
        recordService.setRecordHaveDone(recipeName,pIdentificationNum);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","确认完成");
        return json;
    }

    //对患者某个药品进行确认
    @RequestMapping(value = "/searchrecord",method = {RequestMethod.POST})
    private JSONObject searchrecord(@RequestBody Patient patient){
        List<Recipe> records =recordService.searchrecord(patient.getpIdentificationNum());
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","查找完成");
        json.put("data",records);
        json.put("count",records.size());
        return json;
    }

    // 对患者已缴费的药品确认进行完成退药
    @RequestMapping(value = "/setRecordsnothavedone",method = {RequestMethod.POST})
    private JSONObject setRecordsnothavedone(@RequestBody SomeRecipe someRecipe){
        System.out.println("hhh"+someRecipe.getRecipeName().get(0));
        recordService.setRecordsnotHaveDone(someRecipe);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","批量退药确认完成");
        return json;
    }

    //对患者某个药品进行确认退药
    @RequestMapping(value = "/setRecordnothavedone",method = {RequestMethod.POST})
    private JSONObject setRecordnothavedone(@RequestParam("recipeName") String recipeName, @RequestParam("pIdentificationNum")String pIdentificationNum){
        recordService.setRecordnotHaveDone(recipeName,pIdentificationNum);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","退药确认完成");
        return json;
    }

    //查找某一患者已给出的药品
    @RequestMapping(value = "/searchreturnrecord",method = {RequestMethod.POST})
    private JSONObject searchreturnrecord(@RequestBody Patient patient){
        System.out.println("开始退药");
        List<Recipe> records =recordService.searchreturnrecord(patient.getpIdentificationNum());
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","查找患者可以退药药品完成");
        json.put("data",records);
        json.put("count",records.size());
        return json;
    }
}
