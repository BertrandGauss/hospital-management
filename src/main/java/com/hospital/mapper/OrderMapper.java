package com.hospital.mapper;

import com.hospital.entity.Order;

import java.util.List;

public interface OrderMapper {
    void add(Order order);

    Integer countOrderNumberZ(Order order);

    Integer countOrderNumber(Order order);

    List<Order> selectbyId(Integer patientId);
}
