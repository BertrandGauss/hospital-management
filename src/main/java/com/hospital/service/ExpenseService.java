package com.hospital.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.hospital.config.AlipayConfig;
import com.hospital.entity.*;
import com.hospital.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class ExpenseService {
    @Resource
    private ExpenseMapper expenseMapper;
    @Resource
    private TraceMapper traceMapper;
    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private RecordMapper recordMapper;
    @Resource
    private ItemMapper itemMapper;

    public Double countExpense(Integer patientId){
        List<Item> items = expenseMapper.selectitemsbypatientId(patientId);
        List<Record> med = expenseMapper.selectmebypatientId(patientId);
        Double sumPrice =  0.0;
        for(int i=0; i<items.size(); i++){
            sumPrice  = sumPrice + items.get(i).getItemPrice() ;
        }

        for(int i=0; i<med.size(); i++){
            sumPrice  = sumPrice + med.get(i).getMedPrice() * med.get(i).getDosage() ;
        }
        return sumPrice;
    }
    public List<Recipe> searchRecipes(Integer patientId, Date startDate, Date endDate){
        List<Item> items = expenseMapper.selectitembydate(patientId,startDate,endDate);
        List<Record> med = expenseMapper.selectmedbydate(patientId,startDate,endDate);
        List<Recipe> recipes = new LinkedList<>();
        for(int i=0; i<items.size(); i++){
            Doctor doctor = doctorMapper.selectbyid(items.get(i).getDoctorId());
            Recipe recipe = new Recipe();
            recipe.setType("检查");
            recipe.setdName(doctor.getdName());
            recipe.setRdate(items.get(i).getItemDate());
            recipe.setRecipeName(items.get(i).getItemName());
            recipe.setPrice(items.get(i).getItemPrice());
            if(items.get(i).getHavePay()==0)
                recipe.setState("未缴费");
            else if(items.get(i).getItemHaveDone()==0)
                recipe.setState("等待检查");
            else
                recipe.setState("检查完成");
            recipes.add(recipe);
            System.out.println(recipe.getPatientId());
        }
        for(int i=0; i<med.size(); i++){
            Doctor doctor = doctorMapper.selectbyid(med.get(i).getDoctorId());
            Recipe recipe = new Recipe();
            recipe.setType("处方");
            recipe.setdName(doctor.getdName());
            recipe.setRdate(med.get(i).getRecordDate());
            recipe.setRecipeName(med.get(i).getMedName());
            recipe.setPrice(med.get(i).getMedPrice()*med.get(i).getDosage());
            Integer state = traceMapper.selectById(patientId);
            if(state==3)
                recipe.setState("等待退费");
            else if(med.get(i).getHavePay() == 0)
                recipe.setState("未缴费");
            else if(med.get(i).getHavePay() ==1 && med.get(i).getMedHaveDone()==0)
                recipe.setState("等待配药");
            else if(med.get(i).getHavePay() ==1 && med.get(i).getMedHaveDone()==1)
                recipe.setState("配药完成");
            recipes.add(recipe);
            System.out.println(recipe.getPatientId());

        }
        return recipes;
    }

    public List<Recipe> showallPay(Integer patientId){
        List<Item> items = expenseMapper.selectitemsbypatientId(patientId);
        List<Record> med = expenseMapper.selectmebypatientId(patientId);
        List<Recipe> recipes = new LinkedList<>();
        for(int i=0; i<items.size(); i++){
            Doctor doctor = doctorMapper.selectbyid(items.get(i).getDoctorId());
            Recipe recipe = new Recipe();
            recipe.setType("检查");
            recipe.setdName(doctor.getdName());
            recipe.setRdate(items.get(i).getItemDate());
            recipe.setRecipeName(items.get(i).getItemName());
            recipe.setPrice(items.get(i).getItemPrice());
            if(items.get(i).getHavePay()==0)
                recipe.setState("未缴费");
            else if(items.get(i).getItemHaveDone()==0)
                recipe.setState("等待检查");
            else
                recipe.setState("检查完成");
            recipes.add(recipe);

        }
        for(int i=0; i<med.size(); i++){
            Doctor doctor = doctorMapper.selectbyid(med.get(i).getDoctorId());
            Recipe recipe = new Recipe();
            recipe.setType("处方");
            recipe.setdName(doctor.getdName());
            recipe.setRdate(med.get(i).getRecordDate());
            recipe.setRecipeName(med.get(i).getMedName());
            System.out.println(med.get(i).getMedPrice());
            System.out.println(med.get(i).getDosage());
            recipe.setPrice(med.get(i).getMedPrice()*med.get(i).getDosage());
            Integer state = traceMapper.selectById(patientId);
            if(state==3)
                recipe.setState("等待退费");
            else if(med.get(i).getHavePay() == 0)
                recipe.setState("未缴费");
            else if(med.get(i).getHavePay() ==1 && med.get(i).getMedHaveDone()==0)
                recipe.setState("等待配药");
            else if(med.get(i).getHavePay() ==1 && med.get(i).getMedHaveDone()==1)
                recipe.setState("配药完成");
            recipes.add(recipe);

        }
        return recipes;
    }
    public JSONObject payPrice(Integer patientId){
        Double totalPrice = countExpense(patientId);
        expenseMapper.payItems(patientId);
        expenseMapper.payMedicine(patientId);

        //生成订单号
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderSn = simpleDateFormat.format(Calendar.getInstance().getTime());

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key,
                "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        String out_trade_no = orderSn;
        //付款金额，必填  ShopName
        String total_amount = totalPrice.toString();
        //订单名称，必填
        String subject = "支付药品";
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        JSONObject json = new JSONObject();
        try {

            //请求
            String result;
            //发送请求并返回
            json.put("code",0);
            json.put("msg","支付成功,开始配药");
            json.put("count",totalPrice);
            traceMapper.updateTrace(patientId,1);
            result = alipayClient.pageExecute(alipayRequest).getBody();
            System.out.println("*********************\n返回结果为："+result);
            json.put("data",result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            json.put("code",1);
            json.put("msg","支付失败");
        }
        return json;
    }

    public JSONObject refund(Integer patientId, List<String> recipeIds){
        JSONObject json = new JSONObject();
        Double prices = 0.0;
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.plusHours(12);//12小时
        Date date =Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        for(int i = 0 ;i<recipeIds.size();i++){
            Date rDateRecord = recordMapper.getRdate(patientId,recipeIds.get(i));
            Date iDateItem = itemMapper.getIdate(patientId,recipeIds.get(i));
            Item item = expenseMapper.checkitem(patientId,recipeIds.get(i));
            Record record = expenseMapper.checkmedicine(patientId,recipeIds.get(i));
            if(item==null && record==null){
                json.put("code",1);
                json.put("msg","退费中存在未支付的物品");
                return  json;
            }
            else if(item.getItemHaveDone() ==1){
                json.put("code",2);
                json.put("msg","退费检查已做");
                return  json;
            }
            else if(record.getMedHaveDone() ==1){
                json.put("code",3);
                json.put("msg","退费药品未退回");
                return  json;
            }else if(rDateRecord!=null&&rDateRecord.after(date)){
                json.put("code",4);
                json.put("msg","必须缴费12小时以内才可以退费");
                return  json;
            }else if(iDateItem!=null&&iDateItem.after(date)){
                json.put("code",4);
                json.put("msg","必须缴费12小时以内才可以退费");
                return  json;
            }
            else if(item!=null){
                prices = prices + item.getItemPrice();
                expenseMapper.deleteItem(patientId,recipeIds.get(i));
            }else if(record!=null){
                prices = prices + record.getMedPrice() * record.getDosage();
                expenseMapper.deleteRecord(patientId,recipeIds.get(i));
            }
        }
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.gatewayUrl,
                AlipayConfig.app_id,
                AlipayConfig.merchant_private_key,
                "json",
                AlipayConfig.charset,
                AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type
        );
        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        //生成订单号
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String orderSn = simpleDateFormat.format(Calendar.getInstance().getTime());
        //商户订单号，后台可以写一个工具类生成一个订单号，必填
        String out_trade_no = orderSn;
        //支付宝交易号，后台可以写一个工具类生成一个订单号，必填
        String trade_no  = new String(orderSn);
        //付款金额，从前台获取，必填
        String refund_amount = prices.toString();
        //订单名称/标题，可自定义
        String subject = new String("支付宝沙箱测试");
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"trade_no\":\""+ trade_no +"\","
                + "\"refund_amount\":\""+ refund_amount +"\"}");

        //请求
        try {

            //请求
            //发送请求并返回
            json.put("code",0);
            json.put("msg","退费成功");
            json.put("count",prices);
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            System.out.println("*********************\n返回结果为："+result);
            json.put("data",result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            json.put("code",4);
            json.put("msg","支付失败");
        }
        return json;
    }


}
