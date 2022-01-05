package com.hospital.entity;

import java.util.List;

public class SomeRecipe {
    private List<String> pIdentificationNum;
    private List<String> recipeName;

    public List<String> getpIdentificationNum() {
        return pIdentificationNum;
    }

    public void setpIdentificationNum(List<String> pIdentificationNum) {
        this.pIdentificationNum = pIdentificationNum;
    }

    public List<String> getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(List<String> recipeName) {
        this.recipeName = recipeName;
    }
}
