package com.lululu.O2O.service;

import java.util.List;

import com.lululu.O2O.dto.ProductCategoryExecution;
import com.lululu.O2O.entity.ProductCategory;
import com.lululu.O2O.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {

	List<ProductCategory> getProductCategoryList(long shopId);
	
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;
	
	/**
	 * firstly set all product category id of all products under denoted category as null
	 * then delete the category
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
}
