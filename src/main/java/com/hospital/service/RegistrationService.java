package com.hospital.service;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Order;
import com.hospital.entity.Registration;
import com.hospital.entity.RegistrationVo;
import com.hospital.mapper.DoctorMapper;
import com.hospital.mapper.OrderMapper;
import com.hospital.mapper.PatientMapper;
import com.hospital.mapper.RegistrationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;

@Service
public class RegistrationService {
    @Resource
    private RegistrationMapper registrationMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private PatientMapper patientMapper;
    @Resource
    private DoctorMapper doctorMapper;

    public void Registration(RegistrationVo registrationVo){
        Integer id = patientMapper.selectByIdentificationNum(registrationVo.getpIdentificationNum());
        Order order = orderMapper.haveOrder(id);
        LocalTime localTime = LocalTime.now();
        Time time =Time.valueOf( localTime.plusMinutes(30));
        //以当前时间生成排队号
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        String orderSn = simpleDateFormat.format(Calendar.getInstance().getTime());
        Integer doctorId = doctorMapper.selectbyName(registrationVo.getDepartment(),registrationVo.getdName());
        Registration registration = new Registration();
        if(order!=null && order.getoTime().after(time)){//预约30分钟前挂号,以普通挂号处理但预约不算失效
            registration.setPatientId(id);
            registration.setDoctorId(doctorId);
            registration.setDepartment(registrationVo.getDepartment());
            registration.setrNum(orderSn);
            orderMapper.updateisValidById(order.getOrderId());
        }else if(order!=null){//预约挂号
            registration.setPatientId(id);
            registration.setDepartment(order.getDepartment());
            registration.setDoctorId(order.getDoctorId());
            registration.setrNum("v"+orderSn);//预约的优越性
        }else{//普通挂号
            registration.setDepartment(registrationVo.getDepartment());
            registration.setDoctorId(doctorId);
            registration.setPatientId(id);
            registration.setrNum(orderSn);
        }

    }
}
