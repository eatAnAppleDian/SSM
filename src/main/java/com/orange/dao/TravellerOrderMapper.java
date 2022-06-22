package com.orange.dao;

import com.orange.entity.TravellerOrder;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TravellerOrderMapper {
    int deleteByPrimaryKey(@Param("id") String id, @Param("ordId") String ordId);

    int insert(TravellerOrder record);

    List<TravellerOrder> selectAll();
}