package com.example.demo.entity;

// 药品、医学检查
public class Item {
    public Integer itemId;     // 条项ID
    public Integer tag;        // 区分药品和医学检查（0代表药品，1代表医学检查）
    public String itemName;    // 药品/医学检查（例如核磁共振，CT等）名称
    public String price;       // 药品/医学检查单价
    public String usage;       // 用法
    public Integer remains;    // 药品库存量/医学检查剩余可做次数
    public Integer haveDone;   // 是否做过医学检查/使用过药品（0代表否，1代表是）

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Integer getRemains() {
        return remains;
    }

    public void setRemains(Integer remains) {
        this.remains = remains;
    }

    public Integer getHaveDone() {
        return haveDone;
    }

    public void setHaveDone(Integer haveDone) {
        this.haveDone = haveDone;
    }
}
