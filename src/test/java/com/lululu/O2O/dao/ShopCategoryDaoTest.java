package com.lululu.O2O.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lululu.O2O.BaseTest;
import com.lululu.O2O.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest {
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Test
	public void testQueryShopCategory() {
		
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
		assertEquals(7, shopCategoryList.size());
		
		ShopCategory testCategory = new ShopCategory();
		ShopCategory paretnCategory = new ShopCategory();
		paretnCategory.setShopCategoryId(1L);
		testCategory.setParent(paretnCategory);
		shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
		assertEquals(1, shopCategoryList.size());
		System.out.println(shopCategoryList.get(0).getShopCategoryName());
		
	}
	
}
