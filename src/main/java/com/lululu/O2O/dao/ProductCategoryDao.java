package com.lululu.O2O.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lululu.O2O.entity.ProductCategory;

public interface ProductCategoryDao {

	/**
	 * query all product categories by shopId
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);
	
	/**
	 * batch insert product category
	 * @param productCategoryList
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	
	/**
	 * delete productCategory by id
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
	
}
