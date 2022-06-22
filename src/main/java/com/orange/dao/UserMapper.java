package com.orange.dao;

import com.orange.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    /**
     * 批处理插入用户
     * @param userList
     * @return
     */
    int batchAddUser(@Param("users") List<User> userList);
}