package com.lululu.O2O.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lululu.O2O.BaseTest;
import com.lululu.O2O.entity.ShopCategory;

public class ShopCategoryServiceTest extends BaseTest {

	@Autowired
	ShopCategoryService shopCategoryService;
	
	@Test
	public void testGetShopCategoryList() {
		
		List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(null);
		assertEquals(4, shopCategoryList.size());
		
	}
	
}
