package com.lululu.O2O.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lululu.O2O.entity.ShopCategory;

public interface ShopCategoryDao {
	
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
	
}
