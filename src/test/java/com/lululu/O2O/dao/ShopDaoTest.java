package com.lululu.O2O.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lululu.O2O.BaseTest;
import com.lululu.O2O.entity.Area;
import com.lululu.O2O.entity.PersonInfo;
import com.lululu.O2O.entity.Shop;
import com.lululu.O2O.entity.ShopCategory;

public class ShopDaoTest extends BaseTest {

	@Autowired
	private ShopDao shopDao;
	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop = new Shop();
		
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		
		owner.setUserId(13L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(43L);
		
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("A New Page");
		shop.setShopDesc("An on-line bookstore");
		shop.setShopAddr("test on-line book shop addr");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("verifying");
		shop.setShopImg("/Users/lululumi/Pictures/upload/on-line.jpg");
		
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
		
		owner.setUserId(13L);
		area.setAreaId(1);
		shopCategory.setShopCategoryId(44L);
		
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("Turn the Page");
		shop.setShopDesc("An off-line bookstore");
		shop.setShopAddr("test off-line book shop addr");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("verifying");
		shop.setShopImg("/Users/lululumi/Pictures/upload/off-line.jpg");
		
		effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);
		
		shop.setShopDesc("test2");
		shop.setShopAddr("test2");
		shop.setLastEditTime(new Date());
		
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testQueryByShopId() {
		long shopId = 1;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaId: " + shop.getArea().getAreaId());
		System.out.println("areaName: " + shop.getArea().getAreaName());
	}
	
	@Test
	@Ignore
	public void testQueryShopList() {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1L);
		shopCondition.setOwner(owner);
		
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(1L);
		shopCondition.setShopCategory(sc);
		
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("queried counts of shop list" + shopList.size());
		System.out.println("shop counts" + count);
		
	}
	
	@Test
	public void testQueryShopListAndCount() {
		Shop shopCondition = new Shop();
		ShopCategory childCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(42L);
		childCategory.setParent(parentCategory);
		shopCondition.setShopCategory(childCategory);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 2, 5);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("size：" + shopList.size());
		System.out.println("count：" + count);		
	}
	
}
