package com.orange.dao;

import com.orange.entity.Traveller;
import java.util.List;

public interface TravellerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Traveller record);

    Traveller selectByPrimaryKey(String id);

    List<Traveller> selectAll();

    int updateByPrimaryKey(Traveller record);
}