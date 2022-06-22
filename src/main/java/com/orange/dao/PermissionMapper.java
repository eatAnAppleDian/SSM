package com.orange.dao;

import com.orange.entity.Permission;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    Permission selectByPrimaryKey(String id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);
}