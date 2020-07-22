package com.lululu.O2O.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	
	/**
	 * paging query shop, conditions: shop name(fuzzy query), shop condition, shop category, owner
	 * @param shopCondition
	 * @param rowIndex select from index line
	 * @param pageSize counts of returned rows
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
							@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	/**
	 * return total counts of queryShopList
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
