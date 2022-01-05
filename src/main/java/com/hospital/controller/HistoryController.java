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
        System.out.println(id);
        Patient patientinfo = historyService.showpatientInfo(id);
        //把患者Id保存在session中
        this.httpServletRequest.getSession().setAttribute("Patient", patientinfo.getPatientId());
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
    private JSONObject viewAllHisByPid(@RequestParam("pIdentificationNum") String pIdentificationNum){
        List<History> history = historyService.showAllHisByPid(pIdentificationNum);
        JSONObject json = new JSONObject();
        json.put("data", history);
        json.put("code",0);
        json.put("msg","成功获取历史病历");
        return json;
    }
}
