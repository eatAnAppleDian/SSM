package com.orange.dao;

import com.orange.entity.Roles;
import java.util.List;

public interface RolesMapper {
    int deleteByPrimaryKey(String id);

    int insert(Roles record);

    Roles selectByPrimaryKey(String id);

    List<Roles> selectAll();

    int updateByPrimaryKey(Roles record);
}