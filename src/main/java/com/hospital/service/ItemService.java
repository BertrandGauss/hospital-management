package com.hospital.service;

import com.hospital.entity.Doctor;
import com.hospital.entity.Item;
import com.hospital.entity.Recipe;
import com.hospital.entity.SomeRecipe;
import com.hospital.mapper.DoctorMapper;
import com.hospital.mapper.ItemMapper;
import com.hospital.mapper.PatientMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class ItemService {
    @Resource
    private ItemMapper itemMapper;
    @Resource
    private PatientMapper patientMapper;
    @Resource
    private DoctorMapper doctorMapper;

    public void addCheckItem(Item item){
        itemMapper.addCheckItem(item);
    }

    //显示所有已缴费的检查
    public List<Recipe> showAllItem(){
        List<Item> items = itemMapper.selectitems();
        List<Recipe> recipes = new LinkedList<>();
        Recipe recipe = new Recipe();
        for(int i=0; i<items.size(); i++){
            String pIdentificationNum = patientMapper.selectbyid(items.get(i).getPatientId()).getpIdentificationNum();
            Doctor doctor = doctorMapper.selectbyid(items.get(i).getDoctorId());
            recipe.setdName(doctor.getdName());
            recipe.setRecipeName(items.get(i).getItemName());
            recipe.setPrice(items.get(i).getItemPrice());
            recipe.setPatientId(items.get(i).getPatientId());
            recipe.setRdate(items.get(i).getItemDate());
            recipe.setpIdentificationNum(pIdentificationNum);
            recipe.setState("等待检查");
            recipes.add(recipe);
        }
        return recipes;
    }

    //显示所有已缴费的检查
    public List<Recipe> searchitem(String pIdentificationNum){
        Integer patientId = patientMapper.selectByIdentificationNum(pIdentificationNum);
        List<Item> items = itemMapper.selectitemsbyPid(patientId);
        List<Recipe> recipes = new LinkedList<>();
        Recipe recipe = new Recipe();
        for(int i=0; i<items.size(); i++){
            Doctor doctor = doctorMapper.selectbyid(items.get(i).getDoctorId());
            recipe.setdName(doctor.getdName());
            recipe.setRecipeName(items.get(i).getItemName());
            recipe.setPrice(items.get(i).getItemPrice());
            recipe.setPatientId(items.get(i).getPatientId());
            recipe.setRdate(items.get(i).getItemDate());
            recipe.setpIdentificationNum(pIdentificationNum);
            recipe.setState("等待检查");
            recipes.add(recipe);
        }
        return recipes;
    }

    //设置所有检查已做
    public void setItemsHaveDone(SomeRecipe someRecipe) {
        List<String> Names = someRecipe.getRecipeName();
        List<String> pIdentificationNums = someRecipe.getpIdentificationNum();
        for (int i=0;i<Names.size();i++){
            Integer patientId = patientMapper.selectByIdentificationNum(pIdentificationNums.get(i));
            itemMapper.setItemsHaveDone(patientId,Names.get(i));
        }
    }

    //设置某项检查已做
    public void setItemHaveDone(String recordname, String pIdentificationNum) {
        Integer patientId = patientMapper.selectByIdentificationNum(pIdentificationNum);
        itemMapper.setItemsHaveDone(patientId,recordname);
    }

    public List<Item> showAll(Integer doctorId, Integer patientId){
        List<Item> items = itemMapper.selectitemsbyPidandDid(patientId,doctorId);
        return  items;

    }

}
