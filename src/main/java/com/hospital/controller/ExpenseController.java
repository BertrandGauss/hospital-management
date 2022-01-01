package com.hospital.controller;
import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.Recipe;
import com.hospital.entity.SomeRecipe;
import com.hospital.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    //返回处方单
    @RequestMapping(value = "/showrecipe",method = {RequestMethod.GET})
    private JSONObject showallRecipe(){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        List<Recipe> recipes = expenseService.showallPay(id);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","处方显示成功");
        json.put("date",recipes);

        return  json;
    }

    //返回需要的金额
    @RequestMapping(value = "/showprice",method = {RequestMethod.GET})
    private JSONObject showPrice(){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        Double totalPrice = expenseService.countExpense(id);
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","返回总金额");
        json.put("date",totalPrice);
        return json;
    }

    //支付
    @RequestMapping(value = "/payprice",method = {RequestMethod.GET})
    private JSONObject payPrice(){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        JSONObject json = expenseService.payPrice(id);

        return json;
    }

    //退费
    @RequestMapping(value = "/refund",method = {RequestMethod.POST})
    private JSONObject refund(@RequestBody SomeRecipe someRecipe){
        Integer id=(Integer) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        JSONObject json =expenseService.refund(id, someRecipe.getRecipeName());
        return json;
    }


}
