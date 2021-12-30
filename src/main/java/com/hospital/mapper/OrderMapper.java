package com.hospital.mapper;

import com.hospital.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface OrderMapper {
    void add(Order order);

    Integer countOrderNumberZ(Order order);

    Integer countOrderNumber(Order order);

    List<Order> selectbyId(Integer patientId);

    Integer selectValid(@Param("patientId") Integer patientId, @Param("orderId") Integer orderId);

    void deleteValid(@Param("patientId") Integer patientId, @Param("orderId") Integer orderId);

    Integer selectbyorderTime(Integer patientId);

    void updateisValid(@Param("oDate") Date oDate, @Param("oTime") Time oTime);
}
