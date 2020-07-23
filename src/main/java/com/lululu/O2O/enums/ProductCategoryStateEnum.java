package com.lululu.O2O.enums;

public enum ProductCategoryStateEnum {

	SUCCESS(1, "Success"), INNER_ERROR(-1001, "Failed Operation"), EMPTY_LIST(-1002, "Product category is less than 1");

	private int state;

	private String stateInfo;

	private ProductCategoryStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	/**
	 * return state info by state
	 * @param index
	 * @return
	 */
	public static ProductCategoryStateEnum stateOf(int index) {
		for (ProductCategoryStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
	
}
