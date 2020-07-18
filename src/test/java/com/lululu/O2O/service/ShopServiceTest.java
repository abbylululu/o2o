package com.lululu.O2O.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lululu.O2O.BaseTest;
import com.lululu.O2O.dto.ShopExecution;
import com.lululu.O2O.entity.Area;
import com.lululu.O2O.entity.PersonInfo;
import com.lululu.O2O.entity.Shop;
import com.lululu.O2O.entity.ShopCategory;
import com.lululu.O2O.enums.ShopStateEnum;

public class ShopServiceTest extends BaseTest {
	
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testAddShop() {
		
		Shop shop = new Shop();
		
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("Test Shop???");
		shop.setShopDesc("test111");
		shop.setShopAddr("test111");
		shop.setPhone("test111");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("verifying");
	
		File shopImg = new File("/Users/lululumi/Pictures/cloud.jpg");
		ShopExecution se = shopService.addShop(shop, shopImg);
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
		
	}

}
