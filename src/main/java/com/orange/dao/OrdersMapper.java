package com.orange.dao;

import com.orange.entity.Orders;
import java.util.List;

public interface OrdersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Orders record);

    Orders selectByPrimaryKey(String id);

    List<Orders> selectAll();

    int updateByPrimaryKey(Orders record);
}