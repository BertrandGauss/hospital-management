package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Item;
import com.hospital.entity.Patient;
import com.hospital.service.HistoryService;
import com.hospital.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
    @RequestMapping(value = "/getcheckinfobypid",method = {RequestMethod.POST})
    private JSONObject getPatientInfoByPid(@RequestParam("pIdentificationNum") String pIdentificationNum){
        Patient patientinfo = historyService.originatebypid(pIdentificationNum);
        JSONObject json = new JSONObject();
        json.put("data", patientinfo);
        json.put("code",0);
        json.put("msg","成功获取病历中患者相关信息");
        return json;
    }


    // 开具检查单
    @RequestMapping(value = "/addcheckitem", method = {RequestMethod.POST})
    private JSONObject addCheckItem(@RequestBody Item item){
        itemService.addCheckItem(item);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","检查单开具完成");
        return json;
    }



    private Integer itemId;          // 医学检查项目ID
    private String illnessSummary;   // 病情摘要
    private String checkArea;        // 检查部位
    private String notice;           // 注意事项
    private Date checkTime;          // 检查时间
    private String checkRes;         // 检查结果
    private String opinion;          // 总结意见
}
