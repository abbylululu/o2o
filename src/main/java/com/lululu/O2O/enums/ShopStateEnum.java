package com.lululu.O2O.enums;

public enum ShopStateEnum {

	CHECK(0, "verifying"), OFFLINE(-1, "Illegal"), SUCCESS(1, "success"), PASS(2, "pass verification"),
	INNER_ERROR(-1001, "system error"), NULL_SHOPID(-1002, "shopId is NULL"), NULL_SHOP(-1003, "shop info is NULL");

	private int state;
	private String stateInfo;

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

	private ShopStateEnum(int state, String stateInfo) {

		this.state = state;
		this.stateInfo = stateInfo;

	}
	
	/**
	 * return an enum based on a state
	 * @param state
	 * @return
	 */
	public static ShopStateEnum stateOf(int state) {
		
		for (ShopStateEnum stateEnum: values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
		
	}

}
