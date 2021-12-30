package com.hospital.service;

import com.hospital.entity.Recipe;
import com.hospital.mapper.RecipeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RecipeService {
    @Resource
    private RecipeMapper recipeMapper;

    // 开处方
    public void addRecipe(Recipe recipe) {
        recipeMapper.add(recipe);
    }
}
