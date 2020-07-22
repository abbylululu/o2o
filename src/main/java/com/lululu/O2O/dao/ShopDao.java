package com.lululu.O2O.dao;

import com.lululu.O2O.entity.Shop;

public interface ShopDao {
	
	/*
	 * add new shop
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	
	/*
	 * update shop
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
	
	/**
	 * query shop info by id
	 * @param shopId
	 * @return
	 */
	Shop queryByShopId(long shopId);
	
}
