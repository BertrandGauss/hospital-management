package com.hospital.service;


import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Order;
import com.hospital.mapper.OrderMapper;
import com.hospital.mapper.PatientMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private PatientMapper patientMapper;

    public JSONObject addOrder(Order order){
        Integer isInBlackList = patientMapper.selectBlacklist(order.getPatientId());
        JSONObject json = new JSONObject();
        if(isInBlackList==1){
            json.put("msg","您已被拉入黑名单");
            json.put("code",1);
            return json;
        }
        if(order.getoType()=="专家预约"){
            Integer orderNum = orderMapper.countOrderNumberZ(order);
            if(orderNum>=5){
                json.put("msg","专家预约号不足");
                json.put("code",2);
                return json;
            }
        }
        else{
            Integer orderNum = orderMapper.countOrderNumber(order);
            if(orderNum>=10){
                json.put("msg","科室预约号不足");
                json.put("code",3);
                return json;
            }
        }
        orderMapper.add(order);
        json.put("msg","预约成功");
        json.put("code",0);
        return json;
    }

    public List<Order> showallOrder(Integer patientId){
        List<Order> orders = orderMapper.selectbyId(patientId);
        return orders;
    }
}
