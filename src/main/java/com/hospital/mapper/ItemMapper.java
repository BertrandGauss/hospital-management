package com.hospital.mapper;

import com.hospital.entity.Item;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ItemMapper {
    void addCheckItem(Item item);

    void setItemsHaveDone(@Param("patientId") Integer patientId, @Param("itemName") String itemName );

    List<Item> selectitems();

    List<Item> selectitemsbyPid(String pIdentificationNum);

    Date getIdate(@Param("patientId") Integer patientId, @Param("itemName") String itemName );
}
