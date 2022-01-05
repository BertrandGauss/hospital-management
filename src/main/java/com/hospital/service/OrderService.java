package com.hospital.service;


import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Doctor;
import com.hospital.entity.Order;
import com.hospital.mapper.DoctorMapper;
import com.hospital.mapper.OrderMapper;
import com.hospital.mapper.PatientMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Time;
import java.text.DateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private PatientMapper patientMapper;
    @Resource
    private DoctorMapper doctorMapper;

    public JSONObject addOrder(Order order){
        Integer isInBlackList = patientMapper.selectBlacklist(order.getPatientId());
        Integer doctorId = doctorMapper.selectbyName(order.getDepartment(),order.getdName());

        JSONObject json = new JSONObject();
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        Date tomorrow = c.getTime();

        order.setoDate(tomorrow);
        if(!order.getoType().equals("普通") && doctorId==null){
            json.put("msg","不存在该医生");
            json.put("code",1);
            return json;
        }
        order.setDoctorId(doctorId);
        if(orderMapper.selectbyorderTime(order.getPatientId())!=null){
            json.put("msg","您今日已有预约");
            json.put("code",1);
            return json;
        }
        if(isInBlackList==1){
            json.put("msg","您已被拉入黑名单");
            json.put("code",2);
            return json;
        }
        if(order.getoType()=="专家预约"){
            Integer orderNum = orderMapper.countOrderNumberZ(order);
            if(orderNum>=5){
                json.put("msg","专家预约号不足");
                json.put("code",3);
                return json;
            }
        }
        else{
            Integer orderNum = orderMapper.countOrderNumber(order);
            if(orderNum>=10){
                json.put("msg","科室预约号不足");
                json.put("code",4);
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
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).getDoctorId()!=null){
                orders.get(i).setdName(doctorMapper.selectbyid(orders.get(i).getDoctorId()).getdName());
            }
        }
        return orders;
    }

    public JSONObject cancleOrder(Integer patientId, Integer orderId){
        JSONObject json = new JSONObject();
        if (orderMapper.selectValid(patientId, orderId)==0){
            json.put("msg","该预约是无效预约");
            json.put("code",1);
            return  json;
        }
        orderMapper.deleteValid(patientId, orderId);
        //取消预约次数加一
        patientMapper.updateorderTimes(patientId);
        if(patientMapper.showcancleorder(patientId)>=20){//取消次数大于上限
            patientMapper.updateBlacklist(patientId);//把患者拉入黑名单
        }
        json.put("msg","取消预约成功");
        json.put("code",0);
        return json;
    }

    public List<Order> searchOrder(Integer patientId, Date startDate,Date endDate){
        List<Order> orders = orderMapper.selectbydate(patientId,startDate,endDate);
        for(int i=0;i<orders.size();i++){
            if(orders.get(i).getDoctorId()!=null){
                orders.get(i).setdName(doctorMapper.selectbyid(orders.get(i).getDoctorId()).getdName());
            }
        }
        return  orders;
    }

    //按时间检测预约是否时效
    @Scheduled(cron = "0 0 */1 * * ?")  //每小时检测一次
    public void checkOrder(){
        Date today = new Date();
        System.out.println("today"+today);
        Time time =Time.valueOf(LocalTime.now().plusHours(-1));
        List<Integer> pIds= orderMapper.checkOrder();
        for(int i=0;i<pIds.size();i++){
            //过时算取消预约
            patientMapper.updateorderTimes(pIds.get(i));
            if(patientMapper.showcancleorder(pIds.get(i))>=20){//取消次数大于上限
                patientMapper.updateBlacklist(pIds.get(i));//把患者拉入黑名单
            }
        }
        orderMapper.updateisValid(today, time);

    }
}
