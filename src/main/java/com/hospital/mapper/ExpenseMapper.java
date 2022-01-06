package com.hospital.mapper;

import com.hospital.entity.Item;

import com.hospital.entity.Record;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ExpenseMapper {

    List<Item> selectitemsbypatientId(Integer patientId);

    List<Record> selectmebypatientId(Integer patientId);

    List<Item> selectitemsPay(Integer patientId);

    List<Record> selectmePay(Integer patientId);

    List<Record> selectmedbydate(@Param("patientId") Integer patientId, @Param("startDate") Date startDate, @Param("endDate")Date endDate);

    List<Item> selectitembydate(@Param("patientId") Integer patientId, @Param("startDate") Date startDate, @Param("endDate")Date endDate);

    void payItems(Integer patientId);

    void payMedicine(Integer patientId);

    Item checkitem(@Param("patientId")Integer patientId,@Param("recipeName") String  recipeName);

    Record checkmedicine(@Param("patientId")Integer patientId,@Param("recipeName") String  recipeName);

    void deleteItem(@Param("patientId")Integer patientId,@Param("recipeName") String  recipeName);

    void deleteRecord(@Param("patientId")Integer patientId,@Param("recipeName") String  recipeName);

}
