package com.lululu.O2O.service;

import java.util.List;

import com.lululu.O2O.entity.ShopCategory;

public interface ShopCategoryService {
	public static final String SCLISTKEY = "shopcategorylist";
	
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
	
}
