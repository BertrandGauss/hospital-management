package com.hospital.controller;

import com.alibaba.fastjson.JSONObject;
import com.hospital.entity.History;
import com.hospital.entity.Patient;
import com.hospital.entity.Recipe;
import com.hospital.service.HistoryService;
import com.hospital.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    // 开处方
    @RequestMapping(value = "/addrecipe", method = {RequestMethod.POST})
    private JSONObject addRecipe(@RequestBody Recipe recipe){
        recipeService.addRecipe(recipe);
        JSONObject json = new JSONObject();
        json.put("code", 0);
        json.put("msg","处方开具成功");
        System.out.println(json.get("msg"));
        return json;
    }
}
