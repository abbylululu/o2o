package com.lululu.O2O.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lululu.O2O.BaseTest;
import com.lululu.O2O.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	public void testQueryByShopId() throws Exception {
		
		long shopId = 28L;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		assertEquals(8, productCategoryList.size());
		
	}
	
}
