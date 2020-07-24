package com.lululu.O2O.service;

import java.io.File;
import java.io.InputStream;

import com.lululu.O2O.dto.ImageHolder;
import com.lululu.O2O.dto.ShopExecution;
import com.lululu.O2O.entity.Shop;
import com.lululu.O2O.exceptions.ShopOperationException;

public interface ShopService {

	ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
	
	/**
	 * get shop info by shop id
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	
	/**update shop info, including photo processing
	 * 
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
	
	/**
	 * based on shopCondition, return shop list with pagination
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
}
