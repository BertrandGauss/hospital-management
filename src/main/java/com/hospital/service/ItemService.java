package com.hospital.service;

import com.hospital.entity.Item;
import com.hospital.mapper.ItemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItemService {
    @Resource
    private ItemMapper itemMapper;

    public void addCheckItem(Item item){
        itemMapper.addCheckItem(item);
    }
}
