package com.hospital.mapper;

import com.hospital.entity.Item;
import org.apache.ibatis.annotations.Param;

public interface ItemMapper {
    void addCheckItem(Item item);

    void setItemsHaveDone(@Param("patientId") Integer patientId, @Param("itemName") String itemName );
}
