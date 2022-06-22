package com.orange.vo;

import com.orange.entity.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class QueryVo {
	private Product product;
	private BigDecimal minprice;
	private BigDecimal maxprice;
}
