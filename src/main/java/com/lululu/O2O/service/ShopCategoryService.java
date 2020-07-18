package com.lululu.O2O.service;

import java.util.List;

import com.lululu.O2O.entity.ShopCategory;

public interface ShopCategoryService {

	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
	
}
