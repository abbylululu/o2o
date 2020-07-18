package com.lululu.O2O.dto;

import java.util.List;

import com.lululu.O2O.entity.Shop;
import com.lululu.O2O.enums.ShopStateEnum;

/**
 * wrapper class for add shop info and shop info
 * @author lululumi
 *
 */
public class ShopExecution {

	// result status
	private int state;
	
	// status explanation
	private String stateInfo;
	
	// number of shops
	private int count;
	
	// for CRUD operations
	private Shop shop;
	
	// for shop list loop up
	private List<Shop> shopList;

	public ShopExecution() {

	}

	// constructor for failed shop creation
	public ShopExecution(ShopStateEnum stateEnum) {

		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		
	}
	
	// constructor for successful shop creation
	public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
		
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
		
	}
	
	// constructor for successful shop creation
	public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
		
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
		
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	
}
