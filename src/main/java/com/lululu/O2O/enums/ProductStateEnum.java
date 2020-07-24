package com.lululu.O2O.enums;

public enum ProductStateEnum {
	OFFLINE(-1, "Illegal Product"), DOWN(0, "Offline"), SUCCESS(1, "Success"), INNER_ERROR(-1001, "Failed"), EMPTY(-1002, "Product is empty");

	private int state;

	private String stateInfo;

	private ProductStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ProductStateEnum stateOf(int index) {
		for (ProductStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}
