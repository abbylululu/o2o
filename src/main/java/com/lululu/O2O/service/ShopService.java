package com.lululu.O2O.service;

import java.io.File;

import com.lululu.O2O.dto.ShopExecution;
import com.lululu.O2O.entity.Shop;

public interface ShopService {

	ShopExecution addShop(Shop shop, File shopImg);
	
}
