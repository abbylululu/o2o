package com.lululu.O2O.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lululu.O2O.dao.ShopDao;
import com.lululu.O2O.dto.ShopExecution;
import com.lululu.O2O.entity.Shop;
import com.lululu.O2O.enums.ShopStateEnum;
import com.lululu.O2O.exceptions.ShopOperationException;
import com.lululu.O2O.service.ShopService;
import com.lululu.O2O.util.ImageUtil;
import com.lululu.O2O.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;
	
	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {
		
		// verify whether shop has null fields
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			
			// initialize shop info, enable status is initialized as "verifying"
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			
			// add the shop into DB
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("failed to add the shop");
			}
			else {
				if (shopImgInputStream != null) {
					// store the img
					try {
						addShopImg(shop, shopImgInputStream, fileName);		
					} catch (Exception e) {
						throw new ShopOperationException("add shopImg error" + e.getMessage());
					}
					// update shopImg addr
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("failed to update the shopImg address");
					}
				}
			}
			
		} catch(Exception e) {
			throw new ShopOperationException("addShop error" + e.getMessage());
		}
		
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
		
		// get shop image relative path
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
		shop.setShopImg(shopImgAddr);
		
	}

	
	
}
