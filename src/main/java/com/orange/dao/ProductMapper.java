package com.orange.dao;

import com.orange.vo.QueryVo;
import com.orange.entity.Product;
import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(String id);
    int insert(Product record);
    Product selectByPrimaryKey(String id);
    List<Product> selectAll();
    int updateByPrimaryKey(Product record);

    List<Product> findByQuery(QueryVo queryVo);

    int deleteMutli(String[] ids);

}