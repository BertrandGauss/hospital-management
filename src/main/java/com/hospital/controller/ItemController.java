package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.*;
import com.hospital.service.HistoryService;
import com.hospital.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    // 初始化检查单
    @RequestMapping(value = "/originatecheckitem",method = {RequestMethod.GET})
    private JSONObject originateCheckItem(){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        String dept = historyService.showdepartmentofhis(id);   // 获取开具检查单的部门
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("data", dept);
        json.put("msg","成功初始化检查单");
        return json;
    }

    // 获取检查单中的患者相关信息
    @RequestMapping(value = "/getcheckinfobypid",method = {RequestMethod.GET})
    private JSONObject getPatientInfoByPid(){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("Patient");
        Patient patientinfo = historyService.patientInfo(id);
        JSONObject json = new JSONObject();
        json.put("data", patientinfo);
        json.put("code",0);
        json.put("msg","成功获取病历中患者相关信息");
        return json;
    }

    // 开具检查单
    @RequestMapping(value = "/addcheckitem", method = {RequestMethod.POST})
    private JSONObject addCheckItem(@RequestBody Item item){
        Integer did=(Integer) httpServletRequest.getSession().getAttribute("USER");//医生ID
        Integer pid=(Integer) httpServletRequest.getSession().getAttribute("Patient");//患者ID
        item.setDoctorId(did);
        item.setPatientId(pid);
        Date date = new Date();
        item.setItemDate(date);
        itemService.addCheckItem(item);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","检查单开具完成");
        return json;
    }


    //显示所有已缴费的处方
    @RequestMapping(value = "/showItem", method = {RequestMethod.GET})
    private JSONObject showItem(){
        List<Recipe> items = itemService.showAllItem();
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","显示成功");
        json.put("data",items);
        json.put("count",items.size());
        return json;
    }

    // 对患者已缴费的检查确认进行完成检查
    @RequestMapping(value = "/setItemshavedone",method = {RequestMethod.POST})
    private JSONObject setItemshavedone(@RequestBody SomeRecipe someRecipe){
        itemService.setItemsHaveDone(someRecipe);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","确认完成");
        return json;
    }

    //对患者某个检查进行确认
    @RequestMapping(value = "/setItemhavedone",method = {RequestMethod.POST})
    private JSONObject setItemhavedone(@RequestParam("recipeName") String recipeName, @RequestParam("pIdentificationNum")String pIdentificationNum){
        itemService.setItemHaveDone(recipeName,pIdentificationNum);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","确认完成");
        return json;
    }

    //查找某个患者的检查
    @RequestMapping(value = "/searchitem",method = {RequestMethod.POST})
    private JSONObject searchitem(@RequestBody Patient patient){
        List<Recipe> items =itemService.searchitem(patient.getpIdentificationNum());
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","查找完成");
        json.put("data",items);
        json.put("count",items.size());
        return json;
    }

    //显示医生开具给该患者的所有检查
    @RequestMapping(value = "/showallItem",method = {RequestMethod.GET})
    private JSONObject showALlItems(){
        Integer did=(Integer) httpServletRequest.getSession().getAttribute("USER");//医生ID
        Integer pid=(Integer) httpServletRequest.getSession().getAttribute("Patient");//患者ID
        List<Item> items = itemService.showAll(did,pid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","显示成功");
        jsonObject.put("data",items);
        return jsonObject;
    }
}
