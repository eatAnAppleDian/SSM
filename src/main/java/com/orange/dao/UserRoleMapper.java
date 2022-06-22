package com.orange.dao;

import com.orange.entity.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int deleteByPrimaryKey(@Param("id") String id, @Param("rolId") String rolId);

    int insert(UserRole record);

    List<UserRole> selectAll();
}