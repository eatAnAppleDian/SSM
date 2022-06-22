package com.orange.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orange.vo.QueryVo;
import com.orange.dao.ProductMapper;
import com.orange.entity.Product;
import com.orange.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product findById(String id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> findAll() {
        return productMapper.selectAll();
    }

    @Override
    public void save(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKey(product);
    }

    @Override
    public void delete(String[] ids) {
        productMapper.deleteMutli(ids);
    }

    @Override
    public List<Product> findByQuery(QueryVo queryVo) {
        return productMapper.findByQuery(queryVo);
    }

    @Override
    public PageInfo<Product> findByPage(Integer pageNo) {
        int pageSize = 2;
        PageHelper.startPage(pageNo, pageSize);
        List<Product> productList = productMapper.selectAll();
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        return pageInfo;
    }
}
