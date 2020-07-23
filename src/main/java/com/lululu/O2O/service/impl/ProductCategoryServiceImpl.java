package com.lululu.O2O.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lululu.O2O.dao.ProductCategoryDao;
import com.lululu.O2O.dto.ProductCategoryExecution;
import com.lululu.O2O.entity.ProductCategory;
import com.lululu.O2O.enums.ProductCategoryStateEnum;
import com.lululu.O2O.exceptions.ProductCategoryOperationException;
import com.lululu.O2O.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao; 
	
	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		
		return productCategoryDao.queryProductCategoryList(shopId);
		
	}

	@Override
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException {
		
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if (effectedNum <= 0) {
					throw new ProductCategoryOperationException("product category creation failed");
				} else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}

			} catch (Exception e) {
				throw new ProductCategoryOperationException("batchAddProductCategory error: " + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
		
	}

	@Override
	@Transactional // b/c there are 2 steps. only submit when both of the 2 steps succeed
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		
		// TODO: firstly set all product category id of all products under denoted category as null
		try {
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if (effectedNum <= 0) {
				throw new ProductCategoryOperationException("product category deletion failed");
			} else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			throw new ProductCategoryOperationException("deleteProductCategory error: " + e.getMessage());
		}
	}

}
