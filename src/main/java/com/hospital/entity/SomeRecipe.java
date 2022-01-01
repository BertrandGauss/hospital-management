package com.hospital.entity;

import java.util.List;

public class SomeRecipe {
    private String pIdentificationNum;
    private List<String> recipeName;

    public String getpIdentificationNum() {
        return pIdentificationNum;
    }

    public void setpIdentificationNum(String pIdentificationNum) {
        this.pIdentificationNum = pIdentificationNum;
    }

    public List<String> getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(List<String> recipeName) {
        this.recipeName = recipeName;
    }
}
