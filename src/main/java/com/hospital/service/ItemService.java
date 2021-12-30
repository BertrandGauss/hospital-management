package com.hospital.service;

import com.hospital.mapper.ItemMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItemService {
    @Resource
    private ItemMapper itemMapper;

}
