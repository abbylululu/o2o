package com.lululu.O2O.service;

import java.util.List;

import com.lululu.O2O.entity.ProductCategory;

public interface ProductCategoryService {

	List<ProductCategory> getProductCategoryList(long shopId);
	
}
