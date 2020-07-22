package com.lululu.O2O.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lululu.O2O.BaseTest;
import com.lululu.O2O.dto.ShopExecution;
import com.lululu.O2O.entity.Area;
import com.lululu.O2O.entity.PersonInfo;
import com.lululu.O2O.entity.Shop;
import com.lululu.O2O.entity.ShopCategory;
import com.lululu.O2O.enums.ShopStateEnum;
import com.lululu.O2O.exceptions.ShopOperationException;

public class ShopServiceTest extends BaseTest {
	
	@Autowired
	private ShopService shopService;
	
	@Test
	@Ignore
	public void testModifyShop() throws ShopOperationException, FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopId(48L);
		shop.setShopName("New Name");
		
		File shopImg = new File("/Users/lululumi/Pictures/cloud.jpg");
		InputStream is = new FileInputStream(shopImg);
		ShopExecution shopExecution = shopService.modifyShop(shop, is, "cloud.jpg");
		System.out.println("new shop addr: " + shopExecution.getShop().getShopImg());
	}
	
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException {
		
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
		shop.setShopName("Test Shop3");
		shop.setShopDesc("test3");
		shop.setShopAddr("test3");
		shop.setPhone("test3");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("verifying");
	
		File shopImg = new File("/Users/lululumi/Pictures/cloud.jpg");
		InputStream is = new FileInputStream(shopImg);
		ShopExecution se = shopService.addShop(shop, is, shopImg.getName());
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
		
	}
	
	@Test
	public void testGetShopList() {
		Shop shopCondition = new Shop();
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(1L);
		shopCondition.setShopCategory(sc);
		
		ShopExecution se = shopService.getShopList(shopCondition, 2, 5);
		System.out.println("queried counts of shop list" + se.getShopList().size());
		System.out.println("shop counts" + se.getCount());
	}

}
