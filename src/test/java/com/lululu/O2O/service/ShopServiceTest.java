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
import com.lululu.O2O.dto.ImageHolder;
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
		ImageHolder imageHolder = new ImageHolder("cloud.jpg", is);
		ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
		System.out.println("new shop addr: " + shopExecution.getShop().getShopImg());
	}
	
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException {
		
		Shop shop = new Shop();
		
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		
		owner.setUserId(13L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(44L);
		
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("Turn the Page");
		shop.setShopDesc("An off-line book store");
		shop.setShopAddr("test bear shop 2 addr");
		shop.setPhone("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("verifying");
	
		File shopImg = new File("/Users/lululumi/Pictures/upload/off-line.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder(shopImg.getName(), is);
		ShopExecution se = shopService.addShop(shop, imageHolder);
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
	
	@Test
	@Ignore
	public void testGetShopList() {
		Shop shopCondition = new Shop();
		ShopCategory parent = new ShopCategory();
		parent.setShopCategoryId(42L);
		ShopCategory sc = new ShopCategory();
		sc.setParent(parent);
		shopCondition.setShopCategory(sc);
		
		ShopExecution se = shopService.getShopList(shopCondition, 0, 5);
		System.out.println("queried counts of shop list" + se.getShopList().size());
		System.out.println("shop counts" + se.getCount());
	}

}
