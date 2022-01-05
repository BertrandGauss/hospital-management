package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.History;
import com.hospital.entity.Patient;
import com.hospital.service.HistoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    // 初始化病历
    @RequestMapping(value = "/originatecasehis",method = {RequestMethod.GET})
    private JSONObject originateCaseHis(){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("USER");
        String dept = historyService.showdepartmentofhis(id);
        JSONObject json = new JSONObject();
        json.put("data", dept);
        json.put("code",0);
        json.put("msg","病历初始化完成");
        return json;
    }

    // 根据患者身份证号获取病历中患者相关信息
    @RequestMapping(value = "/getpatientinfobypid",method = {RequestMethod.POST})
    private JSONObject getPatientInfoByPid(@RequestParam("pIdentificationNum") String pIdentificationNum){
        Patient patientinfo = historyService.originatebypid(pIdentificationNum);
        //把患者Id保存在session中
        if(patientinfo==null)
            patientinfo.setPatientId(-1);
        this.httpServletRequest.getSession().setAttribute("Patient", patientinfo.getPatientId());
        JSONObject json = new JSONObject();
        json.put("data", patientinfo);
        json.put("code",0);
        json.put("msg","成功获取病历中患者相关信息");
        return json;
    }

    @RequestMapping(value = "/showpatientinfo",method = {RequestMethod.GET})
    private JSONObject showPatientInfo(){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("USER");
        Patient patientinfo = historyService.showpatientInfo(id);
        Integer ID ;
        //把患者Id保存在session中
        if(patientinfo==null){
            ID = 0;
        }else{
            ID = patientinfo.getPatientId();
        }
        this.httpServletRequest.getSession().setAttribute("Patient",ID);
        System.out.println(httpServletRequest.getSession().getId()+"显示");
        JSONObject json = new JSONObject();
        json.put("data", patientinfo);
        json.put("code",0);
        json.put("msg","成功获取病历中患者相关信息");
        return json;
    }

    // 编辑病历
    @RequestMapping(value = "/editcasehis",method = {RequestMethod.POST})
    private JSONObject editcasehis(@RequestBody History history){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("Patient");
        history.setPatientId(id);
        historyService.editcasehis(history);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","病历编辑完成");
        return json;
    }

    // 根据病人身份证号，查看历史病历
    @RequestMapping(value = "/viewallhisbypid",method = {RequestMethod.POST})
    private JSONObject viewAllHisByPid(@RequestBody History his){
        System.out.println(his.getpIdentificationNum());
        List<History> history = historyService.showAllHisByPid(his.getpIdentificationNum());
        JSONObject json = new JSONObject();
        json.put("data", history);
        json.put("code",0);
        json.put("msg","成功获取历史病历");
        json.put("count",history.size());
        return json;
    }

    //根据查看所有病历
    @RequestMapping(value = "/viewallhis",method = {RequestMethod.GET})
    private JSONObject viewAll(){
        List<History> histories = historyService.showAllHis();
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","成功获取所有历史病历");
        json.put("count",histories.size());
        json.put("data",histories);
        return json;
    }

    //根据查看所有病历
    @RequestMapping(value = "/viewonehis",method = {RequestMethod.POST})
    private JSONObject viewonehis(@RequestBody History his){
        System.out.println(his.getHistoryId());
        History history = historyService.showHis(his.getHistoryId());
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","成功获取历史病历");
        json.put("data",history);
        return json;
    }

    //结束一个诊断
    @RequestMapping(value = "/endregistration", method = {RequestMethod.GET})
    private JSONObject endregistration(){
        Integer did=(Integer) httpServletRequest.getSession().getAttribute("USER");//医生ID
        historyService.endregistration(did);
        System.out.println(did+"结束");
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","结束一个病人的就诊");
        return json;
    }
}
