package com.lululu.O2O.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lululu.O2O.entity.Product;

public interface ProductDao {

	int insertProduct(Product product);
	
	int deleteProduct(@Param("productId") long productId, @Param("shopId") long shopId);

	List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	
	int queryProductCount(@Param("productCondition") Product productCondition);
	
	Product queryProductById(long productId);
	
	int updateProduct(Product product);
	
	int updateProductCategoryToNull(long productCategoryId);
	
}
