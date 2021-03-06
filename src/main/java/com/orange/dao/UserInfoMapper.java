package com.orange.dao;

import com.orange.entity.UserInfo;
import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(String id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);
}