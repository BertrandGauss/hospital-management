package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Order;
import com.hospital.entity.Patient;
import com.hospital.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    //进行预约
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    private JSONObject register(@RequestBody Order order){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        order.setPatientId(id);
        JSONObject json = new JSONObject();
        json= orderService.addOrder(order);
        return json;
    }

    //显示预约信息
    @RequestMapping(value = "/showallorder",method = {RequestMethod.GET})
    private JSONObject showallOrder(){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        List<Order> orders = orderService.showallOrder(id);

        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("data",orders);
        json.put("msg","返回预约信息成功");
        return json;
    }
}
