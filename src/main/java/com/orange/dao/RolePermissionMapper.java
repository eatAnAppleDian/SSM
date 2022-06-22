package com.orange.dao;

import com.orange.entity.RolePermission;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(@Param("id") String id, @Param("perId") String perId);

    int insert(RolePermission record);

    List<RolePermission> selectAll();
}