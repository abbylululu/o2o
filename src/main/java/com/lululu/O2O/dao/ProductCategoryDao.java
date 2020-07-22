package com.lululu.O2O.dao;

import java.util.List;

import com.lululu.O2O.entity.ProductCategory;

public interface ProductCategoryDao {

	/**
	 * query all product categories by shopId
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);
	
}
