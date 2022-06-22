package com.orange.controller;

import com.orange.entity.Product;
import com.orange.service.ProductService;
import com.orange.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public R<List<Product>> findAll(){
        List<Product> all = productService.findAll();
        return new R<List<Product>>().setCode("200").setT(all);
    }
}
