package com.hospital.mapper;

import com.hospital.entity.Item;

import com.hospital.entity.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpenseMapper {

    List<Item> selectitemsbypatientId(Integer patientId);

    List<Record> selectmebypatientId(Integer patientId);

    void payItems(Integer patientId);

    void payMedicine(Integer patientId);

    Item checkitem(@Param("patientId")Integer patientId,@Param("recipeName") String  recipeName);

    Record checkmedicine(@Param("patientId")Integer patientId,@Param("recipeName") String  recipeName);

    void deleteItem(@Param("patientId")Integer patientId,@Param("recipeName") String  recipeName);

    void deleteRecord(@Param("patientId")Integer patientId,@Param("recipeName") String  recipeName);

}
