package com.orange.dao;

import com.orange.entity.SysLog;
import java.util.List;

public interface SysLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysLog record);

    SysLog selectByPrimaryKey(String id);

    List<SysLog> selectAll();

    int updateByPrimaryKey(SysLog record);
}