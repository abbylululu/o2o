package com.lululu.O2O.service;

import java.io.File;
import java.io.InputStream;

import com.lululu.O2O.dto.ShopExecution;
import com.lululu.O2O.entity.Shop;
import com.lululu.O2O.exceptions.ShopOperationException;

public interface ShopService {

	ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
	
}
